import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuzzerResponseComponent } from './buzzer-response.component';

describe('BuzzerResponseComponent', () => {
  let component: BuzzerResponseComponent;
  let fixture: ComponentFixture<BuzzerResponseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BuzzerResponseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BuzzerResponseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
