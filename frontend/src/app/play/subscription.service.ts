import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
}
