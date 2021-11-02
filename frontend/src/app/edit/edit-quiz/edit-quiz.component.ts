import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuestionImpl, QuizEntity } from 'src/app/api/models';
import { EditControllerService } from 'src/app/api/services';

@Component({
  templateUrl: './edit-quiz.component.html',
  styleUrls: ['./edit-quiz.component.css'],
})
export class EditQuizComponent implements OnInit {
  quiz?: QuizEntity;
  questionId?: number;
  maxQuestionIndex: number = 0;
  newOption?: string;
  loading: boolean = true;
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
          this.loading = false;
          if (this.quiz.questions.length > 0) {
            this.quiz.questions.forEach((e) => {
              if (e.questionIndex >= this.maxQuestionIndex) {
                this.maxQuestionIndex = e.questionIndex + 1;
              }
            });
          }
        },
        () => {
          this.router.navigate(['edit', id, 'authorize']);
        }
      );
  }

  createNewQuestion(): void {
    const newQuestion: QuestionImpl = {
      questionIndex: this.maxQuestionIndex++,
      time: this.quiz.quizSettings.defaultTime,
      task: {},
      answerOption: {
        optionAnswerOption: {
          options: new Array(),
        },
        rangeAnswerOption: {},
      },
    };
    this.quiz.questions.push(newQuestion);
    this.questionId = this.getQuestionIndex(newQuestion);
  }

  getQuestionIndex(question: QuestionImpl): number {
    return this.quiz.questions.findIndex(
      (e) => e.questionIndex === question.questionIndex
    );
  }

  save(): void {
    this.loading = true;
    this.api
      .saveQuiz({
        id: this.quiz.id,
        body: this.quiz,
      })
      .subscribe(
        (res) => {
          this.quiz = res;
          this.loading = false;
        },
        (error) => {
          console.log(JSON.stringify(error));
          this.router.navigate(['edit', this.quiz.id, 'authorize']);
        }
      );
  }

  addOption(): void {
    this.quiz.questions[
      this.questionId
    ].answerOption.optionAnswerOption.options.push(this.newOption);
    this.newOption = '';
  }

  deleteOption(option: string): void {
    let newList = Array<string>();
    this.quiz.questions[
      this.questionId
    ].answerOption.optionAnswerOption.options.forEach((e) => {
      if (e !== option) {
        newList.push(e);
      }
    });
    this.quiz.questions[
      this.questionId
    ].answerOption.optionAnswerOption.options = newList;
  }
}
