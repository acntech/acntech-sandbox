import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
// Modules
import {routing} from "./app.routes";
// Declarations
import {AppComponent} from "./app.component";
import {GreetingComponent} from "./greeting/greeting.component";
import {GreetingListComponent} from "./greeting/greeting-list.component";
// Providers
import {GreetingService} from "./greeting/service/greeting.service";

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  declarations: [
    AppComponent,
    GreetingComponent,
    GreetingListComponent
  ],
  providers: [
    GreetingService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
