import {Component, OnInit} from '@angular/core';
import {Person} from "../person";
import {RestService} from "app/rest.service";
import {SendMessageRequest} from "../send-msg";

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  persons: Person[] = [];
  recipientId: number;
  msg: string;
  sent: string = undefined;

  constructor(private restService: RestService) {
  }

  ngOnInit() {
    this.restService.getPersons()
      .subscribe(persons => {
        this.persons = persons;
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
