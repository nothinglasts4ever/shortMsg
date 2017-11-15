import {Component, AfterViewInit} from '@angular/core';
import {RestService} from "../rest.service";

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements AfterViewInit {

  messages = [];

  constructor(private restService: RestService) {
  }

  ngAfterViewInit() {
    this.getInboxMessages();
  }

  markAsRead(messageId: number, read: boolean) {
    if (read == false) {
      this.restService.markAsRead(messageId)
        .subscribe(this.getInboxMessages);
    }
  }

  getInboxMessages = () => {
    this.restService.getInbox()
      .subscribe(messages => {
        this.messages = messages;
      });
  }

}
