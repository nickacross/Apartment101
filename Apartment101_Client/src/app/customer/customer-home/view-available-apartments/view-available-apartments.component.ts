import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

import { Application } from './application'
import { Observable, Subscription } from 'rxjs';

import { User } from 'src/app/shared/models/user';

@Component({
  selector: 'app-view-available-apartments',
  templateUrl: './view-available-apartments.component.html',
  styleUrls: ['./view-available-apartments.component.css']
})
export class ViewAvailableApartmentsComponent implements OnInit {

  private _url: string = 'http://localhost:8080/Verizon_Server';

  private headers = new HttpHeaders({ 'Content-Type': 'text/plain' });
  currentUser: User;
  apartments: Observable<any>;
  application: Observable<Application>;
  email;
  errorMsg;

  success: boolean;
  
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.success = false;
    this.getApartments();
    this.currentUser = JSON.parse(sessionStorage.getItem("customer"));
  }

  getApartments(){
    this.apartments = this.http.get(this._url + '/ApartmentAPI/getApts');
  }

  newApp(aptNo: number){
    this.sendPostRequest(aptNo).subscribe(
      res => this.email = res,
      error => this.errorMsg = error);
  }

  sendPostRequest(aptNo: number): Observable<any>{
    const data = {
      status: 0,
      apartment: {
        aptType: null, // apt_type 1B1Bath, 2B1Bath, 2B2Bath
        noOfRooms: null,
        noOfBaths: null,
        aptNo: aptNo,
        aptLevel: null,
        typeOfFlooring: null, // Laminate, Carpet, Wood, Tile, Linoleum
        availability: null,
      },
      user: {
        username: null,
        password: null,
        email: this.currentUser.email,
        usertype: 'CUSTOMER'
      }
    }
 
    return this.http.post<any>(
      this._url + '/ApplicationAPI/newApp',
      JSON.stringify(data),
      {headers: this.headers, responseType: 'text' as 'json'}
    );


  }
}







/*
.pipe(
      catchError(
        (err: HttpErrorResponse) => {
          return this.errorHandler(err)
        }
      )
    );
      
  }
  errorHandler(err: HttpErrorResponse): any {
    throw new Error(err.message);
  }
*/



/*newApp( aptNo: number){
    //console.log("newApp" + aptNo);
    const data: Application = {
      status: 0,
      apartment: {
        aptType: null, // apt_type 1B1Bath, 2B1Bath, 2B2Bath
        noOfRooms: null,
        noOfBaths: null,
        aptNo: aptNo,
        aptLevel: null,
        typeOfFlooring: null, // Laminate, Carpet, Wood, Tile, Linoleum
        availability: null,
      },
      user: {
        username: null,
        password: null,
        email: 'nick@gmail.com',
        usertype: 'CUSTOMER'
      }
    }
    //console.log(data);
    const myObjStr = JSON.stringify(data);
    console.log(myObjStr)
    this.application = this.http.post(this._url + '/ApplicationAPI/newApp', myObjStr);
    //console.log(this.application);
  }*/

  //private _url1: string = 'src\assets\Fake JSON\apartment1.json';
  //private _url1: string = 'http://localhost:3000/apartments';
  //private _url2: string = 'http://localhost:3000';

     //console.log(data);
    //const myObjStr = JSON.stringify(data);
    //console.log(myObjStr)
    //console.log(JSON.stringify(data));
    //return this.http.post<any>(this._url2 + '/ApplicationAPI/newApp', JSON.stringify(data));
    //return this.http.post<any>(this._url2 , JSON.stringify(data));
      //console.log(res);
        /*if(res.statusText.equals("OK")){
          this.success=false;
        }*/
        //console.log(res.status);
        //console.log(res.statusText);