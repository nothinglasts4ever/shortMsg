import {Component, AfterViewInit} from '@angular/core';
import {RestService} from "../rest.service";

@Component({
  selector: 'app-outbox',
  templateUrl: './outbox.component.html',
  styleUrls: ['./outbox.component.css']
})
export class OutboxComponent implements AfterViewInit {

  messages = [];

  constructor(private restService: RestService) {
  }

  ngAfterViewInit() {
    this.restService.getOutbox()
      .subscribe(messages => {
        this.messages = messages;
      });
  }

}
