import {Component, OnDestroy} from '@angular/core';
import {UserService} from "../user.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnDestroy {

  title = 'Short Message Mailbox';
  currentUser: string;

  constructor(public userService: UserService) {
  }

  ngOnDestroy() {
    this.userService.cleanUserDetails();
  }

}
