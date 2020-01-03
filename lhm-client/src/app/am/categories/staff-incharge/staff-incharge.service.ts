import { Injectable } from "@angular/core";
import { Http, Headers } from "@angular/http";

import "rxjs/add/operator/toPromise";
import { CommonService } from "../../common/util/common-service/common.service";
import { Router } from "@angular/router";
import { Constants } from "../../common/util/constants";
import { HeaderField } from "../../common/util/header-field";
import { HeaderValue } from "../../common/util/header-value";
import { StaffIncharge } from "./staff-incharge";
import { promise } from "protractor";

@Injectable()
export class StaffInchargeService extends CommonService {
  staffInchargeApi = Constants.BASE_URL + "/manage/staffIncharge";

  constructor(private http: Http, router: Router) {
    super(router);
  }

  /**
     * @description create a new staff-incharge
     * @param staffIncharge the new staff-Incharge
     */
    create(staffIncharge: StaffIncharge): Promise<any> {

      debugger
      let accessToken = this.getAccessToken();
      var secureHeaders = new Headers();
      secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
      secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
      var promise = this.http.post(this.staffInchargeApi,
        staffIncharge, { headers: secureHeaders })
          .toPromise()
          .then(response => response.json() as any)
          .catch(error => {
              return this.handleError(error);
          });
      return promise;
  }

  /**
   * @description update a staff incharge
   * @param staffIncharge the new staffIncharge
   */
  update(staffIncharge: StaffIncharge): Promise<any> {
      debugger
      let accessToken = this.getAccessToken();
      var secureHeaders = new Headers();
      secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
      secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
      var promise = this.http.put(this.staffInchargeApi,
        staffIncharge, { headers: secureHeaders })
          .toPromise()
          .then(response => response.json() as any)
          .catch(error => {
              return this.handleError(error);
          });
      return promise;
  }

  /**
   * @description Delete a list staff incharge
   * @param entityIds the list ids
   */
  deleteListStaffInchargeById(entityIds: number[]): Promise<any> {
      debugger;
      let accessToken = this.getAccessToken();
      var secureHeaders = new Headers();
      secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
      secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
      var promise = this.http.delete(this.staffInchargeApi + "/delete-muti/" + entityIds, { headers: secureHeaders })
          .toPromise()
          .then(response => response.json() as any)
          .catch(error => {
              return this.handleError(error);
          });
      return promise;
  }

  /**
 * @description get page staffIncharge
 * @param staffIncharge the search staffIncharge
 * @param page the paging restriction
 */
  getPageStaffIncharge(staffIncharge: StaffIncharge, page: number): Promise<any> {
      debugger
      let accessToken = this.getAccessToken();
      var secureHeaders = new Headers();
      // secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
      secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
      var promise = this.http.get(this.staffInchargeApi + "/paging/" + "?page=" + page + "&size=" + Constants.PAGE_SIZE, { headers: secureHeaders })
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
  getListStaffIncharge(): Promise<any> {
      let accessToken = this.getAccessToken();
      var secureHeaders = new Headers();
      secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
      secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
      var promise = this.http.get(this.staffInchargeApi+"/all", { headers: secureHeaders })
          .toPromise()
          .then(response => response.json() as any)
          .catch(error => {
              return this.handleError(error);
          });
      return promise;
  }

  /**
   * @description return a staffIncharge by staffIncharge id
   * @param id the id of the staffIncharge
   */
  findOne(id: number): Promise<any> {
      debugger
      let accessToken = this.getAccessToken();
      var secureHeaders = new Headers();
      secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
      secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
      var promise = this.http.get(this.staffInchargeApi  + id, { headers: secureHeaders })
          .toPromise()
          .then(response => response.json() as any)
          .catch(error => {
              return this.handleError(error);
          });
      return promise;
  }

  /**
   * @description return list staffIncharge by classroom
   * 
   * @param page the paging restriction
 */
   


  advanceSearch(items: string[], page: number): Promise<any> {
      debugger
      let accessToken = this.getAccessToken();
      let secureHeaders = new Headers();

      secureHeaders.append(HeaderField.AUTHORIZATION, accessToken);
      secureHeaders.append(HeaderField.CONTENT_TYPE, HeaderValue.APPLICATION_JSON_VALUE);
      debugger;
      var promise = this.http.post(this.staffInchargeApi + "/advance-search?search=" + page+ "&size=" + Constants.PAGE_SIZE, staffIncharge, { headers: secureHeaders })
          .toPromise()
          .then(response => response.json() as any)
          .catch(error => {
              return this.handleError(error);
          });
      return promise;
  }

}
