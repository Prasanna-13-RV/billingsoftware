import { Component, OnInit, AfterContentInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit, AfterContentInit {

  constructor(private http: HttpClient) { }
  allData: any;
  myData : any;


  ngOnInit(): void {
    this.allData = JSON.parse(localStorage.getItem('user')!)
    // console.log(this.allData.address);

    for (let i = 0; i < this.allData.length; i++) {

    }

    // this.http.get(`http://localhost:8080/user/${this.allData.id}`).subscribe((res) => {
    //   console.log(res);

    // })
  }



  userProfile: any;
  ngAfterContentInit(): void {
    this.userProfile = new FormGroup({
      email: new FormControl(this.allData.email),
      username: new FormControl(this.allData.username),
      address: new FormControl(this.allData.address),
      state: new FormControl(this.allData.state),
      country: new FormControl(this.allData.country),
      zipcode: new FormControl(this.allData.zipcode),
      organization: new FormControl(this.allData.organization),
      companyName: new FormControl(this.allData.companyName),
      phone: new FormControl(this.allData.phone),
      template_id: new FormControl(this.allData.template_id),
    })
  }

  profile() {
    console.log(this.userProfile.value);
    this.http.put(`http://localhost:8080/user/${this.allData.id}`, {
      email: this.userProfile.value.email,
      username: this.userProfile.value.username,
      address: this.userProfile.value.address,
      state: this.userProfile.value.state,
      country: this.userProfile.value.country,
      zipcode: this.userProfile.value.zipcode,
      organization: this.userProfile.value.organization,
      companyName: this.userProfile.value.companyName,
      phone: this.userProfile.value.phone,
      template_id: this.userProfile.value.template_id,
    }).subscribe((res) => {
      console.log(res, "res");
    })
  }
}
