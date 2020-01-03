import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

import 'rxjs/add/operator/toPromise';


import { Amphitheater } from './amphitheater';
import { Router } from '@angular/router';

import { concatStatic } from 'rxjs/operator/concat';
import { CommonService } from '../../common/util/common-service/common.service';
import { Constants } from '../../common/util/constants';
import { HeaderField } from '../../common/util/header-field';
import { HeaderValue } from '../../common/util/header-value';

/**
 * Examine the handling  of business requirements with Country module
 */
@Injectable()
export class AmphitheaterService extends CommonService {

    /**  the api url */
    AmphitheaterApi = Constants.BASE_URL + "/amphitheater-management/managed-amphitheater";

    constructor(
        private http: Http,
        router: Router
    ) {
        super(router)
    }

    /**
     * @description create a new country
     * @param country the new country
     */
    create(amphitheater: Amphitheater): Promise<any> {
        // amphitheater.status = "1";
        debugger
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.post(this.AmphitheaterApi,
            amphitheater, { headers: secureHeaders })
            .toPromise()
            .then(response => response.status as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }

    // getListAm(): Promise<any> {
    //     let accessToken = this.getAccessToken();
    //     var secureHeaders = new Headers();
    //     secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
    //     secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
    //     var promise = this.http.get(this.AmphitheaterApi, { headers: secureHeaders })
    //         .toPromise()
    //         .then(response => response.json() as any)
    //         .catch(error => {
    //             return this.handleError(error);
    //         });
    //     return promise;
    // }


    /**
     * @description update a country
     * @param country the new country
     */
    update(amphitheater: Amphitheater): Promise<any> {
        debugger
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.put(this.AmphitheaterApi,
            amphitheater, { headers: secureHeaders })
            .toPromise()
            .then(response => response.status as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }

    /**
     * @description Delete a list countries
     * @param entityIds the list ids
     */
    deleteAmphitheatersByListId(entityIds: number[]): Promise<any> {
        debugger;
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.delete(this.AmphitheaterApi + "/delete-multiple/" + entityIds, { headers: secureHeaders })
            .toPromise()
            .then(response => response.json() as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }

    deleteAmphitheatersById(id: number): Promise<any> {
      debugger;
      let accessToken = this.getAccessToken();
      var secureHeaders = new Headers();
      secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
      secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
      var promise = this.http.delete(this.AmphitheaterApi + "/delete/" + id, { headers: secureHeaders })
          .toPromise()
          .then(response => response.json() as any)
          .catch(error => {
              return this.handleError(error);
          });
      return promise;
  }

    /**
   * @description get page country
   * @param country the search restriction
   * @param page the paging restriction
   */
    getPageAmphitheater(page: number): Promise<any> {
        debugger
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        // secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.get(this.AmphitheaterApi + "?page=" + page + "&size=" + Constants.PAGE_SIZE, { headers: secureHeaders })
            .toPromise()
            .then(response => response.json() as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }

    /**
     * @description Returns a list of entities
     */
    public getListAmphitheater(): Promise<any> {
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.get(this.AmphitheaterApi+"/all", { headers: secureHeaders })
            .toPromise()
            .then(response => response.json() as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }

    /**
     * @description return a country by countryId
     * @param id the id of the country
     */
    findOne(id: number): Promise<any> {
        debugger
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.get(this.AmphitheaterApi + "/find-id/" + id, { headers: secureHeaders })
            .toPromise()
            .then(response => response.json() as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }



    advanceSearch(amphitheater: Amphitheater, page: number): Promise<any> {
        debugger
        let accessToken = this.getAccessToken();
        let secureHeaders = new Headers();

        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        debugger;
        var promise = this.http.post(this.AmphitheaterApi + "/advance-search?page=" + page+ "&size=" + Constants.PAGE_SIZE, amphitheater, { headers: secureHeaders })
            .toPromise()
            .then(response => response.json() as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }



}
