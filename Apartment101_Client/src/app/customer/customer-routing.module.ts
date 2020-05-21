import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegistrationComponent } from './customer-landing-page/registration/registration.component';
import { LoginComponent } from './customer-landing-page/login/login.component';
import { CustomerLandingPageComponent } from './customer-landing-page/customer-landing-page.component';

import { CustomerHomeComponent } from './customer-home/customer-home.component';
import { ViewAvailableApartmentsComponent} from './customer-home/view-available-apartments/view-available-apartments.component'

const routes: Routes = [
  { path: 'applicationHome', component: CustomerLandingPageComponent, children: [
    { path: '', redirectTo: 'login', pathMatch: 'full'},
    { path: 'login', component: LoginComponent},
    { path: 'register', component: RegistrationComponent}
  ] },
  { path: 'home', component: CustomerHomeComponent, children: [
    { path: '', redirectTo: 'apartments', pathMatch: 'full'},
    { path: 'apartments', component: ViewAvailableApartmentsComponent}
  ]},
  { path: '', redirectTo: '/applicationHome', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
