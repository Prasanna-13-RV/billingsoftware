import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TemplateAddComponent } from 'src/app/components/template-add/template-add.component';


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

  ngOnInit(): void { }

  allRows: Row[] = [
    {
      id: 1,
      name: "Hello",
      quantity: 65,
      price: 50,
      total: 25
    },
    {
      id: 2,
      name: "Hello",
      quantity: 65,
      price: 50,
      total: 25
    },
    {
      id: 3,
      name: "Hello",
      quantity: 65,
      price: 50,
      total: 25
    },
    {
      id: 4,
      name: "Hello",
      quantity: 65,
      price: 50,
      total: 25
    },
  ];

  constructor(private matDialog: MatDialog) { }
  openDialog() {
    this.matDialog.open(TemplateAddComponent, {
      width: '400px',
    })
  }

  // displayedColumns: string[] = ['position', 'name', 'quantity', 'price', 'total'];
  // dataSource = this.allRows;
}
