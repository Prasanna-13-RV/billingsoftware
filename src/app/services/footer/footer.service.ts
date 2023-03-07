import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FooterService {

  constructor() { }

  footer: boolean = true;

  show() {
    this.footer = true;
  }

  hide() {
    this.footer = false;
  }
}
