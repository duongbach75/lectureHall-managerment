import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Classroom } from './Classroom';
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
export class ClassroomService extends CommonService {
    /**  the api url */
    ClassroomApi = Constants.BASE_URL + "/classroom-management/managed-classroom";

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
    create(classroom: Classroom): Promise<any> {
        // amphitheater.status = "1";
        debugger
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.post(this.ClassroomApi,
            classroom, { headers: secureHeaders })
            .toPromise()
            .then(response => response.json() as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }

    /**
     * @description update a country
     * @param country the new country
     */
    update(classroom: Classroom): Promise<any> {
        debugger
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.put(this.ClassroomApi,
            classroom, { headers: secureHeaders })
            .toPromise()
            .then(response => response.json() as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }

    /**
     * @description Delete a list countries
     * @param entityIds the list ids
     */
    deleteClassroomsById(entityIds: number[]): Promise<any> {
        debugger;
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.delete(this.ClassroomApi + "/delete-all/" + entityIds, { headers: secureHeaders })
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
    getPageClassroom(page: number): Promise<any> {
        debugger
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        // secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.get(this.ClassroomApi + "?page=" + page + "&size=" + Constants.PAGE_SIZE, { headers: secureHeaders })
            .toPromise()
            .then(response => response.json() as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }
    getListClassroom(): Promise<any> {
        debugger
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.get(this.ClassroomApi + "/all", { headers: secureHeaders })
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
        var promise = this.http.get(this.ClassroomApi + "/find-id/" + id, { headers: secureHeaders })
            .toPromise()
            .then(response => response.json() as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }

    advanceSearch(classroom: Classroom, page: number): Promise<any> {
        debugger
        let accessToken = this.getAccessToken();
        let secureHeaders = new Headers();

        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        debugger;
        var promise = this.http.post(this.ClassroomApi + "/advance-search?page=" + page + "&size=" + Constants.PAGE_SIZE, classroom, { headers: secureHeaders })
            .toPromise()
            .then(response => response.json() as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }


}
