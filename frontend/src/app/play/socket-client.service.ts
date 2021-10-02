import { Injectable, OnDestroy } from '@angular/core';
import { Client, Message, StompSubscription } from '@stomp/stompjs';
import { BehaviorSubject, Observable } from 'rxjs';
import { filter, first, switchMap } from 'rxjs/operators';
import SockJS from 'sockjs-client';
import config from '../../assets/config/config.json';
import { SocketClientState } from './socket-client-state';

@Injectable({
  providedIn: 'root',
})
export class SocketClientService implements OnDestroy {
  private client: Client;
  private state: BehaviorSubject<SocketClientState>;

  constructor() {
    this.client = new Client({
      webSocketFactory: () =>
        new SockJS(`${config.apiUrl}${config.apiBasePath}/open-socket`),
      reconnectDelay: 4000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });
    this.state = new BehaviorSubject<SocketClientState>(
      SocketClientState.ATTEMPTING
    );
    this.client.onConnect = function () {
      this.state.next(SocketClientState.CONNECTED);
    };
    this.client.onDisconnect = function () {
      this.state.next(SocketClientState.ATTEMPTING);
    };
  }

  private connect(): Observable<Client> {
    return new Observable<Client>((observer) => {
      this.state
        .pipe(
          filter(
            (state: SocketClientState) => state === SocketClientState.CONNECTED
          )
        )
        .subscribe(() => {
          observer.next(this.client);
        });
    });
  }

  ngOnDestroy(): void {
    this.disconnect();
  }

  /**
   * This method activates the SockJS connection to the backend.
   */
  connectToSocket(): void {
    this.client.activate();
  }

  /**
   * This method disconnects from the SockJS socket.
   */
  disconnect(): void {
    this.connect()
      .pipe(first())
      .subscribe((client) => client.deactivate());
  }

  /**
   * This method returns a Observable with the message from the topic every time a new Event is emitted.
   * @param topic The topic to subscribe to.
   * @param handler The handler to use.
   * @returns a Observable with the message from the topic every time a new Event is emitted.
   */
  onMessage(
    topic: string,
    handler = SocketClientService.jsonHandler
  ): Observable<any> {
    return this.connect().pipe(
      first(),
      switchMap((client) => {
        return new Observable<any>((observer) => {
          const subscription: StompSubscription = client.subscribe(
            topic,
            (message) => {
              observer.next(handler(message));
            }
          );
          return () => client.unsubscribe(subscription.id);
        });
      })
    );
  }

  /**
   * like #onMessage just for plain text responses
   * @param topic The topic to subscribe to.
   * @returns a Observable with the message from the topic every time a new Event is emitted.
   */
  onPlainMessage(topic: string): Observable<string> {
    return this.onMessage(topic, SocketClientService.textHandler);
  }

  /**
   * Publish a new message to the specified topic.
   * @param topic The topic to send the payload to.
   * @param payload The payload to send
   */
  send(topic: string, payload: any): void {
    this.connect()
      .pipe(first())
      .subscribe((client) =>
        client.publish({ destination: topic, body: JSON.stringify(payload) })
      );
  }

  static jsonHandler(message: Message): any {
    return JSON.parse(message.body);
  }

  static textHandler(message: Message): string {
    return message.body;
  }
}
