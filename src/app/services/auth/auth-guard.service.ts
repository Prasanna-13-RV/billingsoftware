import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {


  canActivate(): boolean {
    if (Boolean(localStorage.getItem('token')) == true) {
      return true
    }
    this.router.navigate(['/login'])
    return false
  }

  constructor(private router: Router) { }
}
