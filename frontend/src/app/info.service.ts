import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Info } from './api/models';
import { SettingsControllerService } from './api/services';

@Injectable({
  providedIn: 'root',
})
export class InfoService {
  constructor(private api: SettingsControllerService) {}

  getInfo(): Observable<Info> {
    return this.api.getCurrentInfo();
  }
}
