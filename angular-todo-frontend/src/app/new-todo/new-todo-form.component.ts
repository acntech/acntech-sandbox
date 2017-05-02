import { Component } from '@angular/core';
import { Todo } from '../shared/model/todo';
import { TodosService } from '../shared/todos.service';

@Component({
  selector: 'new-todo-form',
  templateUrl: './new-todo-form.component.html'
})
export class NewTodoFormComponent {
  todo = new Todo('', '');
  savedTodo: Todo;
  submitted = false;
  saveError: any;

  constructor( private todosService: TodosService ) {}

  save() {
    this.todosService.create(this.todo).subscribe(
      response => {
        this.savedTodo = response;
        this.submitted = true;
      },
      error => this.handleSaveError
    );
  }

  makeNewTodo() {
    this.todo = new Todo('','');
    this.submitted = false;
    this.saveError = null;
  }

  private handleSaveError(error) {
    console.log(error);
    this.saveError = error;
  }

  modelDebug(todo: Todo) {
    return JSON.stringify(todo);
  }
}
