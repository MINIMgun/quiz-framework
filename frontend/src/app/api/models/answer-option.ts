/* tslint:disable */
/* eslint-disable */
import { OptionAnswerOption } from './option-answer-option';
import { RangeAnswerOption } from './range-answer-option';
export interface AnswerOption {
  answerOptionType?: 'RANGE' | 'OPTION' | 'BUZZER';
  id?: number;
  optionAnswerOption?: OptionAnswerOption;
  rangeAnswerOption?: RangeAnswerOption;
}
