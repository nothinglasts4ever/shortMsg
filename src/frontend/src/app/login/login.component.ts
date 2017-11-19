import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../auth.service";
import {Router} from "@angular/router";
import {UserService} from "../user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})

export class LoginComponent implements OnInit {

  model: any = {};
  loading = false;
  error = '';

  constructor(private router: Router, private authService: AuthenticationService, private userService: UserService) {
  }

  ngOnInit() {
    this.userService.cleanUserDetails();
    this.authService.logout();
  }

  login() {
    this.loading = true;
    this.authService.login(this.model.mobileId, this.model.password)
      .subscribe(result => {
        if (result === true) {
          this.router.navigate(['/message']);
        } else {
          this.error = 'Vale kasutajanimi või parool';
          this.loading = false;
        }
      });
  }

  close() {
    this.error = undefined;
  }

}
