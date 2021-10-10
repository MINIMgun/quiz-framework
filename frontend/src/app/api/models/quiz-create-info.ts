/* tslint:disable */
/* eslint-disable */
import { QuizSettingsImpl } from './quiz-settings-impl';
export interface QuizCreateInfo {
  author: string;
  editPassword: string;
  quizName: string;
  quizSettings?: QuizSettingsImpl;
}
