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
    this.getToken = Boolean(localStorage.getItem('token')!)
    console.log(this.getToken);
  }

  logout() {
    this.auth.logout()
  }
}
