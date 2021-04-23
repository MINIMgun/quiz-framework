import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Info } from './info';
import { INFO } from './mock-info';

@Injectable({
  providedIn: 'root'
})
export class InfoService {

  constructor() { }

  getInfo(): Observable<Info> {
    return of(INFO);
  }
}
