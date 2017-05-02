import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { routing, appRoutingProviders } from './app.routing';
import { ListComponent, SearchTodosComponent } from './list'
import { FrontpageComponent } from './frontpage/frontpage.component';
import { NewTodoComponent } from './new-todo/new-todo.component';
import { NewTodoFormComponent } from './new-todo/new-todo-form.component';
import { FilterPipe, SearchPipe } from './pipes';
import { TRANSLATION_PROVIDERS, TranslatePipe, TranslateService } from './translate';

@NgModule({
  declarations: [
    AppComponent,
    SearchTodosComponent,
    ListComponent,
    FrontpageComponent,
    NewTodoComponent,
    NewTodoFormComponent,
    FilterPipe,
    SearchPipe,
    TranslatePipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [
    appRoutingProviders,
    TRANSLATION_PROVIDERS,
    TranslateService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
