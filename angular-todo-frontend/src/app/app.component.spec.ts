import { TestBed, async } from '@angular/core/testing';

import { AppComponent } from './app.component';
import { TranslatePipe } from "./translate/translate.pipe";
import {RouterLinkStubDirective, RouterOutletStubComponent} from "../testing/router-stubs";
import { TranslateServiceStub} from "../testing/translate-stubs";
import { Router } from "@angular/router";
import { TranslateService } from "./translate/translate.service";

class RouterStub {}


describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        TranslatePipe,
        RouterLinkStubDirective, RouterOutletStubComponent
      ],
      providers: [
        { provide: Router, useClass: RouterStub },
        { provide: TranslateService, useClass: TranslateServiceStub },
      ]
    }).compileComponents();
  }));

  it('should make an app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    fixture.detectChanges();
    expect(app).toBeTruthy();
    expect(app.supportedLangs).toBeDefined();
  }));

  it('should set initial language in translate service', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const ts = fixture.debugElement.injector.get(TranslateService);
    const useSpy = spyOn(ts, 'use').and.callThrough();
    fixture.detectChanges();

    expect(useSpy.calls.any()).toBe(true, 'en');
  });
});
