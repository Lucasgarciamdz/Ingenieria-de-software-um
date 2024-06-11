import { TestBed } from '@angular/core/testing';

import { EditorsService } from './editors.service';

describe('EditorsService', () => {
  let service: EditorsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EditorsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
