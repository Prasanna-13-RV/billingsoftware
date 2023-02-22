
import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  username: string = "";
  email: string = "";
  password: string = "";
  rePassword: string = "";

  constructor(public auth: AuthService) { }

  register() {
    if (this.password == this.rePassword) {
      if (this.email == "") {
        alert('Please enter your email');
        return;
      }
      else if (this.password == "") {
        alert('Please enter your password');
        return;
      }
      this.auth.register(this.email, this.password);

    }
    else {
      alert("Password and Retype Password should be equal...")
    }


    this.email = "";
    this.password = "";

  }




}
