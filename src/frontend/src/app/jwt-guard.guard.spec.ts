import { TestBed, async, inject } from '@angular/core/testing';

import { JwtGuardGuard } from './jwt-guard.guard';

describe('JwtGuardGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [JwtGuardGuard]
    });
  });

  it('should ...', inject([JwtGuardGuard], (guard: JwtGuardGuard) => {
    expect(guard).toBeTruthy();
  }));
});
