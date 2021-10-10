import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { QuizEntityInformation } from 'src/app/api/models';
import {
  EditControllerService,
  LobbyControllerService,
} from 'src/app/api/services';

@Component({
  templateUrl: './quiz-overview.component.html',
  styleUrls: ['./quiz-overview.component.css'],
})
export class QuizOverviewComponent implements OnInit {
  quizInformation?: Array<QuizEntityInformation>;
  authorControll: FormControl = new FormControl(null);
  constructor(
    private api: EditControllerService,
    private router: Router,
    private lobbyApi: LobbyControllerService
  ) {}

  ngOnInit(): void {
    this.authorControll.valueChanges
      .pipe(debounceTime(400), distinctUntilChanged())
      .subscribe((e) => {
        this.updateList(e);
      });
    this.updateList();
  }

  updateList(author: string = null) {
    if (author || author != '') {
      this.api
        .getAll({
          author: author,
        })
        .subscribe(
          (res) => {
            this.quizInformation = res;
          },
          (error) => {
            console.log(error.error);
          }
        );
    } else {
      this.api.getAll().subscribe(
        (res) => {
          this.quizInformation = res;
        },
        (error) => {
          console.log(error.error);
        }
      );
    }
  }

  editQuiz(quiz: QuizEntityInformation): void {
    const tokenIds = JSON.parse(
      sessionStorage.getItem('edit_tokens')
    ) as String[];
    if (tokenIds && tokenIds.indexOf(quiz.quizEntityId) > -1) {
      this.router.navigate(['edit', quiz.quizEntityId]);
    } else {
      this.router.navigate(['edit', quiz.quizEntityId, 'authorize']);
    }
  }

  play(quiz: QuizEntityInformation): void {
    this.lobbyApi
      .createSession({
        quizId: quiz.quizEntityId,
      })
      .subscribe((res) => {
        this.router.navigate(['join', res.sessionId]);
      });
  }
}
