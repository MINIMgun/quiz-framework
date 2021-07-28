import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { QuizEntityInformation } from 'src/app/api/models';
import { EditControllerService } from 'src/app/api/services';

@Component({
  templateUrl: './quiz-overview.component.html',
  styleUrls: ['./quiz-overview.component.css'],
})
export class QuizOverviewComponent implements OnInit {
  quizInformation?: Array<QuizEntityInformation>;
  authorControll: FormControl = new FormControl(null);
  constructor(
    private api: EditControllerService,
    private cookie: CookieService,
    private router: Router
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
    if (this.cookie.check(`edit_token_${quiz.quizEntityId}`)) {
      this.router.navigate(['edit', quiz.quizEntityId]);
    } else {
      this.router.navigate(['edit', quiz.quizEntityId, 'authorize']);
    }
  }
}
