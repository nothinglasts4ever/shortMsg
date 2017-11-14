import {Component, OnInit} from '@angular/core';
import {UserService} from "../user.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Short Message Mailbox';
  currentUser: string;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getUserDetails()
      .subscribe(person => {
        this.currentUser = person.firstName + ' ' + person.lastName;
      });
  }

}
