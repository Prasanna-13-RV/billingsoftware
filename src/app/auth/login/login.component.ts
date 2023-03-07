import { Component, ViewChild, ElementRef } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  email: string = "";
  password: string = "";

  constructor(public auth: AuthService) { }

  login() {
    if (this.email == "") {
      alert('Please enter your email');
      return;
    }
    if (this.password == "") {
      alert('Please enter your password');
      return;
    }
    this.auth.login(this.email, this.password)
    this.email = "";
    this.password = "";
  }
}
