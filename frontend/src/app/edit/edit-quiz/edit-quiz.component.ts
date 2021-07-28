import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
    private api: EditControllerService
  ) {}

  ngOnInit(): void {
    this.api
      .editQuiz({
        id: this.route.snapshot.paramMap.get('id'),
      })
      .subscribe((res) => {
        this.quiz = res;
      });
  }
}
