import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/shared/models/user';
import { AdminSharedService } from './admin-shared.service';
import { Apartment } from 'src/app/shared/models/apartment';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {
  currentUser: User;
  viewApplicationsService: any;
  //apartmentList: Apartment[];

  constructor(private router: Router, private sharedService: AdminSharedService) { }

  ngOnInit() {
    this.sharedService.currentAdmin.subscribe(admin => this.currentUser = admin);
    this.currentUser = JSON.parse(sessionStorage.getItem("admin"));
  }

  logout() {
    sessionStorage.clear();
    this.router.navigate(["admin"]);
  }

}
