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

import { EditPassword } from '../models/edit-password';
import { QuizCreateInfo } from '../models/quiz-create-info';
import { QuizEntity } from '../models/quiz-entity';
import { QuizEntityInformation } from '../models/quiz-entity-information';

@Injectable({
  providedIn: 'root',
})
export class EditControllerService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation saveQuiz
   */
  static readonly SaveQuizPath = '/edit/saveQuiz/id={id}';

  /**
   * Update a QuizEntity.
   *
   * This method updates the QuizEntity with the provided id, with the given QuizEntity if the user has a valid editToken.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveQuiz()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveQuiz$Response(params: {
    id: string;
    body: QuizEntity
  }): Observable<StrictHttpResponse<QuizEntity>> {

    const rb = new RequestBuilder(this.rootUrl, EditControllerService.SaveQuizPath, 'post');
    if (params) {
      rb.path('id', params.id, {});
      rb.body(params.body, 'application/json');
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<QuizEntity>;
      })
    );
  }

  /**
   * Update a QuizEntity.
   *
   * This method updates the QuizEntity with the provided id, with the given QuizEntity if the user has a valid editToken.
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `saveQuiz$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveQuiz(params: {
    id: string;
    body: QuizEntity
  }): Observable<QuizEntity> {

    return this.saveQuiz$Response(params).pipe(
      map((r: StrictHttpResponse<QuizEntity>) => r.body as QuizEntity)
    );
  }

  /**
   * Path part for operation editQuiz
   */
  static readonly EditQuizPath = '/edit/getQuiz/id={id}';

  /**
   * Retrieve a QuizEntity to edit.
   *
   * This method is used to provide a QuizEntity for users that want to edit it. For that they need to authorize with the editPassword of that Quiz. The authorization is checked and a cookie is returned with a editToken for this QuizEntity.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `editQuiz()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  editQuiz$Response(params: {
    id: string;
    body?: EditPassword
  }): Observable<StrictHttpResponse<QuizEntity>> {

    const rb = new RequestBuilder(this.rootUrl, EditControllerService.EditQuizPath, 'post');
    if (params) {
      rb.path('id', params.id, {});
      rb.body(params.body, 'application/json');
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<QuizEntity>;
      })
    );
  }

  /**
   * Retrieve a QuizEntity to edit.
   *
   * This method is used to provide a QuizEntity for users that want to edit it. For that they need to authorize with the editPassword of that Quiz. The authorization is checked and a cookie is returned with a editToken for this QuizEntity.
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `editQuiz$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  editQuiz(params: {
    id: string;
    body?: EditPassword
  }): Observable<QuizEntity> {

    return this.editQuiz$Response(params).pipe(
      map((r: StrictHttpResponse<QuizEntity>) => r.body as QuizEntity)
    );
  }

  /**
   * Path part for operation createNewQuiz
   */
  static readonly CreateNewQuizPath = '/edit/createNewQuiz';

  /**
   * Create a new QuizEntity.
   *
   * This method creates a new QuizEntity with the specified QuizCreateInfo. It also sets a Cookie with the editToken.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `createNewQuiz()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createNewQuiz$Response(params: {
    body: QuizCreateInfo
  }): Observable<StrictHttpResponse<QuizEntity>> {

    const rb = new RequestBuilder(this.rootUrl, EditControllerService.CreateNewQuizPath, 'post');
    if (params) {
      rb.body(params.body, 'application/json');
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<QuizEntity>;
      })
    );
  }

  /**
   * Create a new QuizEntity.
   *
   * This method creates a new QuizEntity with the specified QuizCreateInfo. It also sets a Cookie with the editToken.
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `createNewQuiz$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createNewQuiz(params: {
    body: QuizCreateInfo
  }): Observable<QuizEntity> {

    return this.createNewQuiz$Response(params).pipe(
      map((r: StrictHttpResponse<QuizEntity>) => r.body as QuizEntity)
    );
  }

  /**
   * Path part for operation getAll
   */
  static readonly GetAllPath = '/edit/getAllQuizInformation';

  /**
   * Get a list of all QuizEntityInformation.
   *
   * Returns a list of all QuizEntityInformation's with the given author.
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAll()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAll$Response(params?: {
    author?: string;
  }): Observable<StrictHttpResponse<Array<QuizEntityInformation>>> {

    const rb = new RequestBuilder(this.rootUrl, EditControllerService.GetAllPath, 'get');
    if (params) {
      rb.query('author', params.author, {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<QuizEntityInformation>>;
      })
    );
  }

  /**
   * Get a list of all QuizEntityInformation.
   *
   * Returns a list of all QuizEntityInformation's with the given author.
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getAll$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAll(params?: {
    author?: string;
  }): Observable<Array<QuizEntityInformation>> {

    return this.getAll$Response(params).pipe(
      map((r: StrictHttpResponse<Array<QuizEntityInformation>>) => r.body as Array<QuizEntityInformation>)
    );
  }

}
