import { TestBed } from '@angular/core/testing';

import { AddTemplateService } from './add-template.service';

describe('AddTemplateService', () => {
  let service: AddTemplateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddTemplateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
