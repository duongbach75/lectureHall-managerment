import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ClassroomForm } from './classroom-form.component';

import { ClassroomService } from './classroom.service';

import { DataTable } from 'angular2-datatable';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { Toast } from 'ng2-toastr';

import { TranslateService } from '@ngx-translate/core';
import { Router } from '@angular/router';

import { stat } from 'fs';
import { Classroom } from './classroom';
import { DialogService } from '../../common/dialog/dialog.service';
import { PageInfo } from '../../common/util/page-info';
import { Constants } from '../../common/util/constants';
import { ClassroomPageInfo } from './ClassroomPageInfo';

@Component({
    selector: 'app-classroom-list',
    templateUrl: './classroom-list.component.html',
    providers: [Classroom, DialogService, DataTable, ClassroomService]
})
/**
 * @description: Display the list of countries and supports search, delete objects
 */
export class ClassroomListComponent implements OnInit {

    classroomInfo: ClassroomPageInfo
    // name: Amphitheater.nameAmphitheater;
    classrooms: Classroom[];
    currentPage = 0;
    filterForm: FormGroup;
    // search restriction
    searchObject: Classroom;
    checkAllItemFlag = false;
    currentPageView: number;
    fromElement: number;
    toElement: number;
    // total page
    totalPages: number;
    // page sizw
    pageLength: number;
    // toal elements
    totalElements: number;
    numberDeleteItems = 0;

    listStatus = Constants.STATUS_LIST;

    // list amphitheater to export file excel
    fromNumber: number;
    toNumber: number;
    filterObject: Classroom;
    switchGetClassroom: boolean;

    constructor(
        private classroomService: ClassroomService,
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

        this.filterForm = ClassroomForm.classroomForm(this.fb, '');
        this.searchObject = new Classroom();
        this.getPageClassroom(this.currentPage);
        new PageInfo();
    }

    /**
     * @description: Return a Page of entities meeting the search and paging restriction provided in the page and country object
     * @param country: the search restriction
     * @param page: the paging restriction
     */
    getPageClassroom(currentPage: number) {
        debugger
        this.classroomService
            .getPageClassroom(currentPage)
            .then(
                classroomInfo => {
                this.classroomInfo = classroomInfo.data;
                this.classrooms = this.classroomInfo.content;
                this.pageLength = this.classroomInfo.content.length;
                this.totalElements = this.classroomInfo.totalElements;
                this.totalPages = this.classroomInfo.totalPages;
                if (!(this.totalPages > 0)) {
                    this.currentPage = -1;
                }

                this.setCurrentPage();
                this.countNumberDeleteItems();
            }).catch(
                error => {
                    console.log(error);
                });
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
            this.getPageClassroom(this.currentPage);
            // page.value = pageNumber + 1;
        }
    }

    // set the information of the page
    private setCurrentPage() {
        debugger;
        if (this.classroomInfo.numberOfElements > 0) {
            this.currentPageView = this.classroomInfo.number + 1;
        } else {
            this.currentPageView = 0;
        }
        var numberOfElements = this.classroomInfo.numberOfElements;
        var size = this.classroomInfo.size;
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
                    this.classroomService.deleteClassroomsById(entityIds)
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

                            this.getPageClassroom(this.currentPage);
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
        this.classroomInfo.content.forEach(item => {
            item.checked = this.checkAllItemFlag;
        });
    }

    /**
     * @description: Browse the object list, put the checked objects into the object list will be deleted
     * Delete list object
     */
    deleteCheckedItems() {
      var entityIds = [];
      this.classrooms.forEach(item => {
          if (item.checked == true) {
              entityIds.push(item.idClassroom);
          }
      });
      if (entityIds.length > 0) {
          this.dialogService.confirm('Confirm Information', 'Are you sure to delete?')
              .subscribe(response => {
                  if (response == true) {
                      this.classroomService.deleteClassroomsById(entityIds)
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

                            this.getPageClassroom(this.currentPage);
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
        this.classroomInfo.content.forEach(item => {
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
    search(classroom: Classroom, page: number) {
        this.filterObject = classroom;
        this.switchGetClassroom = true;
        debugger;
        this.classroomService.advanceSearch(classroom, page)
            .then(classroomInfo => {
                debugger;
                this.classroomInfo = classroomInfo.data;
                this.classrooms = this.classroomInfo.content;
                this.pageLength = this.classroomInfo.content.length;
                this.totalElements = this.classroomInfo.totalElements;
                this.totalPages = this.classroomInfo.totalPages;
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

    routerLinkUpdate(type) {
        debugger
        if (type == 'create2') {
            this.router.navigate(['/classroom/create2']);
        } else {
            this.classroomInfo.content.forEach(item => {
                if (item.checked == true) {
                    debugger
                    this.router.navigate(['/classroom/' + type, item.idClassroom]);
                }
            });
        }
    }
    getNumberDeleteItems(): number {
        return this.numberDeleteItems;
    }

}
