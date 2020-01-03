import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Constants } from '../../../common/util/constants';
import { CommonService } from '../../util/common-service/common.service';
import { Router } from '@angular/router';
import { HeaderField } from '../../util/header-field';
import { HeaderValue } from '../../util/header-value';

@Injectable()
export class MbsNavigationsService extends CommonService {
  mbsNavigationApi = Constants.BASE_URL + "navigations";
  constructor(
    private http: Http,
    router: Router
  ) {
    super(router)
  }

  // findAllMbsNavigations(): Promise<any> {
  //   debugger
  //   let accessToken = this.getAccessToken();
  //   var secureHeaders = new Headers();
  //   secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
  //   secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
  //   var promise = this.http.get(this.mbsNavigationApi, { headers: secureHeaders })
  //     .toPromise()
  //     .then(response => response.json() as any)
  //     .catch(error => {
  //       this.handleError(error);
  //     });
  //   return promise;
  // }
}
