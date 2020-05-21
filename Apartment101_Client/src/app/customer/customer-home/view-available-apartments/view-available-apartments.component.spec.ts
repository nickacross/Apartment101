import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAvailableApartmentsComponent } from './view-available-apartments.component';

describe('ViewAvailableApartmentsComponent', () => {
  let component: ViewAvailableApartmentsComponent;
  let fixture: ComponentFixture<ViewAvailableApartmentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAvailableApartmentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAvailableApartmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
