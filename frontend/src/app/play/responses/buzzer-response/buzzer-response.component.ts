import { Component, OnInit } from '@angular/core';
import { GameService } from '../../game.service';
import { SocketClientService } from '../../socket-client.service';

@Component({
  selector: 'app-buzzer-response',
  templateUrl: './buzzer-response.component.html',
  styleUrls: ['./buzzer-response.component.css'],
})
export class BuzzerResponseComponent implements OnInit {
  constructor(public game: GameService, private socket: SocketClientService) {}

  ngOnInit(): void {}

  buzzer(): void {
    this.socket.send(`/play/session/${this.game.sessionId}/buzz`, {});
  }

  wrong(): void {
    this.socket.send(`/play/session/${this.game.sessionId}/wrong`, {});
  }

  right(): void {
    this.socket.send(`/play/session/${this.game.sessionId}/correct`, {});
  }
}
