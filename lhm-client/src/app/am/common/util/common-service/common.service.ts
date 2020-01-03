import { Router } from '@angular/router';
import { Constants } from '../constants';

export class CommonService {

    constructor(
        protected router: Router
    ) { }

    /**
     * @description Error handling when calling service from server
     * @param error the error infomation
     */
    protected handleError(error: any): Promise<any> {
        debugger
        error = error.json();
        // if stauts error is 403 (Access Denied), re
        if (error.status == 403 || error.status == 401) {
            this.router.navigate(['/login']);
        }
        return Promise.reject(error);
    }

    /**
     * @description get accessToken from localStorage
     */
    protected getAccessToken(): string {
        let accessToken = localStorage.getItem(Constants.ACCESS_TOKEN);
        return accessToken;
    }
}