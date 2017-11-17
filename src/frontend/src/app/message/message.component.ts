import {Component, AfterViewInit, AfterContentInit} from '@angular/core';
import {Person} from "../person";
import {RestService} from "app/rest.service";
import {SendMessageRequest} from "../send-msg";
import {UserService} from "../user.service";

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html'
})
export class MessageComponent implements AfterContentInit, AfterViewInit {

  persons: Person[] = [];
  recipientId: number;
  msg: string;
  sent: string = undefined;
  error: string = undefined;

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
    if (!this.recipientId || !this.msg) {
      this.closeWell();
      this.error = 'Person or message cannot be empty';
    } else {
      this.restService.sendMessage(new SendMessageRequest(this.recipientId, this.msg))
        .subscribe(message => {
          if (message) {
            this.closeWrong();
            this.sent = "Message sent";
            this.msg = undefined;
          }
        });
    }
  }

  closeWell() {
    this.sent = undefined;
  }

  closeWrong() {
    this.error = undefined;
  }

}
