import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/api/models';
import { GameService } from '../game.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css'],
})
export class ResultComponent implements OnInit {
  constructor(public game: GameService) {}

  ngOnInit(): void {
    this.game.gameClients.sort((a, b) => a.points - b.points);
  }

  getIconURL(client: Client): string {
    return `https://avatars.dicebear.com/api/big-ears-neutral/${client.nickname}.svg?r=50`;
  }
}
