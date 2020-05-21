import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../shared/models/user';
import { CustomerSharedService } from './customer-shared-service';

@Component({
  selector: 'app-customer-home',
  templateUrl: './customer-home.component.html',
  styleUrls: ['./customer-home.component.css']
})
export class CustomerHomeComponent implements OnInit {
  currentUser: User;

  constructor(
    private router: Router, 
    private customerSharedService: CustomerSharedService) { }

  ngOnInit() { 
    this.getLoggedInCustomer();
  }

  getLoggedInCustomer() {
    this.customerSharedService.updatedCustomer.subscribe(customer => this.currentUser = customer);
    this.currentUser = JSON.parse(sessionStorage.getItem("customer"));
  }

  logout(){
    sessionStorage.clear();
    this.router.navigate([""]);
  }

}
