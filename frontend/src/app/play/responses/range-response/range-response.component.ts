import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ClientResponse } from 'src/app/api/models';
import { GameService } from '../../game.service';
import { SocketClientService } from '../../socket-client.service';

@Component({
  selector: 'app-range-response',
  templateUrl: './range-response.component.html',
  styleUrls: ['./range-response.component.css'],
})
export class RangeResponseComponent implements OnInit {
  guess = new FormControl();
  response: ClientResponse;
  constructor(public game: GameService, private socket: SocketClientService) {}

  ngOnInit(): void {
    this.guess.valueChanges.subscribe((val) => {
      this.response = {
        client: this.game.client,
        numberResponse: val,
      };
      this.socket.send(
        `/play/session/${this.game.sessionId}/respond`,
        this.response
      );
    });
  }
}
