import {ModuleWithProviders} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AppComponent} from "./app.component";
import {GreetingComponent} from "./greeting/greeting.component";

export const routes: Routes = [
  {path: '', redirectTo: '/', pathMatch: 'full'},
  {path: 'greeting', component: GreetingComponent},
  {path: '**', component: AppComponent}
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
