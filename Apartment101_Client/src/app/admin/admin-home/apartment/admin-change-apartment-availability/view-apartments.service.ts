import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Application } from 'src/app/shared/models/application';
import { environment } from 'src/environments/environment';
import { Apartment } from 'src/app/shared/models/apartment';
import { catchError } from 'rxjs/internal/operators/catchError';

@Injectable({
  providedIn: 'root'
})
export class ViewApartmentsService {

  constructor(private http: HttpClient) { }

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  getApartments(): Observable<Apartment[]> {
    const url = environment.apartmentAPI + "/getAllApts";
    console.log(url);
    return this.http.get<Apartment[]>(url).pipe(catchError(this.handleError));
  }

  changeAvailability(aptNo: number, availability: number) {
    const url = environment.apartmentAPI + "/modifyAptAvailability/" + aptNo + "/" + availability + "/";
    return this.http.get<string>(url).pipe(catchError(this.handleError));
  }

  private handleError(err: HttpErrorResponse) {
    console.log(err);
    let errMsg: string = '';

    if (err.error instanceof Error) {
      errMsg = err.error.message;
      console.log(errMsg)
    }
    else if (typeof err.error === 'string') {
      errMsg = JSON.parse(err.error).message
    }
    else {
      if (err.status == 0)
        errMsg = "A connection to back end can not be established.";
      else
        errMsg = err.error.message;
    }
    return throwError(errMsg);
  }

}