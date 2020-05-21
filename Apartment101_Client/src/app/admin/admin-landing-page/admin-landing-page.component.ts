import { Component } from '@angular/core';

@Component({
    selector:"admin-landing-page",
    templateUrl:'./admin-landing-page.component.html',
    
})
export class AdminLandingPageComponent{
      activity:string="login";
    
     userActivity(option:string){
          this.activity=option;
      }
}