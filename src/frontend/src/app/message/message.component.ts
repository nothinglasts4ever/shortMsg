import {Component, AfterViewInit, AfterContentInit} from '@angular/core';
import {Person} from "../person";
import {RestService} from "app/rest.service";
import {SendMessageRequest} from "../send-msg";
import {UserService} from "../user.service";

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements AfterViewInit, AfterContentInit {

  persons: Person[] = [];
  recipientId: number;
  msg: string;
  sent: string = undefined;

  constructor(private restService: RestService, private userService: UserService) {
  }

  ngAfterContentInit() {
    this.userService.setUserDetails();
  }

  ngAfterViewInit() {
    this.restService.getPersons()
      .subscribe(persons => {
        this.persons = persons.filter(person =>
          this.userService && this.userService.userDetails
            ? person.id != this.userService.userDetails.id
            : true);
      });
  }

  onSendMessage() {
    this.restService.sendMessage(new SendMessageRequest(this.recipientId, this.msg))
      .subscribe(message => {
        if (message) {
          this.sent = "Message sent";
        }
      });
  }

}
