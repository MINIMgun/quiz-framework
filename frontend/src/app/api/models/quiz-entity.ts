/* tslint:disable */
/* eslint-disable */
import { QuestionImpl } from './question-impl';
import { QuizSettingsImpl } from './quiz-settings-impl';
export interface QuizEntity {
  author?: string;
  id?: string;
  questions?: Array<QuestionImpl>;
  quizName?: string;
  quizSettings?: QuizSettingsImpl;
}
