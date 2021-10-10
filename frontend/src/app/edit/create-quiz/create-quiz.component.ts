import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { QuizCreateInfo } from 'src/app/api/models';
import { EditControllerService } from 'src/app/api/services';

@Component({
  templateUrl: './create-quiz.component.html',
  styleUrls: ['./create-quiz.component.css'],
})
export class CreateQuizComponent implements OnInit {
  createInfo?: QuizCreateInfo;
  constructor(
    private cookie: CookieService,
    private api: EditControllerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const author = this.cookie.get('pref-nickname');
    this.createInfo = {
      author: author,
      editPassword: '',
      quizName: '',
      quizSettings: {
        defaultTime: 60,
        pointsToAdd: 1,
        pointsToRemove: 1,
        removePointsForInvalidAnswers: false,
      },
    };
  }

  create(): void {
    this.cookie.set('pref-nickname', this.createInfo.author, 365, '/');
    this.api
      .createNewQuiz({
        body: this.createInfo,
      })
      .subscribe((res) => {
        this.router.navigate(['edit', res.id]);
      });
  }
}
