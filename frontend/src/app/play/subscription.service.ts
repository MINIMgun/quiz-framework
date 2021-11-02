import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClientResponse, SessionState, Timer } from '../api/models';
import { Client } from '../api/models/client';
import { SocketClientService } from './socket-client.service';

@Injectable({
  providedIn: 'root',
})
export class SubscriptionService {
  constructor(private socket: SocketClientService) {}

  getClients(sessionId: string): Observable<Array<Client>> {
    return this.socket.onMessage(`/topic/session/${sessionId}/join`);
  }

  getSessionState(sessionId: string): Observable<SessionState> {
    return this.socket.onMessage(`/topic/session/${sessionId}/current-state`);
  }

  getTimer(sessionId: string): Observable<Timer> {
    return this.socket.onMessage(`/topic/session/${sessionId}/timer`);
  }

  getClientResponses(sessionId: string): Observable<Array<ClientResponse>> {
    return this.socket.onMessage(`/topic/session/${sessionId}/responses`);
  }
}
