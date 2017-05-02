import { Injectable } from '@angular/core';
import { Headers, RequestOptions, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {Todo} from "./model/todo";

@Injectable()
export class TodosService {

  private REST_URL = '/rest/todos';

  constructor(private http: Http) { }

  getAll(): Observable<Todo[]> {
    return this.http
      .get(this.REST_URL)
      .map(this.handleResponse)
      .catch(this.handleError);
  }

  create(todo: Todo): Observable<Todo> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return this.http.post(this.REST_URL, todo, options)
      .map(this.handleResponse)
      .catch(this.handleError);
  }

  private handleResponse (res: Response) {
    let body = res.json();
    return (body._embedded || {}).todos || body || {};
  }

  private handleError (error: Response | any) {
    console.log(error);
    let errorMsg = error.json();
    console.log(errorMsg);
    return Observable.throw(errorMsg);
  }
}
