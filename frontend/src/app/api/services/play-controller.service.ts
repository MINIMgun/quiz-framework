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

import { SessionState } from '../models/session-state';

@Injectable({
  providedIn: 'root',
})
export class PlayControllerService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation getCurrentSessionState
   */
  static readonly GetCurrentSessionStatePath = '/play/get/session/{sessionId}/current-state';

  /**
   * Get the current state of the session.
   *
   * This method gets the current session state of the session with the specified sessionId.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getCurrentSessionState()` instead.
   *
   * This method doesn't expect any request body.
   */
  getCurrentSessionState$Response(params: {
    sessionId: string;
  }): Observable<StrictHttpResponse<SessionState>> {

    const rb = new RequestBuilder(this.rootUrl, PlayControllerService.GetCurrentSessionStatePath, 'get');
    if (params) {
      rb.path('sessionId', params.sessionId, {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<SessionState>;
      })
    );
  }

  /**
   * Get the current state of the session.
   *
   * This method gets the current session state of the session with the specified sessionId.
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getCurrentSessionState$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getCurrentSessionState(params: {
    sessionId: string;
  }): Observable<SessionState> {

    return this.getCurrentSessionState$Response(params).pipe(
      map((r: StrictHttpResponse<SessionState>) => r.body as SessionState)
    );
  }

}
