import {Component, OnInit} from '@angular/core';
import {RestService} from "../rest.service";

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

  messages = [];

  constructor(private restService: RestService) {
  }

  ngOnInit() {
    this.restService.getInbox()
      .subscribe(messages => {
        this.messages = messages;
      });
  }

}
