import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { AddApartmentService } from './add-apartment.service';
import { User } from 'src/app/shared/models/user';
import { Apartment } from 'src/app/shared/models/apartment';

@Component({
  selector: 'app-admin-add-apartment',
  templateUrl: './admin-add-apartment.component.html',
  styleUrls: ['./admin-add-apartment.component.css']
})
export class AdminAddApartmentComponent implements OnInit {
  user: User;
  errorMessage: string;
  successMessage: string;
  aptType: string; noOfRooms: number;
  noOfBaths: number;
  apartmentAddForm: FormGroup;
  constructor(private fb: FormBuilder, private addApartmentService: AddApartmentService) { }

  ngOnInit() {
    this.createForm();
    this.user = JSON.parse(sessionStorage.getItem('user'));
  }

  createForm() {
    this.apartmentAddForm = this.fb.group({
      aptType: ["", Validators.required],
      aptLevel: ["", Validators.required],
      typeOfFlooring: ["", Validators.required],
      availability: ["", [Validators.required, Validators.min(0), Validators.max(1)]]
    });
  }

  // Modify noOfBaths and noOfRooms when aptType
  typeChanged(aptType: string) {
    this.noOfBaths = Number.parseInt(aptType[2]);
    this.noOfRooms = Number.parseInt(aptType[0]);
  }

  AddApartment() {
    let apartment: Apartment = this.apartmentAddForm.value as Apartment;
    apartment.noOfBaths = this.noOfBaths;
    apartment.noOfRooms = this.noOfRooms;

    this.addApartmentService.addApartment(apartment).subscribe(
      (response) => {
        this.successMessage = response;
        console.log(this.successMessage.split(" ")[5]); // Print apt no on the console
        apartment.aptNo = Number.parseInt(this.successMessage.split(" ")[5]);
        this.apartmentAddForm.reset();
      },
      error => this.errorMessage = <any>error
    );
  }

}
