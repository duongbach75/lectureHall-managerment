import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { DeviceForm } from './device-form.component';

import { DeviceService } from './device.service';

import { DataTable } from 'angular2-datatable';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { Toast } from 'ng2-toastr';

import { TranslateService } from '@ngx-translate/core';
import { Router } from '@angular/router';

import { stat } from 'fs';
import { Classroom } from '../classroom/classroom';
import { from } from 'rxjs/observable/from';
import { Device } from './device';
import { DialogService } from '../../common/dialog/dialog.service';
import { PageInfo } from '../../common/util/page-info';
import { Constants } from '../../common/util/constants';
import { DevicePageInfo } from './DevicePageInfo';

@Component({
    selector: 'app-device-list',
    templateUrl: './device-list.component.html',
    providers: [Device, DialogService, DataTable, DeviceService]
})
/**
 * @description: Display the list of countries and supports search, delete objects
 */
export class DeviceListComponent implements OnInit {

    deviceInfo: DevicePageInfo;
    // name: Amphitheater.nameAmphitheater;
    currentPage = 0;
    filterForm: FormGroup;
    // search restriction
    searchObject: Device;
    checkAllItemFlag = false;
    currentPageView: number = 0;
    fromElement: number;
    toElement: number;
    // total page
    totalPages: number;
    // page sizw
    pageLength: number;
    // toal elements
    totalElements: number;
    numberDeleteItems = 0;
    id: number;
    deviceId: number;
    /** the name of business */
    business: string;
    Classroom: Classroom
    /** the form object */
    DeviceForm: FormGroup;
    Device: Device;
    isUpdate: boolean = true;
    listAmphitheater = null;
    indexClassroomSelection = null;
    amphitheaterSelections = null;
    listClassroom = null;
    classroomSelections = null;


    listStatus = Constants.STATUS_LIST;

    // list amphitheater to export file excel
    devices: Device[];
    fromNumber: number;
    toNumber: number;
    filterObject: Device;
    switchGetDevice: boolean;

    constructor(
        private deviceService: DeviceService,
        // private exportExcelService: ExcelExportService,
        private dialogService: DialogService,
        private fb: FormBuilder,
        private router: Router,
        private translate: TranslateService,
        // private authGuardSubmenu: AuthGuardSubmenu,
        public toastr: ToastsManager, vcr: ViewContainerRef,
        // public amphitheater: Amphitheater
    ) {
        this.toastr.setRootViewContainerRef(vcr);
        // this.exportExcelService = exportExcelService;
    }

    ngOnInit() {

        this.filterForm = DeviceForm.DeviceForm(this.fb, '');
        this.searchObject = new Device();
        this.getPageDevice(this.searchObject, this.currentPage);
        new PageInfo();
    }

    /**
     * @description: Return a Page of entities meeting the search and paging restriction provided in the page and country object
     * @param country: the search restriction
     * @param page: the paging restriction
     */
    getPageDevice(device: Device, page: number) {
        this.searchObject = device;
        debugger
        this.deviceService.getPageDevice(device, page)
            .then(response => {
                debugger
                this.deviceInfo = response.data;
                console.log(this.deviceInfo)
                this.devices = this.deviceInfo.content;
                this.pageLength = this.deviceInfo.content.length;
                this.totalElements = this.deviceInfo.totalElements;
                this.totalPages = this.deviceInfo.totalPages;
                if (!(this.totalPages > 0)) {
                    this.currentPage = -1;
                }

                this.setCurrentPage();
                this.countNumberDeleteItems();
            }).catch(
                error => {
                    console.log("no ok");
                    console.log(error);
                });
    }

    getNumberDeleteItems(): number {
        return this.numberDeleteItems;
    }
    /**
     * @description: Manage page transfers
     * @param page: Page will move to
     */
    choosePageNumber(page) {
        debugger;
        var flag = true;
        var pageNumber;

        if (page.valueAsNumber != null) {
            if (isNaN(page.valueAsNumber)) {
                flag = false;
                page.value = this.currentPage + 1;
                this.currentPageView = 1;
            } else {
                pageNumber = page.value - 1;
            }
        } else {
            pageNumber = page;
        }

        if (flag == true && this.currentPage > pageNumber && pageNumber < 0) {
            pageNumber = 0;
        }
        if (flag == true && this.currentPage < pageNumber && pageNumber > this.totalPages - 1) {
            pageNumber = this.totalPages - 1;
        }
        if (flag == true && !Number.isInteger(pageNumber)) {
            flag = false;
            page.value = this.currentPage + 1;
        }
        if (flag == true) {

            this.currentPage = pageNumber;
            this.getPageDevice(this.searchObject, this.currentPage);
            // page.value = pageNumber + 1;
        }
    }

    // set the information of the page
    private setCurrentPage() {
        debugger;
        if (this.deviceInfo.numberOfElements > 0) {
            this.currentPageView = this.deviceInfo.number + 1;
        } else {
            this.currentPageView = 0;
        }
        var numberOfElements = this.deviceInfo.numberOfElements;
        var size = this.deviceInfo.size;
        this.fromNumber = (this.currentPageView - 1) * size + 1;
        this.toNumber = (this.currentPageView - 1) * size + numberOfElements;
        if (this.toNumber < 1) {
            this.fromNumber = 0;
            this.toNumber = 0;
        }
    }

