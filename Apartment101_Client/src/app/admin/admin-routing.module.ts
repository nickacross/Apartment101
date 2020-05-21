import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdminLandingPageComponent } from './admin-landing-page/admin-landing-page.component';
import { AdminRegisterComponent } from './admin-landing-page/register/admin-register.component';
import { AdminLoginComponent } from './admin-landing-page/login/admin-login.component';

import { AdminAddApartmentComponent } from './admin-home/apartment/admin-add-apartment/admin-add-apartment.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AdminChangeApartmentAvailabilityComponent } from './admin-home/apartment/admin-change-apartment-availability/admin-change-apartment-availability.component';
import { AdminViewApplicationsComponent } from './admin-home/application/admin-view-applications/admin-view-applications.component';


const routes: Routes = [
  {
    path: 'admin', component: AdminLandingPageComponent, children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: AdminLoginComponent },
      { path: 'register', component: AdminRegisterComponent },
    ]
  },
  {
    path: 'adminHome', component: AdminHomeComponent, children: [
      { path: 'addApartment', component: AdminAddApartmentComponent },
      { path: 'changeApartmentAvailability', component: AdminChangeApartmentAvailabilityComponent },
      { path: 'viewApplications', component: AdminViewApplicationsComponent }
    ]
  },
  { path: 'adminRegistration', component: AdminRegisterComponent },
  { path: '', redirectTo: '/applicationHome', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }