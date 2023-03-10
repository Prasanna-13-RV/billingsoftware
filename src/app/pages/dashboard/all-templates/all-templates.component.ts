import { HttpClient } from '@angular/common/http';
import { FooterService } from './../../../services/footer/footer.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { NavService } from 'src/app/services/nav/nav.service';

@Component({
  selector: 'app-all-templates',
  templateUrl: './all-templates.component.html',
  styleUrls: ['./all-templates.component.css']
})
export class AllTemplatesComponent implements OnInit {

  ngOnInit(): void {
    this.nav.hide()
    this.footer.hide()
  }

  constructor(private router: Router, private nav: NavService, private footer: FooterService, private http: HttpClient) { }

  clickButton(id: any) {

    localStorage.setItem('template_id', id)


    this.router.navigate(['/template1'])
  }

}
