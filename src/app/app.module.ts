import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './pages/home/home.component';
import { ContactComponent } from './pages/contact/contact.component';
import { FormsModule } from '@angular/forms';
import { AngularFireModule } from '@angular/fire/compat';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NavbarComponent,
    FooterComponent,
    HomeComponent,
    ContactComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,

    AngularFireModule.initializeApp({
      apiKey: "AIzaSyDekzTTXgScbcWhOlqC4T7zcNCWlinCGuw",
      authDomain: "innobix-1c2c1.firebaseapp.com",
      projectId: "innobix-1c2c1",
      storageBucket: "innobix-1c2c1.appspot.com",
      messagingSenderId: "596616046478",
      appId: "1:596616046478:web:4c5436e8b989706d95e6df"
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
