import { TemplateComponent } from './pages/template/template.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { ContactComponent } from './pages/contact/contact.component';
import { HomeComponent } from './pages/home/home.component';
import { AllusersComponent } from './pages/practise/allusers/allusers.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { Template1Component } from './pages/temp/template1/template1.component';
import { TemplateAddComponent } from './components/template-add/template-add.component';
import { MyTemplatesComponent } from './pages/dashboard/my-templates/my-templates.component';
import { AllTemplatesComponent } from './pages/dashboard/all-templates/all-templates.component';
import { EditTemplatesComponent } from './pages/dashboard/edit-templates/edit-templates.component';
import { AuthGuardService } from './services/auth/auth-guard.service';

const routes: Routes = [
  {
    path: "",
    component: HomeComponent
  },
  {
    path: "contact",
    component: ContactComponent
  },
  {
    path: "profile",
    component: ProfileComponent,
    canActivate : [AuthGuardService]
  },
  {
    path: "template",
    component: TemplateComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "allusers",
    component: AllusersComponent,
    canActivate : [AuthGuardService]
  },
  {
    path: "template1",
    component: Template1Component,
    canActivate : [AuthGuardService]
  },
  {
    path: "template/:id",
    component: TemplateAddComponent,
    canActivate : [AuthGuardService]
  },
  {
    path: "dashboard/mytemplates",
    component: MyTemplatesComponent,
    canActivate : [AuthGuardService]
  },
  {
    path: "dashboard/alltemplates",
    component: AllTemplatesComponent,
    canActivate : [AuthGuardService]
  },
  {
    path: "dashboard/edittemplates/:id",
    component: EditTemplatesComponent,
    canActivate : [AuthGuardService]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})


export class AppRoutingModule { }
