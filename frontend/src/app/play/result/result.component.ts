import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/api/models';
import { GameService } from '../game.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css'],
})
export class ResultComponent implements OnInit {
  result: Client[];
  constructor(public game: GameService) {}

  ngOnInit(): void {
    this.result = this.game.gameClients.sort((a, b) => b.points - a.points);
  }

  getIconURL(client: Client): string {
    return `https://avatars.dicebear.com/api/big-ears-neutral/${client.nickname}.svg?r=50`;
  }
}
