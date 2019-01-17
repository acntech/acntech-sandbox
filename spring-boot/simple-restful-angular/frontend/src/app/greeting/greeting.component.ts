import {Component, OnInit} from "@angular/core";
import {GreetingService} from "./service/greeting.service";
import {Greeting} from "./model/greeting";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'greeting',
  templateUrl: './greeting.component.html',
  styleUrls: ['./greeting.component.css']
})
export class GreetingComponent implements OnInit {

  greetingForm: FormGroup;
  post: any;
  greeting: Greeting;

  constructor(private formBuilder: FormBuilder, private greetingService: GreetingService) {
    this.greetingForm = formBuilder.group({
      'name': [null, Validators.compose([
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(100)
      ])]
    });
  }

  ngOnInit() {
  }

  greetingFormPost(post) {
    this.getGreeting(post.name);
  }

  getGreeting(name: string) {
    this.greetingService.getGreeting(name)
      .subscribe((data: Greeting) => this.greeting = data);
  }
}
