import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('user')!)
    console.log(this.user);
  }
  user: any = {}

  constructor(private auth: AuthService) { }

  visible: boolean = false;
  myprofile: boolean = false;

  navClick() {
    this.visible = !this.visible
  }

  profileButton() {
    this.myprofile = !this.myprofile

  }



  logout() {
    this.auth.logout()
  }

}
