import { Component, OnInit } from '@angular/core';
import { Client, ClientResponse } from 'src/app/api/models';
import { JoinSessionService } from 'src/app/join/join-session.service';
import { GameService } from '../../game.service';
import { SocketClientService } from '../../socket-client.service';

@Component({
  selector: 'app-option-response',
  templateUrl: './option-response.component.html',
  styleUrls: ['./option-response.component.css'],
})
export class OptionResponseComponent implements OnInit {
  selected?: string;
  response: ClientResponse;
  constructor(
    public game: GameService,
    private socket: SocketClientService,
    private session: JoinSessionService
  ) {}

  ngOnInit(): void {
    try {
      this.selected = this.game.sessionState.clientResponses.filter(
        (e) => e.client.nickname === this.session.joinSessionInfo.nickname
      )[0].textResponse;
    } catch (error) {}
  }

  select(option: string): void {
    this.selected = option;
    this.response = {
      client: this.game.client,
      textResponse: this.selected,
    };
    this.socket.send(
      `/play/session/${this.game.sessionId}/respond`,
      this.response
    );
  }

  getIconURL(client: Client): string {
    return `https://avatars.dicebear.com/api/big-ears-neutral/${client.nickname}.svg?r=50`;
  }
}
