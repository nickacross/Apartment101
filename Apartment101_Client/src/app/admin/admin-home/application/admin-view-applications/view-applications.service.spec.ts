import { TestBed } from '@angular/core/testing';

import { ViewApplicationsService } from './view-applications.service';

describe('ViewApplicationsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ViewApplicationsService = TestBed.get(ViewApplicationsService);
    expect(service).toBeTruthy();
  });
});
