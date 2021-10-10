import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OptionResponseComponent } from './option-response.component';

describe('OptionResponseComponent', () => {
  let component: OptionResponseComponent;
  let fixture: ComponentFixture<OptionResponseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OptionResponseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OptionResponseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
