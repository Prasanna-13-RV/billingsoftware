import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-allusers',
  templateUrl: './allusers.component.html',
  styleUrls: ['./allusers.component.css']
})
export class AllusersComponent {
  // allusers: any;

  constructor(private http: HttpClient) { }

  // ngOnInit(): void {
  //   this.http.get(`http://localhost:8080/users`).subscribe((res) => {
  //     this.allusers = res;
  //     console.log(this.allusers);
  //   })
  // }

  para: any = "I am button";

  oneButton() {
    this.para = "Hello one button"
  }
  twoButton() {
    this.para = " Hello Two Button"
  }
  threeButton() {
    this.para = " Hello Three Button"
  }
}
