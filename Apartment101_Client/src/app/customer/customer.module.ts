import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CustomerRoutingModule } from './customer-routing.module';

import { RegistrationComponent } from './customer-landing-page/registration/registration.component';
import { CustomerLandingPageComponent } from './customer-landing-page/customer-landing-page.component';
import { LoginComponent } from './customer-landing-page/login/login.component';
import { CustomerHomeComponent } from './customer-home/customer-home.component';
import { ViewAvailableApartmentsComponent } from './customer-home/view-available-apartments/view-available-apartments.component';

import { LoginService } from './customer-landing-page/login/login.service';
import { RegistrationService } from './customer-landing-page/registration/registration.service';

@NgModule({
    declarations: [
        RegistrationComponent,
        CustomerLandingPageComponent,
        LoginComponent,
        CustomerHomeComponent,
        ViewAvailableApartmentsComponent
    ],
    imports: [
        CommonModule,
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        CustomerRoutingModule
    ],
    providers: [
        RegistrationService,
        LoginService
    ],
    bootstrap:[RegistrationComponent, LoginComponent],
    exports: []

})
export class CustomerModule {

}