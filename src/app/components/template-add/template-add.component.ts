import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Component, ViewChild, ElementRef } from '@angular/core';
import jsPDF from 'jspdf';

interface Data {
  invoiceName: String,
  invoiceDate: String,
  billingAddress: String;
  toAddress: String;
  allRows: Array<Row>
}

interface Row {
  id: Number,
  name: String,
  quantity: String,
  price: String,
  total: String
}

@Component({
  selector: 'app-template-add',
  templateUrl: './template-add.component.html',
  styleUrls: ['./template-add.component.css']
})

export class TemplateAddComponent {
  grant_total: Number = 0;

  ngOnInit(): void {
    // this.href = this.router.url.split('/');
    // console.log(this.href[this.href.length - 1]);

    this.id = this.activatedRoute.snapshot.paramMap.get('id')


    this.http.get(`http://localhost:8080/template/template1/${this.id}`).subscribe((res) => {
      // this.mydata.push(res)
      this.mydata = res
      // this.tabledata.push(JSON.parse(this.mydata[0].all_data))
      // console.log(this.tabledata[0][0]);
      this.tabledata = JSON.parse(this.mydata.all_data)
      console.log(this.tabledata[0]);




    })

  }

  id: any = [];


  mydata: any = {};
  tabledata: any = {};


  @ViewChild('pdfDownload', { static: false }) el!: ElementRef;

  constructor(private http: HttpClient, private router: Router, private activatedRoute: ActivatedRoute) { }
  makepdf() {
    try {
      let pdf = new jsPDF('p', 'pt', 'a4');
      pdf.html(this.el.nativeElement, {
        callback: (pdf) => {
          pdf.save('demo.pdf')
        }
      })
      // pdf.text("dfgsdfg", 10, 10)
      // pdf.save()
    } catch (error) {
      console.log(error);
    }
  }


}


// allRows
// :
// [{…}]
// billingAddress
// :
// "fghjkl"
// invoiceDate
// :
// "6789-05-04"
// invoiceName
// :
// "4567"
// toAddress
// :
// "fghjk"
