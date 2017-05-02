import { Todo } from "../app/shared/model/todo";
import {Observable} from "rxjs/Observable";


export class TodosServiceStub {

  testTodo = new Todo('');

  getAll(): Observable<Todo[]>{
    const todoList = [this.testTodo];
    return Observable.create((observer)=> {
      observer.onNext(todoList);
      observer.onComplete();
    });
  }

  create(todo: Todo):Observable<Todo> {
    return Observable.create((observer) => {
      observer.next(todo);
      observer.complete();
    });
  }
}
