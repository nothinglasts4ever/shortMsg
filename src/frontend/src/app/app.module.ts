import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {HttpModule} from '@angular/http';
import {AppComponent} from './app.component';
import {InboxComponent} from './inbox/inbox.component';
import {MessageComponent} from './message/message.component';
import {OutboxComponent} from './outbox/outbox.component';
import {LoginComponent} from './login/login.component';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "./jwt.guard";
import {AuthenticationService} from "./auth.service";
import {HomeComponent} from './home/home.component';
import {RestService} from "app/rest.service";
import {UserService} from "./user.service";

const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: ''}
];

@NgModule({
  declarations: [
    AppComponent,
    InboxComponent,
    MessageComponent,
    OutboxComponent,
    LoginComponent,
    HomeComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    FormsModule,
    HttpClientModule,
    HttpModule
  ],
  providers: [
    AuthGuard,
    AuthenticationService,
    RestService,
    UserService
  ],
  bootstrap: [AppComponent],
  exports: [RouterModule]
})

export class AppModule {
}
