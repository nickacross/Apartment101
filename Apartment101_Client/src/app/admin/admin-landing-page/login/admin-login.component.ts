import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginValidators } from 'src/app/shared/validators/login.validator';
import { AdminLoginService } from './admin-login.service';

@Component({
    selector: 'admin-login',
    templateUrl: './admin-login.component.html'
})
export class AdminLoginComponent implements OnInit {
    admin: User;
    loginForm: FormGroup;
    errorMessage: string;
    successMessage: string;
    tryToLogin: boolean = false;
    constructor(private fb: FormBuilder, private loginService: AdminLoginService, private router: Router) { }

    ngOnInit() {
        this.admin = new User();
        this.createForm();
    }

    createForm() {
        this.loginForm = this.fb.group({
            email: [this.admin.email, [Validators.required, LoginValidators.validateEmailId], null],
            password: [this.admin.password, [Validators.required, LoginValidators.validatePassword], null]
        });
    }

    login() {
        this.errorMessage = null;
        this.successMessage = null;
        this.admin = this.loginForm.value as User;

        this.loginService.login(this.admin).subscribe(
            (response) => {
                this.admin = response;
                this.admin.usertype = "ADMIN";
                sessionStorage.setItem("admin", JSON.stringify(this.admin));
                this.router.navigate(['/adminHome']);
            }, error => this.errorMessage = <any>error
        );
    }
}