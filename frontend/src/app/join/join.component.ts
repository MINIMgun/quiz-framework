import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ExceptionEntity, JoinSessionInfo } from '../api/models';
import { LobbyControllerService } from '../api/services';
import { JoinSessionService } from './join-session.service';

@Component({
  selector: 'app-join',
  templateUrl: './join.component.html',
  styleUrls: ['./join.component.css'],
})
export class JoinComponent implements OnInit {
  joinUser: JoinSessionInfo = {};
  nicknameInUse: boolean = false;
  noSessionFound: boolean = false;
  sessionId?: string;

  constructor(
    private route: ActivatedRoute,
    private api: LobbyControllerService,
    private router: Router,
    private session: JoinSessionService,
    private cookie: CookieService
  ) {}

  ngOnInit(): void {
    this.getSessionId();
    this.joinUser.nickname = this.cookie.get('pref-nickname');
  }

  onSubmit(): void {
    this.nicknameInUse = false;
    this.noSessionFound = false;
    this.api
      .availableSession({
        body: this.joinUser,
      })
      .subscribe(
        () => {
          this.session.joinSessionInfo = this.joinUser;
          this.cookie.set('pref-nickname', this.joinUser.nickname, 365, '/');
          this.router.navigate(['play']);
        },
        (error) => {
          try {
            const exception: ExceptionEntity = error.error;
            if (exception.type === 'NoSessionException') {
              this.noSessionFound = true;
              this.sessionId = this.joinUser.sessionId;
            }
            if (exception.type === 'NicknameAlreadyInUseException') {
              this.nicknameInUse = true;
            }
          } catch (error) {}
        }
      );
  }

  getSessionId(): void {
    const sessionID = this.route.snapshot.paramMap.get('sessionID');
    if (sessionID != undefined) {
      this.joinUser.sessionId = sessionID;
    }
  }
}
