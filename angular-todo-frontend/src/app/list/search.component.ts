import { Component, Input } from '@angular/core';
import { TodoFilter } from '../shared/model/filter';

@Component({
  selector: '[data-search-todos]',
  template: `
    <form>
      <div class="form-group">
        <label>Search</label>
        <input class="form-control" type="search" name="query" autocomplete="off" [(ngModel)]="filter.query">
      </div>
      <div class="checkbox">
        <label>
          <input type="checkbox" name="done" [(ngModel)]="filter.isDone">
          <span>Hide done todos</span>
        </label>
      </div>
    </form>
  `
})

export class SearchTodosComponent {
  @Input() filter: TodoFilter;
  constructor() {
  }
}