    /**
     * @description Delete a list countries
     * @param entityIds the list ids
     */
    private delete(entityIds: number[]) {
        this.dialogService
            .confirm('Confirm Information', 'Are you sure to delete?')
            .subscribe(response => {
                if (response == true) {
                    this.deviceService.deleteClassroomsById(entityIds)
                        .then(response => {
                            let message;
                            if (response.code == 200) {
                                this.translate.get('Message.DeleteSuccess').subscribe((res: string) => {
                                    message = res;
                                });
                                this.toastr.success('', message, { dismiss: 'controlled' })
                                    .then((toast: Toast) => {
                                        setTimeout(() => {
                                            this.toastr.dismissToast(toast);
                                        }, 3000);
                                    });
                            } else if (response.code == 400) {
                                this.translate.get('Message.DeleteFail400').subscribe((res: string) => {
                                    message = res;
                                });
                                this.toastr.error('', message, { dismiss: 'controlled' })
                                    .then((toast: Toast) => {
                                        setTimeout(() => {
                                            this.toastr.dismissToast(toast);
                                        }, 3000);
                                    });
                            }

                            this.getPageDevice(this.searchObject, this.currentPage);
                        })
                        .catch(error => {
                            let message;
                            this.translate.get('Message.DeleteFail').subscribe((res: string) => {
                                message = res;
                            });
                            this.toastr.error('', message, { dismiss: 'controlled' })
                                .then((toast: Toast) => {
                                    setTimeout(() => {
                                        this.toastr.dismissToast(toast);
                                    }, 3000);
                                });
                        });
                }
            })
    }

    /**
     *
     * @param id
     */
    deleteOneItem(id: number) {
        var entityIds = [];
        entityIds.push(id);
        this.delete(entityIds);

    }

    /**
     * @description: Check all items when user click the checkbox all.
     */
    checkAllItem() {
        this.checkAllItemFlag = !this.checkAllItemFlag;
        this.deviceInfo.content.forEach(item => {
            item.checked = this.checkAllItemFlag;
        });
    }

    /**
     * @description: Browse the object list, put the checked objects into the object list will be deleted
     * Delete list object
     */
    deleteCheckedItems() {
      var entityIds = [];
      this.devices.forEach(item => {
          if (item.checked == true) {
              entityIds.push(item.idDevice);
          }
      });
      if (entityIds.length > 0) {
          this.dialogService.confirm('Confirm Information', 'Are you sure to delete?')
              .subscribe(response => {
                  if (response == true) {
                      this.deviceService.deleteClassroomsById(entityIds)
                          .then(response => {
                              let message;
                              if (response.code == 200) {
                                this.translate.get('Message.DeleteSuccess').subscribe((res: string) => {
                                    message = res;
                                });
                                this.toastr.success('', message, { dismiss: 'controlled' })
                                    .then((toast: Toast) => {
                                        setTimeout(() => {
                                            this.toastr.dismissToast(toast);
                                        }, 3000);
                                    });
                            } else if (response.code == 400) {
                                this.translate.get('Message.DeleteFail400').subscribe((res: string) => {
                                    message = res;
                                });
                                this.toastr.error('', message, { dismiss: 'controlled' })
                                    .then((toast: Toast) => {
                                        setTimeout(() => {
                                            this.toastr.dismissToast(toast);
                                        }, 3000);
                                    });
                            }

                            this.getPageDevice(this.searchObject, this.currentPage);
                        })
                        .catch(error => {
                            let message;
                            this.translate.get('Message.DeleteFail').subscribe((res: string) => {
                                message = res;
                            });
                            this.toastr.error('', message, { dismiss: 'controlled' })
                                .then((toast: Toast) => {
                                    setTimeout(() => {
                                        this.toastr.dismissToast(toast);
                                    }, 3000);
                                });
                        });
                  }
              })
      }
  }
    /**
     * Count the number of objects checked
    */
    countNumberDeleteItems() {
        this.numberDeleteItems = 0;
        this.deviceInfo.content.forEach(item => {
            if (item.checked) {
                this.numberDeleteItems += 1;
            }
        });
    }

    /**
     * @description: export list country to excel
     */

    // isAuthoriziedNavigation(): boolean {
    //     var isAuthorizied = this.authGuardSubmenu.isAuthoriziedWithCurrentUrl(this.router.url);
    //     return isAuthorizied;
    // }

    search(device: Device, page: number) {
        this.filterObject = device;
        this.switchGetDevice = true;
        debugger;
        this.deviceService.advanceSearch(device, page)
            .then(deviceInfo => {
                debugger;
                this.deviceInfo = deviceInfo.data;
                this.devices = this.deviceInfo.content;
                this.pageLength = this.deviceInfo.content.length;
                this.totalElements = this.deviceInfo.totalElements;
                this.totalPages = this.deviceInfo.totalPages;
                if (this.totalPages > 0) {
                    this.currentPage = -1;
                }
                this.setCurrentPage();
                this.countNumberDeleteItems();
            })
            .catch(error => {
                console.log(error);
            });
    }


}
