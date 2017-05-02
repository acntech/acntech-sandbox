import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NewTodoFormComponent } from "./new-todo-form.component";
import { Todo } from "../shared/model/todo";
import {FormsModule} from "@angular/forms";
import {TodosServiceStub} from "../../testing/todos-service-stub";
import {TodosService} from "../shared/todos.service";

let component: NewTodoFormComponent;
let fixture: ComponentFixture<NewTodoFormComponent>;
let todoService: TodosServiceStub;

describe('NewTodoFormComponent', () => {

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ FormsModule ],
      declarations: [ NewTodoFormComponent ],
      providers: [ { provide: TodosService, useClass: TodosServiceStub }]
    })
      .compileComponents()
      .then(createComponent);
  }));

  it('should create a form component and set fresh state', () => {
    expect(component).toBeTruthy();
    expect(component.submitted).toBeFalsy();
    expect(component.savedTodo).toBeUndefined();
    expect(component.saveError).toBeUndefined();
  });

  it('should define functions', () => {
    expect(typeof component.makeNewTodo).toBe('function');
    expect(typeof component.save).toBe('function');
  });

  it('should call TodoService on save', async(() => {
    component.todo.description = 'Test todo';
    component.save();
    fixture.detectChanges();

    fixture.whenStable().then(() => {
      fixture.detectChanges();
      expect(component.savedTodo.description).toBe('Test todo');
    });
  }));
});

function createComponent() {
  fixture = TestBed.createComponent(NewTodoFormComponent);
  component = fixture.componentInstance;
  todoService = fixture.debugElement.injector.get(TodosService) as any;
  fixture.detectChanges();

  return fixture.whenStable().then(() => {
    fixture.detectChanges();
  });
}
