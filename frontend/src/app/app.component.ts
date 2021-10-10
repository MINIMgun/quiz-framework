import { Component, OnInit } from '@angular/core';
import { InfoService } from './info.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title: string;

  constructor(public infoService: InfoService) { }

  ngOnInit(): void {
    this.getInfo();
  }

  getInfo(): void {
    this.infoService.getInfo().subscribe(e => this.title = e.title);
  }
}
