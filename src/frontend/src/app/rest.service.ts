import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions, Response} from '@angular/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map'
import {AuthenticationService} from "./auth-service.service";
import {Person} from "./person";

@Injectable()
export class RestService {

  constructor(private http: Http, private authenticationService: AuthenticationService) {
  }

  getPersons(): Observable<Person[]> {
    return this.getRequest('http://localhost:8080/users');
  }

  getInbox(): Observable<Object[]> {
    return this.getRequest('http://localhost:8080/messages/inbox');
  }

  getOutbox(): Observable<Object[]> {
    return this.getRequest('http://localhost:8080/messages/outbox');
  }

  private getRequest(url: string) {
    let headers = new Headers({'Authorization': this.authenticationService.token});
    let options = new RequestOptions({headers: headers});

    return this.http
      .get(url, options)
      .map((response: Response) => response.json());
  }

}
