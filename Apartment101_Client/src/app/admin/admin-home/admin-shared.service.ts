import { Injectable } from "@angular/core";
import { BehaviorSubject } from 'rxjs'
import { User } from 'src/app/shared/models/user';

@Injectable({
    providedIn: 'root'
})
export class AdminSharedService {

    private currentAdminDetails = new BehaviorSubject<User>(JSON.parse(sessionStorage.getItem("admin")));
    currentAdmin = this.currentAdminDetails.asObservable();

    updateAdmin(admin: User) {
        this.currentAdminDetails.next(admin);
    }

}