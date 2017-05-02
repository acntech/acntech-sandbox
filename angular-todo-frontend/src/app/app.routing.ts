import { RouterModule, Routes } from '@angular/router';
import { ListComponent } from './list/list.component';
import { FrontpageComponent } from './frontpage/frontpage.component';
import { NewTodoComponent } from './new-todo/new-todo.component';

const appRoutes: Routes = [
  { path: 'new', component: NewTodoComponent },
  { path: 'list', component: ListComponent },
  { path: '', component: FrontpageComponent, pathMatch: 'full' }
];

export const appRoutingProviders: any[] = [];
export const routing = RouterModule.forRoot(appRoutes);
