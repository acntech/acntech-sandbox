import {Component, OnInit} from "@angular/core";
import {GreetingService} from "./service/greeting.service";
import {Greeting} from "./model/greeting";

@Component({
  selector: 'greeting-list',
  templateUrl: './greeting-list.component.html',
  styleUrls: ['./greeting.component.css']
})
export class GreetingListComponent implements OnInit {

  greetings: Greeting[] = [];

  constructor(private greetingService: GreetingService) {
  }

  ngOnInit() {
    this.greetingService.findGreetings()
      .subscribe((data: Greeting[]) => this.greetings = data);
  }
}
