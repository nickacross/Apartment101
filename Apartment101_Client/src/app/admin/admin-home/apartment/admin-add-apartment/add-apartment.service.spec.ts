import { TestBed } from '@angular/core/testing';

import { AddApartmentService } from './add-apartment.service';

describe('AddApartmentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddApartmentService = TestBed.get(AddApartmentService);
    expect(service).toBeTruthy();
  });
});
