import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminRoutingModule } from './admin-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AdminLandingPageComponent } from './admin-landing-page/admin-landing-page.component';
import { AdminRegisterComponent } from './admin-landing-page/register/admin-register.component';
import { AdminLoginComponent } from './admin-landing-page/login/admin-login.component';
import { AdminRegisterService } from './admin-landing-page/register/admin-register.service';
import { AdminLoginService } from './admin-landing-page/login/admin-login.service';
import { AddApartmentService } from './admin-home/apartment/admin-add-apartment/add-apartment.service';
import { ViewApartmentsService } from './admin-home/apartment/admin-change-apartment-availability/view-apartments.service';
import { AdminSharedService } from './admin-home/admin-shared.service';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AdminAddApartmentComponent } from './admin-home/apartment/admin-add-apartment/admin-add-apartment.component';
import { AdminViewApplicationsComponent } from './admin-home/application/admin-view-applications/admin-view-applications.component';
import { AdminChangeApartmentAvailabilityComponent } from './admin-home/apartment/admin-change-apartment-availability/admin-change-apartment-availability.component';

@NgModule({
    declarations: [
        AdminLandingPageComponent,
        AdminRegisterComponent,
        AdminLoginComponent,
        AdminHomeComponent,
        AdminAddApartmentComponent,
        AdminViewApplicationsComponent,
        AdminChangeApartmentAvailabilityComponent
    ],
    imports: [
        CommonModule,
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        AdminRoutingModule
    ],
    providers: [
        AdminLoginService,
        AdminRegisterService,
        AddApartmentService,
        ViewApartmentsService,
        AdminSharedService
    ],
    //    bootstrap: [],
    bootstrap: [AdminRegisterComponent, AdminLoginComponent],
    exports: []
})
export class AdminModule {

}