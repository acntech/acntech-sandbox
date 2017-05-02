import { Component, OnInit } from '@angular/core';
import { TodosService } from "../shared/todos.service";
import { Observable } from "rxjs";
import { Todo } from "../shared/model/todo";
import { TodoFilter } from "../shared/model/filter";

@Component({
  templateUrl: 'list.component.html',
  providers: [ TodosService ]
})

export class ListComponent implements OnInit {

  todos: Todo[];
  filter = new TodoFilter('', false);
  errorMsg: Observable<string>;

  constructor( private todosService: TodosService ) {}

  ngOnInit() {
    this.getTodos();
  }

  getTodos() {
    this.todosService.getAll().subscribe(
      response => this.todos = response,
      error => this.errorMsg = error
    );
  }
}
