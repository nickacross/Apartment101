import { Component, OnInit } from '@angular/core';
import { ViewApartmentsService } from './view-apartments.service';
import { User } from 'src/app/shared/models/user';
import { Apartment } from 'src/app/shared/models/apartment';

@Component({
  selector: 'app-admin-change-apartment-availability',
  templateUrl: './admin-change-apartment-availability.component.html',
  styleUrls: ['./admin-change-apartment-availability.component.css']
})
export class AdminChangeApartmentAvailabilityComponent implements OnInit {
  user: User;
  apartmentList: Apartment[];
  successMessage: string;
  errorMessage: string;

  constructor(private viewApartmentsService: ViewApartmentsService) { }

  ngOnInit() {
    this.viewApartmentsService.getApartments().subscribe(
      apartmentList => {
        this.apartmentList = apartmentList;
      });
    this.user = JSON.parse(sessionStorage.getItem("user"));
  }

  changeAvailability(aptNo: number, availability: number) {
    let changeAvailability: number = (availability == 0) ? 1 : 0;
    this.viewApartmentsService.changeAvailability(aptNo, changeAvailability).subscribe(
      (response) => {
        this.successMessage = response;
        for (let apt of this.apartmentList)
          if (apt.aptNo == aptNo) {
            apt.availability = changeAvailability;
            return;
          }
      },
      error => {
        this.errorMessage = <any>error;
        console.log(error);
      });
  }
  
}
