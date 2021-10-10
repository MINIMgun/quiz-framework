import { Component, OnInit } from '@angular/core';
import { Client } from 'src/app/api/models/client';
import { GameService } from '../game.service';
import { SocketClientService } from '../socket-client.service';
import KUTE from 'kute.js';

@Component({
  selector: 'app-lobby',
  templateUrl: './lobby.component.html',
  styleUrls: ['./lobby.component.css'],
})
export class LobbyComponent implements OnInit {
  urlBasePath: string;
  constructor(public game: GameService, private socket: SocketClientService) {}

  ngOnInit(): void {
    this.urlBasePath = window.location.href.replace('play', 'join/');
    const tween = KUTE.fromTo(
      '#blob-1',
      { path: '#blob-1' },
      { path: '#blob-2' },
      { repeat: 999, duration: 5000, yoyo: true }
    );

    tween.start();
  }

  getIconURL(client: Client): string {
    return `https://avatars.dicebear.com/api/big-ears-neutral/${client.nickname}.svg?r=50`;
  }

  copyMessage(val: string) {
    if (!navigator.clipboard) {
      const selBox = document.createElement('textarea');
      selBox.style.position = 'fixed';
      selBox.style.left = '0';
      selBox.style.top = '0';
      selBox.style.opacity = '0';
      selBox.value = val;
      document.body.appendChild(selBox);
      selBox.focus();
      selBox.select();
      document.execCommand('copy');
      document.body.removeChild(selBox);
    } else {
      navigator.clipboard.writeText(val);
    }
  }

  startSession(): void {
    this.socket.send(`/play/session/${this.game.sessionId}/next`, {});
  }
}
