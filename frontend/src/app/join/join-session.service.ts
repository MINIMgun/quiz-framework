import { Injectable } from '@angular/core';
import { JoinSessionInfo } from '../api/models';

@Injectable({
  providedIn: 'root',
})
export class JoinSessionService {
  joinSessionInfo?: JoinSessionInfo;
  constructor() {}
}
