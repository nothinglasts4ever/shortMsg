import {Injectable} from '@angular/core';
import {Http,  Headers, RequestOptions, Response} from '@angular/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map'
import {Person} from "./person";

@Injectable()
export class AuthenticationService {

  public token: string;

  constructor(private http: Http) {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }

  login(mobileId: string, password: string): Observable<boolean> {
    return this.http.post('http://localhost:8080/login', JSON.stringify({mobileId: mobileId, password: password}))
      .map((response: Response) => {
        if (response && response.status == 200) {
          this.token = response.headers.get('Authorization');
          localStorage.setItem('currentUser', JSON.stringify({mobileId: mobileId, token: this.token}));
          return true;
        } else {
          return false;
        }
      });
  }

  getUserDetails(): Observable<Person> {
    return this.getRequest('http://localhost:8080/users/current');
  }

  private getRequest(url: string) {
    return this.http
      .get(url, this.createOptions())
      .map((response: Response) => response.json());
  }

  private createOptions() {
    let headers = new Headers({'Authorization': this.token});
    return new RequestOptions({headers: headers});
  }

  logout(): void {
    this.token = null;
    localStorage.removeItem('currentUser');
  }

}
