import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth'
import { Router } from '@angular/router';
import { GoogleAuthProvider } from 'firebase/auth';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private fireauth: AngularFireAuth, private router: Router, private http: HttpClient) { }
  login(email: string, password: string) {
    this.fireauth.signInWithEmailAndPassword(email, password).then((response) => {
      const data = {
        email: response.user?.email,
        username: response.user?.email?.split('@')[0]
      }
      localStorage.setItem('token', 'true');
      this.router.navigate(['/']);
      this.http.get(`http://localhost:8080/user/${email}`).subscribe((res) => {
        localStorage.setItem('user', JSON.stringify(res))
      })
    }, err => {
      alert(err.message);
      this.router.navigate(['/login'])
    })
  }

  register(email: string, password: string) {
    this.fireauth.createUserWithEmailAndPassword(email, password).then((response) => {

      this.http.post("http://localhost:8080/user", {
        email: response.user?.email,
        username: response.user?.email?.split('@')[0]
      }).subscribe((res) => {
        alert('Registration Successful');
        this.router.navigate(['/login']);
      })
    }, err => {
      alert(err.message);
      this.router.navigate(['/register']);
    })
  }

  logout() {
    this.fireauth.signOut().then(() => {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      this.router.navigate(["/login"]);
    }, err => {
      alert(err.message)
    })
  }

  GoogleAuth() {
    return this.AuthLogin(new GoogleAuthProvider());
  }

  async AuthLogin(provider: any) {
    return this.fireauth
      .signInWithPopup(provider)
      .then((result) => {
        console.log('You have been successfully logged in!');
        this.router.navigate(["/profile"]);
      })
      .catch((error) => {
        console.log(error);
      });
  }
}
