import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {InboxComponent} from './inbox/inbox.component';
import {PersonComponent} from './person/person.component';
import {OutboxComponent} from './outbox/outbox.component';
import {LoginComponent} from './login/login.component';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "./jwt-guard.guard";
import {AuthenticationService} from "./auth-service.service";
import {HomeComponent} from './home/home.component';
import {RestService} from "app/rest.service";

const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: ''}
];

@NgModule({
  declarations: [
    AppComponent,
    InboxComponent,
    PersonComponent,
    OutboxComponent,
    LoginComponent,
    HomeComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes, {enableTracing: true}),
    BrowserModule,
    FormsModule,
    HttpClientModule,
    HttpModule
  ],
  providers: [
    AuthGuard,
    AuthenticationService,
    RestService
  ],
  bootstrap: [AppComponent],
  exports: [RouterModule]
})

export class AppModule {
}
