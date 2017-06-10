import {Component} from "@angular/core";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  main_title = 'Simple Angular Restful Webapp';
  navbar_brand = 'Angular 4';
  navbar_link_home = 'Home';
  navbar_link_greeting = 'Greetings';
}
