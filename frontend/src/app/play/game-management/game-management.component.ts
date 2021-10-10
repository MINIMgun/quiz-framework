import { Component, OnDestroy, OnInit } from '@angular/core';
import { GameService } from '../game.service';
import { SocketClientService } from '../socket-client.service';

@Component({
  templateUrl: './game-management.component.html',
  styleUrls: ['./game-management.component.css'],
})
export class GameManagementComponent implements OnInit, OnDestroy {
  constructor(public game: GameService, private socket: SocketClientService) {}

  ngOnDestroy(): void {
    this.socket.disconnect();
  }

  ngOnInit(): void {
    this.game.joinSession();
  }
}
