import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions, Response} from '@angular/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/map'
import {AuthenticationService} from "./auth.service";
import {Person} from "./person";
import {SendMessageRequest} from "./send-msg";

@Injectable()
export class RestService {

  constructor(private http: Http, private authService: AuthenticationService) {
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

  sendMessage(message: SendMessageRequest): Observable<Object[]> {
    return this.postRequest('http://localhost:8080/messages/send', message);
  }

  private getRequest(url: string) {
    return this.http
      .get(url, this.createOptions())
      .map((response: Response) => response.json());
  }

  private postRequest(url: string, body: any) {
    return this.http
      .post(url, body, this.createOptions())
      .map((response: Response) => response.json());
  }

  private createOptions() {
    let headers = new Headers({'Authorization': this.authService.token});
    return new RequestOptions({headers: headers});
  }

}
