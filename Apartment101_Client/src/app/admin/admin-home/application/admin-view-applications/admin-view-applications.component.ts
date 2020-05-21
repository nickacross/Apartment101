import { Component, OnInit } from '@angular/core';
import { ViewApplicationsService } from './view-applications.service';
import { User } from 'src/app/shared/models/user';
import { Apartment } from 'src/app/shared/models/apartment';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-view-applications',
  templateUrl: './admin-view-applications.component.html'
})
export class AdminViewApplicationsComponent implements OnInit {
  user: User;
  apartmentList: Apartment[];
  successMessage: string;
  errorMessage: string;

  constructor(private viewApplicationsService: ViewApplicationsService, private router: Router) { }

  ngOnInit() {
    this.viewApplicationsService.getApartments().subscribe(
      apartmentList => {
        this.apartmentList = apartmentList;
      });
    this.user = JSON.parse(sessionStorage.getItem("admin"));
  }

  approveApplication(appId: number) {
    this.viewApplicationsService.approveApplication(appId).subscribe(
      response => {
        console.log(response)
        console.log(this.successMessage);

        let appId: number = Number.parseInt(this.successMessage.split[5]);
        for (let apt of this.apartmentList)
          if (apt.availability == 1)
            for (let app of apt.appList)
              if (app.status == 0)
                if (app.appId == appId)
                  app.status = 1;
      },
      error => {
        this.errorMessage = <any>error;
        console.log(error);
      });

  }

}