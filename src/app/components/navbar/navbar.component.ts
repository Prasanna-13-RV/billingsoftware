import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  getToken: boolean = false;

  constructor(private auth: AuthService) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('user')!)
    console.log(this.user);
    
    this.getToken = Boolean(localStorage.getItem('token')!)
    console.log(this.getToken);

  }

  user: any;

  myprofile: boolean = false
  profileButton() {
    this.myprofile = !this.myprofile
    console.log(this.myprofile);
  }

  logout() {
    this.auth.logout()

  }
}
