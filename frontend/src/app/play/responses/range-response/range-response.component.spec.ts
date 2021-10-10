import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RangeResponseComponent } from './range-response.component';

describe('RangeResponseComponent', () => {
  let component: RangeResponseComponent;
  let fixture: ComponentFixture<RangeResponseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RangeResponseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RangeResponseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
