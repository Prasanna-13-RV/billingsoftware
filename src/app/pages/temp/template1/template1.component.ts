import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TemplateAddComponent } from 'src/app/components/template-add/template-add.component';

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
  selector: 'app-template1',
  templateUrl: './template1.component.html',
  styleUrls: ['./template1.component.css']
})

export class Template1Component implements OnInit {

  grant_total: Number = 0;
  ngOnInit(): void {
    this.allRows.map((res) => {
      this.grant_total = Number(this.grant_total) + Number(res.total);
      console.log(res);
    })
  }

  allRows: Row[] = [
    // {
    //   id: 1,
    //   name: "Hello",
    //   quantity: 65,
    //   price: 50,
    //   total: 21
    // },
    // {
    //   id: 2,
    //   name: "Hello1",
    //   quantity: 65,
    //   price: 50,
    //   total: 25
    // },
    // {
    //   id: 3,
    //   name: "Hello23",
    //   quantity: 65,
    //   price: 50,
    //   total: 27
    // },
    // {
    //   id: 4,
    //   name: "Hello3",
    //   quantity: 65,
    //   price: 50,
    //   total: 20
    // },
  ];


  prod_name: any;
  prod_quantity: any;
  prod_price: any;
  prod_total: any;

  constructor(private http: HttpClient, private router: Router) { }

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

  invoice_name: any;
  invoice_date: any;
  billing_address: any;
  to_address: any;

  newBill: Data[] = [];
  bill_id: Number = 0;

  save_button: boolean = true;
  edit_button: boolean = false;

  create_bill() {
    this.newBill.push({
      invoiceName: this.invoice_name,
      invoiceDate: this.invoice_date,
      billingAddress: this.billing_address,
      toAddress: this.to_address,
      allRows: this.allRows
    })
    console.log(JSON.stringify(this.newBill));

    this.http.post("http://localhost:8080/template/template1/create", {
      user_id: 12345,
      all_data: JSON.stringify(this.newBill),
    }).subscribe((res: any) => {
      console.log(res);
      this.bill_id = res.template_id;
      console.log(this.bill_id);
      alert("Success")
    })
    this.save_button = false;
    this.edit_button = true;
  }

  edit_bill() {
    this.http.put(`http://localhost:8080/template/template1/update/${this.bill_id}`, {
      template_id: this.bill_id,
      user_id: 12345,
      all_data: JSON.stringify(this.newBill),
    }).subscribe((res) => {
      console.log(res);
      alert("Edited")
    })
  }


  template_id: any;
  user_id: any;
  all_data: any;


}
