import {Component, OnInit} from '@angular/core';
import {RestService} from "../rest.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Short Message Mailbox';
  currentUser: string;

  constructor(private restService: RestService) {
  }

  ngOnInit() {
    this.restService.getCurrentUser()
      .subscribe(person => {
        this.currentUser = person.firstName + ' ' + person.lastName;
      });
  }

}
