import {Component, OnInit} from '@angular/core';
import {Person} from "../person";
import {RestService} from "app/rest.service";

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {

  persons: Person[] = [];

  constructor(private restService: RestService) {
  }

  ngOnInit() {
    this.restService.getPersons()
      .subscribe(persons => {
        this.persons = persons;
      });
  }

}
