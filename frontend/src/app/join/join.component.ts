import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JoinUser } from '../join-user';

@Component({
  selector: 'app-join',
  templateUrl: './join.component.html',
  styleUrls: ['./join.component.css']
})
export class JoinComponent implements OnInit {

  joinUser = new JoinUser;
  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getSessionId();
  }

  onSubmit(): void {

  }
  getSessionId(): void {
    const sessionID = this.route.snapshot.paramMap.get('sessionID');
    if(sessionID != undefined) {
      this.joinUser.sessionID=sessionID;
    }
  }
}
