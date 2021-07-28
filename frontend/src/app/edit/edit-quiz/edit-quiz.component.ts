import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizEntity } from 'src/app/api/models';
import { EditControllerService } from 'src/app/api/services';

@Component({
  templateUrl: './edit-quiz.component.html',
  styleUrls: ['./edit-quiz.component.css'],
})
export class EditQuizComponent implements OnInit {
  quiz?: QuizEntity;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private api: EditControllerService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.api
      .editQuiz({
        id: id,
      })
      .subscribe(
        (res) => {
          this.quiz = res;
        },
        () => {
          this.router.navigate(['edit', id, 'authorize']);
        }
      );
  }
}
