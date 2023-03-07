import { FooterService } from './../../../services/footer/footer.service';
import { NavService } from './../../../services/nav/nav.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-my-templates',
  templateUrl: './my-templates.component.html',
  styleUrls: ['./my-templates.component.css']
})
export class MyTemplatesComponent implements OnInit {
  ngOnInit(): void {

    this.nav.hide()
    this.footer.hide()

    this.userId = JSON.parse(localStorage.getItem('user')!)
    console.log(this.userId.id);

    this.http.get("http://localhost:8080/template/template1/alldata").subscribe((res: any) => {
      res.map((r: any) => {
        // console.log(r);
        // console.log(r.user_id);
        if (r.user_id == this.userId.id) {
          this.allTemplate.push(r)
          this.allTemplate.map((e: any) => {
            console.log(e, "e");
          });
        }
      })
    })
  }

  userId: any;

  allTemplate: any = [];
  data: any;

  constructor(private http: HttpClient, private router: Router, private nav: NavService, private footer: FooterService) { }

  deleteTemplate(id: any) {
    this.http.delete(`http://localhost:8080/template/template1/delete/${id}`).subscribe((res) => {
      console.log(res);
      alert("Deleted Successfully")
    })
  }

}
