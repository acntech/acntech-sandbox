import {Component, OnInit} from "@angular/core";
import {GreetingService} from "./greeting.service";

@Component({
  selector: 'greeting',
  templateUrl: './greeting.component.html',
  styleUrls: ['./greeting.component.css']
})
export class GreetingComponent implements OnInit {

  greetings;

  constructor(private greetingService: GreetingService) {
  }

  ngOnInit() {
    this.greetings = this.greetingService.findGreetings();
  }
}
