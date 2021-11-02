import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ClientResponse, SessionState, Timer } from '../api/models';
import { Client } from '../api/models/client';
import { PlayControllerService } from '../api/services';
import { JoinSessionService } from '../join/join-session.service';
import { SocketClientService } from './socket-client.service';
import { SubscriptionService } from './subscription.service';

@Injectable({
  providedIn: 'root',
})
export class GameService {
  sessionId: string;
  client: Client;
  clients: Client[];
  onlineClients: Client[];
  gameClients: Client[];
  responses: ClientResponse[];
  timer: Timer;
  sessionState: SessionState;
  private notSubscribed: boolean = true;
  constructor(
    private sessionInformation: JoinSessionService,
    private router: Router,
    private socket: SocketClientService,
    private subscriptionService: SubscriptionService,
    private api: PlayControllerService
  ) {}

  joinSession(): void {
    const joinSessionInfo = this.sessionInformation.joinSessionInfo;
    if (
      !joinSessionInfo ||
      !joinSessionInfo.sessionId ||
      !joinSessionInfo.nickname
    ) {
      this.router.navigate(['join']);
    }
    this.sessionId = joinSessionInfo.sessionId;
    this.socket.connectToSocket();
    this.makeSubscriptions();
    this.socket.send(`/play/session/${this.sessionId}/join`, joinSessionInfo);
    this.getSessionState();
  }

  private makeSubscriptions(): void {
    this.subscriptionService.getClients(this.sessionId).subscribe((clients) => {
      this.clients = clients;
      this.client = clients.filter(
        (c) => c.nickname === this.sessionInformation.joinSessionInfo.nickname
      )[0];
      this.onlineClients = clients.filter((c) => c.connected);
      this.gameClients = clients.filter((c) => !c.gameMaster);
      if (this.client.gameMaster && this.notSubscribed) {
        this.subscriptionService
          .getClientResponses(this.sessionId)
          .subscribe((responses) => {
            this.responses = responses;
            this.sessionState.clientResponses = responses;
          });
        this.notSubscribed = false;
      }
    });
    this.subscriptionService
      .getSessionState(this.sessionId)
      .subscribe((sessionState) => {
        this.sessionState = sessionState;
        this.timer = sessionState.timer;
        this.responses = sessionState.clientResponses;
      });
    this.subscriptionService.getTimer(this.sessionId).subscribe((timer) => {
      this.timer = timer;
      this.sessionState.timer = timer;
    });
  }

  private getSessionState(): void {
    this.api
      .getCurrentSessionState({
        sessionId: this.sessionId,
      })
      .subscribe((res) => {
        this.sessionState = res;
      });
  }
}
