import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import "rxjs/Rx";
import {Observable} from "rxjs/Observable";
import {Greeting} from "../model/greeting";

@Injectable()
export class GreetingService {

  constructor(private http: Http) {
  }

  getGreeting(name: string): Observable<Greeting> {
    return this.http.get('/api/greetings/' + name)
      .map((response: Response) => response.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  findGreetings(): Observable<Greeting[]> {
    return this.http.get('/api/greetings')
      .map((response: Response) => response.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }
}
