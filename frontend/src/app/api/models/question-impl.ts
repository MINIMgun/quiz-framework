/* tslint:disable */
/* eslint-disable */
import { AnswerOption } from './answer-option';
import { TaskImpl } from './task-impl';
export interface QuestionImpl {
  answerOption?: AnswerOption;
  id?: number;
  questionIndex?: number;
  task?: TaskImpl;
  time?: number;
}
