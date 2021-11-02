/* tslint:disable */
/* eslint-disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { Info } from '../models/info';

@Injectable({
  providedIn: 'root',
})
export class SettingsControllerService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation getInfoById
   */
  static readonly GetInfoByIdPath = '/settings/get/info/id={id}';

  /**
   * Get a Info object by its id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getInfoById()` instead.
   *
   * This method doesn't expect any request body.
   */
  getInfoById$Response(params: {
    id: number;
  }): Observable<StrictHttpResponse<Info>> {

    const rb = new RequestBuilder(this.rootUrl, SettingsControllerService.GetInfoByIdPath, 'get');
    if (params) {
      rb.path('id', params.id, {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Info>;
      })
    );
  }

  /**
   * Get a Info object by its id.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getInfoById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getInfoById(params: {
    id: number;
  }): Observable<Info> {

    return this.getInfoById$Response(params).pipe(
      map((r: StrictHttpResponse<Info>) => r.body as Info)
    );
  }

  /**
   * Path part for operation getCurrentInfo
   */
  static readonly GetCurrentInfoPath = '/settings/get/info/current';

  /**
   * Get the most recent information.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getCurrentInfo()` instead.
   *
   * This method doesn't expect any request body.
   */
  getCurrentInfo$Response(params?: {
  }): Observable<StrictHttpResponse<Info>> {

    const rb = new RequestBuilder(this.rootUrl, SettingsControllerService.GetCurrentInfoPath, 'get');
    if (params) {
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Info>;
      })
    );
  }

  /**
   * Get the most recent information.
   *
   *
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getCurrentInfo$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getCurrentInfo(params?: {
  }): Observable<Info> {

    return this.getCurrentInfo$Response(params).pipe(
      map((r: StrictHttpResponse<Info>) => r.body as Info)
    );
  }

}
