import { Component, OnInit } from '@angular/core';
// import { AuthenticationService } from '../authentication/guard/authentication.service';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { LanguageItem, LanguageItemList } from '../i18n-setting';
import { Idle, DEFAULT_INTERRUPTSOURCES } from '@ng-idle/core';
import { Keepalive } from '@ng-idle/keepalive';
import { Http } from '@angular/http';
// import { User } from '../authentication/guard/user';
import { PageInfo } from './common/util/page-info';
import { Observable } from 'rxjs/Rx';
import { Constants } from './common/util/constants';
@Component({
  selector: 'app-am',
  templateUrl: './am.component.html',
  styleUrls: ['./am.component.css'],
  // providers: [Idle]
})
export class AmComponent implements OnInit {

  // currentUser: User;

  idleState = 'Not started.';
  timedOut = false;
  lastPing?: Date = null;

  // timeout token expire
  private expireTime = 0;
  // the token
  private token: string;
  // the timer
  private timer;
  private sub;


  ListLanguage: LanguageItem[];
  SelectedLanguage: LanguageItem;

  constructor(
    private http: Http,
    private idle: Idle,
    // private authenticationService: AuthenticationService,
    private router: Router,
    private translate: TranslateService
  ) {
    this.setLanguage();

    idle.setIdle(5);
    idle.setTimeout(10);
    idle.setInterrupts(DEFAULT_INTERRUPTSOURCES);

    idle.onTimeoutWarning.subscribe((countdown) => this.idleState = 'You will time out in ' + countdown + ' seconds!');


    // idle.onTimeout.subscribe(() => {
    //   alert('Timeout');
    //   this.idleState = 'Timed out!';
    //   this.timedOut = true;
    //   localStorage.clear();
    //   this.router.navigate(['/auth', { sessionExpirate: 'true' }]);
    // });

    // this.token = localStorage.getItem(Constants.ACCESS_TOKEN);
    // if(this.token != undefined && this.token.startsWith("Bearer ")){
    //   this.token = this.token.substring(7);
      // this.authenticationService.getTimeoutTokenExpires(this.token)
      // .then(response => {
      //     this.countdownTimer(response.expires)
      // })
      // .catch(error => {
      //     console.log(error);
      // });
    // } else {
    //   this.logout();
    // }
  }

  ngOnInit() {

    // this.currentUser = new User(JSON.parse(localStorage.getItem(Constants.CURRENT_USER)).data.mbsUser.username, JSON.parse(localStorage.getItem(Constants.CURRENT_USER)).data.mbsUser.fullname, JSON.parse(localStorage.getItem(Constants.CURRENT_USER)).data.mbsUser.email, JSON.parse(localStorage.getItem(Constants.CURRENT_USER)).data.mbsUser.mobile);
  }

  setLanguage(){
    this.ListLanguage = LanguageItemList;
    this.translate.use(localStorage.getItem(Constants.KEY_LANGUAGE));
    // Get current language
    this.ListLanguage.forEach(lang => {
      if (lang.Key === this.translate.currentLang) {
        this.SelectedLanguage = lang;
      }
    });
  }

  /**
   *
   * @param expireTime the time tokens expire
   */
  countdownTimer(expireTime) {
    this.expireTime = expireTime;
    this.timer = Observable.timer(0,1000);
    // subscribing to a observable returns a subscription object
    // this.sub = this.timer.subscribe(t => this.checkExpiredToken());
  }

  /**
   * @description Check for expired token
   * if token is expired, redirect to login page
   */
  // checkExpiredToken(){
  //   if(this.expireTime == 0 ){
  //     this.logout();
  //   }
  //   this.expireTime = this.expireTime - 1;
  // }



  logout() {
    localStorage.removeItem(Constants.IS_AUTHENTIC);
    localStorage.removeItem(Constants.ACCESS_TOKEN);
    localStorage.removeItem(Constants.CURRENT_USER);
    // this.authenticationService.logout();
    // this.router.navigate(['/login']);
  }

  onSelectLanguage(lang: LanguageItem) {
    this.SelectedLanguage = lang;
    localStorage.setItem(Constants.KEY_LANGUAGE, lang.Key);
    window.location.reload();
  }

  // ngOnDestroy(){
  //       this.sub.unsubscribe();
  // }
}

