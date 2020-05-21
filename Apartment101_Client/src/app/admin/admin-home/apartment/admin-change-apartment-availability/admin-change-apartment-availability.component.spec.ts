import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminChangeApartmentAvailabilityComponent } from './admin-change-apartment-availability.component';

describe('AdminChangeApartmentAvailabilityComponent', () => {
  let component: AdminChangeApartmentAvailabilityComponent;
  let fixture: ComponentFixture<AdminChangeApartmentAvailabilityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminChangeApartmentAvailabilityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminChangeApartmentAvailabilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
