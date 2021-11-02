/* tslint:disable */
/* eslint-disable */
import { Client } from './client';
import { ClientResponse } from './client-response';
import { QuestionImpl } from './question-impl';
import { Timer } from './timer';
export interface SessionState {
  buzzerClient?: Client;
  clientResponses?: Array<ClientResponse>;
  currentQuestion?: QuestionImpl;
  currentQuestionIndex?: number;
  currentQuestionState?: 'ANSWERING' | 'SHOWING_RESPONSES' | 'RESULTS';
  gameState?: 'LOBBY' | 'IN_GAME' | 'RESULT';
  timer?: Timer;
}
