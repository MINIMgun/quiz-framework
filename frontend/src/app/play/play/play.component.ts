import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/api/models';
import { GameService } from '../game.service';
import { SocketClientService } from '../socket-client.service';

@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
  styleUrls: ['./play.component.css'],
})
export class PlayComponent implements OnInit {
  constructor(public game: GameService, private socket: SocketClientService) {}

  ngOnInit(): void {}

  getIconURL(client: Client): string {
    return `https://avatars.dicebear.com/api/big-ears-neutral/${client.nickname}.svg?r=50`;
  }

  next(): void {
    this.socket.send(`/play/session/${this.game.sessionId}/next`, {});
  }
}
