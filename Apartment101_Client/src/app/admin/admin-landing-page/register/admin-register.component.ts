import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginValidators } from 'src/app/shared/validators/login.validator';
import { AdminRegisterService } from './admin-register.service';

@Component(
    {
        selector: "admin-register",
        templateUrl: "./admin-register.component.html"
    }
)
export class AdminRegisterComponent implements OnInit {

    admin: User;
    registerUserForm: FormGroup;
    errorMessage: string;
    successMessage: string;
    constructor(private fb: FormBuilder, private registerService: AdminRegisterService) {

    }

    ngOnInit() {
        this.admin = new User();
        this.admin.usertype = "ADMIN";
        this.createForm();

    }
    createForm() {

        this.registerUserForm = this.fb.group({
            email: ['', [Validators.required, LoginValidators.validateEmailId], null],
            username: ['', [Validators.required, LoginValidators.validateName], null],
            password: ['', [Validators.required, LoginValidators.validatePassword], null],
            confirmPassword: ["", [Validators.required], null]
        });
        this.registerUserForm.get('confirmPassword').setValidators([Validators.required, LoginValidators.confirmPassword(this.registerUserForm.get('password'))]);
    }

    registerUser() {
        console.log(this.registerUserForm)
        this.errorMessage = null;
        this.successMessage = null;
        this.admin = this.registerUserForm.value as User;

        this.registerService.registerAdmin(this.admin).subscribe(
            (response) => { console.log(response); this.successMessage = response; this.registerUserForm.reset() }
            , error => this.errorMessage = <any>error

        )
    }

}