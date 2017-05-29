import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import "rxjs/Rx";

@Injectable()
export class GreetingService {

  constructor(private http: Http) {
  }

  getGreeting(name: string) {
    return this.http.get('api/greeting/' + name)
      .map((response: Response) => {
        return response.json();
      });
  }

  findGreetings() {
    return this.http.get('api/greeting')
      .map((response: Response) => {
        return response.json();
      });
  }
}
