import { FooterService } from './../../../services/footer/footer.service';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { NavService } from './../../../services/nav/nav.service';
import { Component, OnInit } from '@angular/core';

interface Data {
  invoiceName: String,
  invoiceDate: Date,
  billingAddress: String;
  toAddress: String;
  allRows: Array<Row>
}

interface Row {
  id: Number,
  name: String,
  quantity: Number,
  price: Number,
  total: Number
}

@Component({
  selector: 'app-edit-templates',
  templateUrl: './edit-templates.component.html',
  styleUrls: ['./edit-templates.component.css']
})
export class EditTemplatesComponent implements OnInit {
  ngOnInit(): void {

    this.user = JSON.parse(localStorage.getItem('user')!)
    console.log(this.user.id);
    this.nav.hide()
    this.footer.hide()

    this.id = this.activatedRoute.snapshot.paramMap.get('id')

    console.log(this.id);

    this.http.get(`http://localhost:8080/template/template1/${this.id}`).subscribe((res) => {
      // this.mydata.push(res)
      this.mydata = res
      console.log(this.mydata);

      // this.tabledata.push(JSON.parse(this.mydata[0].all_data))
      // console.log(this.tabledata[0][0]);
      this.tabledata = JSON.parse(this.mydata.all_data)
      console.log(this.tabledata[0]);

      this.invoice_name = this.tabledata[0].invoiceName;
      this.invoice_date = this.tabledata[0].invoiceDate;
      this.billing_address = this.tabledata[0].billingAddress;
      this.to_address = this.tabledata[0].toAddress;
      // this.prod_name = this.tabledata[0].;
      // this.prod_quantity = this.tabledata[0].;
      // this.prod_price = this.tabledata[0].;
      // this.prod_total = this.tabledata[0].;


      this.allRows = this.tabledata[0].allRows


    })
  }

  constructor(private nav: NavService, private activatedRoute: ActivatedRoute, private http: HttpClient, private footer: FooterService) { }


  id: any;
  user: any;

  mydata: any = {};
  tabledata: any = {};


  invoice_name: any;
  invoice_date: any;
  billing_address: any;
  to_address: any;
  prod_name: any;
  prod_quantity: any;
  prod_price: any;
  prod_total: any;

  grant_total: Number = 0


  add_row_function() {
    this.allRows.push({
      id: this.allRows.length + 1,
      name: this.prod_name,
      quantity: this.prod_quantity,
      price: this.prod_price,
      total: this.prod_total
    })
    this.grant_total = Number(this.grant_total) + Number(this.prod_total);
  }

  allRows: Row[] = [];

  newBill: Data[] = []


  edit_bill() {
    this.newBill.push({
      invoiceName: this.invoice_name,
      invoiceDate: this.invoice_date,
      billingAddress: this.billing_address,
      toAddress: this.to_address,
      allRows: this.allRows
    })
    console.log(JSON.stringify(this.newBill));
    this.http.put(`http://localhost:8080/template/template1/update/${this.id}`, {
      template_id: this.id,
      user_id: this.user.id,
      all_data: JSON.stringify(this.newBill),
    }).subscribe((res) => {
      console.log(res);
      alert("Edited")
    })
  }


  save_button: any;
  edit_button: any;

  hrefEvent() { }



}
