import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Router } from '@angular/router';
import { Http } from '@angular/http';
import { Idle, DEFAULT_INTERRUPTSOURCES } from '@ng-idle/core';
import { Keepalive } from '@ng-idle/keepalive';
import { Constants } from './am/common/util/constants';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  /**
   * @description Initialize application with the language selected in the previous session
   * if this is the first session, default language is Vietnamese
   * @param translate the translateService
   */
  constructor(
    private translate: TranslateService
  ) {
    // check for existence of keyLanguage in localStorage.
    if(localStorage.getItem(Constants.KEY_LANGUAGE)==undefined||localStorage.getItem(Constants.KEY_LANGUAGE)==''){
      localStorage.setItem(Constants.KEY_LANGUAGE,'vi')
    }
    // get key language in local storage to set default language
    translate.setDefaultLang(localStorage.getItem(Constants.KEY_LANGUAGE));
  }
}