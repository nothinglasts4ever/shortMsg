import {Injectable} from '@angular/core';
import 'rxjs/add/operator/map'
import {AuthenticationService} from "./auth.service";

@Injectable()
export class UserService {

  constructor(private authService: AuthenticationService) {
  }

  getUserDetails() {
    return this.authService.getUserDetails();
  }

}
