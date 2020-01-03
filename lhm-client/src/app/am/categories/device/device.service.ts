import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

import 'rxjs/add/operator/toPromise';


import { Device } from './device';
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
export class DeviceService extends CommonService {

    /**  the api url */
    DeviceApi = Constants.BASE_URL + "/device-management/managed-devices";

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
    create(device: Device): Promise<any> {
        // amphitheater.status = "1";
        debugger
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.post(this.DeviceApi,
            device, { headers: secureHeaders })
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
    update(device: Device): Promise<any> {
        debugger
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.put(this.DeviceApi,
            device, { headers: secureHeaders })
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
        var promise = this.http.delete(this.DeviceApi + "/delete-all/" + entityIds, { headers: secureHeaders })
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
    getPageDevice(device: Device, page: number): Promise<any> {
        debugger
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        // secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.get(this.DeviceApi + "?page=" + page + "&size=" + Constants.PAGE_SIZE, { headers: secureHeaders })
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
    getListClassroom(): Promise<any> {
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.get(this.DeviceApi+"/all", { headers: secureHeaders })
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
        var promise = this.http.get(this.DeviceApi + "/find-one/" + id, { headers: secureHeaders })
            .toPromise()
            .then(response => response.json() as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }



    advanceSearch(device: Device, page: number): Promise<any> {
        debugger
        let accessToken = this.getAccessToken();
        let secureHeaders = new Headers();

        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        debugger;
        var promise = this.http.post(this.DeviceApi + "/advance-search?page=" + page+ "&size=" + Constants.PAGE_SIZE, device, { headers: secureHeaders })
            .toPromise()
            .then(response => response.json() as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }

    getListDeviceById(idDevice: number): Promise<any> {
        debugger
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.get(this.DeviceApi + "/" + idDevice , { headers: secureHeaders })
            .toPromise()
            .then(response => response.json() as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }

    findClassromByDevice(id: Number) :Promise<any> {
        debugger
        let accessToken = this.getAccessToken();
        var secureHeaders = new Headers();
        secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
        secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
        var promise = this.http.get(this.DeviceApi + "/" + id , { headers: secureHeaders })
            .toPromise()
            .then(response => response.json() as any)
            .catch(error => {
                return this.handleError(error);
            });
        return promise;
    }

}
