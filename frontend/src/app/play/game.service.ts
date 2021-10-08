import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from '../api/models/client';
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
  constructor(
    private sessionInformation: JoinSessionService,
    private router: Router,
    private socket: SocketClientService,
    private subscriptionService: SubscriptionService
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
  }

  private makeSubscriptions(): void {
    this.subscriptionService.getClients(this.sessionId).subscribe((clients) => {
      this.clients = clients;
      this.client = clients.filter(
        (c) => c.nickname === this.sessionInformation.joinSessionInfo.nickname
      )[0];
      this.onlineClients = clients.filter((c) => c.connected);
    });
  }
}
