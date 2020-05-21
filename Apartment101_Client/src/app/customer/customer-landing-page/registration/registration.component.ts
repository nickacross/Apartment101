import { User } from 'src/app/shared/models/user';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { LoginValidators } from "../../../shared/validators/login.validator";
import { RegistrationService } from './registration.service';

@Component(
    {
        selector: "registration",
        templateUrl: "./registration.component.html"
    }
)
export class RegistrationComponent implements OnInit {

    customer: User;
    registerUserForm: FormGroup;
    errorMessage: string;
    successMessage: string;
    constructor(private fb: FormBuilder,private registerService: RegistrationService) {

    }

    ngOnInit() {
        
        this.customer = new User();
        this.customer.usertype="CUSTOMER";
        this.createForm();

    }
    createForm() {

        this.registerUserForm = this.fb.group({
            emailId: ['', [Validators.required, LoginValidators.validateEmailId], null],
            username: ['', [Validators.required, LoginValidators.validateName], null],
            password: ['', [Validators.required, LoginValidators.validatePassword], null],
            confirmPassword: ["", [Validators.required], null]

        });
        this.registerUserForm.get('confirmPassword').setValidators([Validators.required,LoginValidators.confirmPassword(this.registerUserForm.get('password'))]);
    }
    registerUser() {
        this.errorMessage = null;
        this.successMessage = null;
        this.customer = this.registerUserForm.value as User;

        this.registerService.registerCustomer(this.customer)
            .subscribe(
                message => {
                    this.successMessage = message;
                    this.registerUserForm.reset();
                }
                , error => this.errorMessage = <any>error
            )

    }
}   

