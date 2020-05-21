import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginValidators } from 'src/app/shared/validators/login.validator';
import { LoginService } from './login.service';

@Component({
    selector: 'login',
    templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
    customer: User;
    loginForm: FormGroup;
    errorMessage: string;
    successMessage: string;
    tryToLogin: boolean = false;
    constructor(private fb: FormBuilder, private loginService: LoginService,
        private router: Router) {

    }

    ngOnInit() {
        this.customer = new User();
        this.createForm();

    }
    createForm() {

        this.loginForm = this.fb.group({
            email: [this.customer.email, [Validators.required, LoginValidators.validateEmailId], null],
            password: [this.customer.password, [Validators.required], null]
        });
    }



    login() {
        this.tryToLogin = true;
        this.errorMessage = null;
        this.successMessage = null;
        this.customer = this.loginForm.value as User;
        this.loginService.login(this.customer).subscribe(
            (response) => {
                this.customer = response
                sessionStorage.setItem("customer", JSON.stringify(this.customer));
                sessionStorage.setItem("userType", JSON.stringify("Customer"));
                this.tryToLogin = false;
                this.router.navigate(['/home']);
                this.successMessage= "CUSTOMER LOGIN SUCCESSFUL!";
               
            },
           

            (error) => {
                this.tryToLogin = false;
                this.errorMessage = <any>error;
            }
        )
    }
}