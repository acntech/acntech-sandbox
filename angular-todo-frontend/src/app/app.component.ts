import {Component, OnInit} from '@angular/core';
import {TranslateService} from "./translate/translate.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  public supportedLangs : any[];

  constructor(private _translate: TranslateService) {}

  ngOnInit() {
    this.supportedLangs = [
      { display: 'English', value: 'en' },
      { display: 'Norsk', value: 'no' },
    ];

    this.selectLang('en');
  }

  isCurrentLang(lang: string) {
    return lang === this._translate.currentLang;
  }

  selectLang(lang: string, event?: any) {
    if(event) {
      event.preventDefault();
    }
    this._translate.use(lang);

  }

}
