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

import { JoinSessionInfo } from '../models/join-session-info';

@Injectable({
  providedIn: 'root',
})
export class LobbyControllerService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation createSession
   */
  static readonly CreateSessionPath = '/play/create-session/{quizId}';

  /**
   * Create a new Session.
   *
   * This method creates a new Session with the specified Quiz by quizId.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `createSession()` instead.
   *
   * This method doesn't expect any request body.
   */
  createSession$Response(params: {
    quizId: string;
  }): Observable<StrictHttpResponse<JoinSessionInfo>> {

    const rb = new RequestBuilder(this.rootUrl, LobbyControllerService.CreateSessionPath, 'post');
    if (params) {
      rb.path('quizId', params.quizId, {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<JoinSessionInfo>;
      })
    );
  }

  /**
   * Create a new Session.
   *
   * This method creates a new Session with the specified Quiz by quizId.
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `createSession$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  createSession(params: {
    quizId: string;
  }): Observable<JoinSessionInfo> {

    return this.createSession$Response(params).pipe(
      map((r: StrictHttpResponse<JoinSessionInfo>) => r.body as JoinSessionInfo)
    );
  }

  /**
   * Path part for operation availableSession
   */
  static readonly AvailableSessionPath = '/play/available-session';

  /**
   * Checks if the JoinSessionInfo is valid.
   *
   * This method checks if the provided JoinSessionInfo is valid, if it is returns a 200 - OK status code a 4xx otherwise.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `availableSession()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  availableSession$Response(params: {
    body: JoinSessionInfo
  }): Observable<StrictHttpResponse<{  }>> {

    const rb = new RequestBuilder(this.rootUrl, LobbyControllerService.AvailableSessionPath, 'post');
    if (params) {
      rb.body(params.body, 'application/json');
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<{  }>;
      })
    );
  }

  /**
   * Checks if the JoinSessionInfo is valid.
   *
   * This method checks if the provided JoinSessionInfo is valid, if it is returns a 200 - OK status code a 4xx otherwise.
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `availableSession$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  availableSession(params: {
    body: JoinSessionInfo
  }): Observable<{  }> {

    return this.availableSession$Response(params).pipe(
      map((r: StrictHttpResponse<{  }>) => r.body as {  })
    );
  }

}
