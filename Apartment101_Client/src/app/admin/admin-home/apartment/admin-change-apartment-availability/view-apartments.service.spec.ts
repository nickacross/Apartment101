import { TestBed } from '@angular/core/testing';

import { ViewApartmentsService } from './view-apartments.service';

describe('ViewApartmentsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ViewApartmentsService = TestBed.get(ViewApartmentsService);
    expect(service).toBeTruthy();
  });
});
