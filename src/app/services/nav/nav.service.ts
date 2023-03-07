import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavService {

  constructor() { }

  nav: boolean = true;

  show() {
    this.nav = true;
  }

  hide() {
    this.nav = false;
  }
}
