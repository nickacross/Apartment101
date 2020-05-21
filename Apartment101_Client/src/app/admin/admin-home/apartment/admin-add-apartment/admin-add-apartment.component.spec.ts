import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddApartmentComponent } from './admin-add-apartment.component';

describe('AdminAddApartmentComponent', () => {
  let component: AdminAddApartmentComponent;
  let fixture: ComponentFixture<AdminAddApartmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminAddApartmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAddApartmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
