import {Injectable} from '@angular/core';
import 'rxjs/add/operator/map'
import {RestService} from "./rest.service";
import {Person} from "./person";

@Injectable()
export class UserService {

  public userDetails: Person;

  constructor(private restService: RestService) {
  }

  setUserDetails() {
    if (!this.userDetails) {
      return this.restService.getCurrentUser()
        .subscribe(person => {
          this.userDetails = person;
        });
    }
  }

  cleanUserDetails() {
    this.userDetails = undefined;
  }

}
