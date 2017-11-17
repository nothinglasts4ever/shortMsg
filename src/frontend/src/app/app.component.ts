import {Component} from '@angular/core';
import {UserService} from "./user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {

  title = 'Short Message Mailbox';

  constructor(public userService: UserService) {
  }

}
