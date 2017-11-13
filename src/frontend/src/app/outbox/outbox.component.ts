import {Component, OnInit} from '@angular/core';
import {RestService} from "../rest.service";

@Component({
  selector: 'app-outbox',
  templateUrl: './outbox.component.html',
  styleUrls: ['./outbox.component.css']
})
export class OutboxComponent implements OnInit {

  messages = [];

  constructor(private restService: RestService) {
  }

  ngOnInit() {
    this.restService.getOutbox()
      .subscribe(messages => {
        this.messages = messages;
      });
  }

}
