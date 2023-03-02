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
    this.data[0].allRows.map((res) => {
      this.grant_total = Number(this.grant_total) + Number(res.total);
    })
    console.log(this.data[0].invoiceName);
  }

  @ViewChild('pdfDownload', { static: false }) el!: ElementRef;

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

  data: Data[] = [
    {
      "invoiceName": "567",
      "invoiceDate": "7898-06-05",
      "billingAddress": "The Boring Company\nTesla Street 007\nFrisco\nCA 0000",
      "toAddress": "The Boring Company\nTesla Street 007\nFrisco\nCA 0000",
      "allRows": [
        {
          "id": 1,
          "name": "fghjk",
          "quantity": "6",
          "price": "6",
          "total": "68"
        },
        {
          "id": 2,
          "name": "fghjk",
          "quantity": "6",
          "price": "6",
          "total": "68"
        },
        {
          "id": 3,
          "name": "fghjk",
          "quantity": "6",
          "price": "6",
          "total": "68"
        }
      ]
    }
  ]

  // allRows: Row[] = [
  //   {
  //     id: 1,
  //     name: "Hello",
  //     quantity: 65,
  //     price: 50,
  //     total: 25
  //   },
  //   {
  //     id: 2,
  //     name: "Hello1",
  //     quantity: 65,
  //     price: 50,
  //     total: 25
  //   },
  //   {
  //     id: 3,
  //     name: "Hello23",
  //     quantity: 65,
  //     price: 50,
  //     total: 25
  //   },
  //   {
  //     id: 4,
  //     name: "Hello3",
  //     quantity: 65,
  //     price: 50,
  //     total: 25
  //   },
  // ];
}
