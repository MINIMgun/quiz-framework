/* tslint:disable */
/* eslint-disable */
import { Injectable } from '@angular/core';
import * as config from '../../assets/config/config.json';

/**
 * Global configuration
 */
@Injectable({
  providedIn: 'root',
})
export class ApiConfiguration {
  rootUrl: string = config.apiUrl + config.apiBasePath;
}

/**
 * Parameters for `ApiModule.forRoot()`
 */
export interface ApiConfigurationParams {
  rootUrl?: string;
}
