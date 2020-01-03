import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

import { AmphitheaterService } from './amphitheater.service';

import { DataTable } from 'angular2-datatable';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { Toast } from 'ng2-toastr';

import { TranslateService } from '@ngx-translate/core';
import { Router } from '@angular/router';

import { stat } from 'fs';
import { Amphitheater } from './amphitheater';
import { DialogService } from '../../common/dialog/dialog.service';
import { PageInfo } from '../../common/util/page-info';
import { Constants } from '../../common/util/constants';
import { AmphitheaterPageInfo } from './AmphitheaterPageInfo';
import { AmphitheaterForm } from './amphitheater-form.component';

@Component({
    selector: 'app-amphitheater-list',
    templateUrl: './amphitheater-list.component.html',
    providers: [Amphitheater, DialogService, DataTable, AmphitheaterService]
})
/**
 * @description: Display the list of countries and supports search, delete objects
 */
export class AmphitheaterListComponent implements OnInit {

    amphitheaterInfo: AmphitheaterPageInfo;
    amphitheaters: Amphitheater[];
    // name: Amphitheater.nameAmphitheater;
    currentPage = 0;
    filterForm: FormGroup;
    // search restriction
    filterObject: Amphitheater;
    switchGetAmphitheater = false;
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

    listStatus = Constants.STATUS_LIST;
    fromNumber: number;
    toNumber: number;

    // list amphitheater to export file excel
    amphitheater: Amphitheater[];

    constructor(
        private amphitheaterService: AmphitheaterService,
        private dialogService: DialogService,
        private fb: FormBuilder,
        private router: Router,
        private translate: TranslateService,
        public toastr: ToastsManager, vcr: ViewContainerRef,
        // public amphitheater: Amphitheater
    ) {
        this.toastr.setRootViewContainerRef(vcr);
    }

    ngOnInit() {
        this.filterForm = AmphitheaterForm.AmphitheaterForm(this.fb, '');
        this.filterObject = new Amphitheater();
        this.getPageAmphitheater(this.currentPage);
        new PageInfo();
    }

    /**
     * @description: Return a Page of entities meeting the search and paging restriction provided in the page and country object
     * @param country: the search restriction
     * @param page: the paging restriction
     */
    getPageAmphitheater(page: number) {
        debugger
        this.amphitheaterService.getPageAmphitheater(page)
            .then(response => {
                debugger
                this.amphitheaterInfo = response.data;
                this.amphitheaters = this.amphitheaterInfo.content;
                this.pageLength = this.amphitheaterInfo.content.length;
                this.totalElements = this.amphitheaterInfo.totalElements;
                this.totalPages = this.amphitheaterInfo.totalPages;
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
                // this.currentPageView = 1;
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
            this.getPageAmphitheater(this.currentPage);
            // page.value = pageNumber + 1;
        }
    }

    // set the information of the page
    private setCurrentPage() {
        if (this.amphitheaterInfo.numberOfElements > 0) {
            this.currentPageView = this.amphitheaterInfo.number + 1;
        } else {
            this.currentPageView = 0;
        }

        var numberOfElements = this.amphitheaterInfo.numberOfElements;
        var size = this.amphitheaterInfo.size;
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
                    this.amphitheaterService.deleteAmphitheatersByListId(entityIds)
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

                            this.getPageAmphitheater(this.currentPage);
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
        this.amphitheaterInfo.content.forEach(item => {
            item.checked = this.checkAllItemFlag;
        });
    }

    /**
     * @description: Browse the object list, put the checked objects into the object list will be deleted
     * Delete list object
     */
    deleteCheckedItems() {
      var entityIds = [];
      this.amphitheaters.forEach(item => {
          if (item.checked == true) {
              entityIds.push(item.idAmphitheater);
          }
      });
      if (entityIds.length > 0) {
          this.dialogService.confirm('Confirm Information', 'Are you sure to delete?')
              .subscribe(response => {
                  if (response == true) {
                      this.amphitheaterService.deleteAmphitheatersByListId(entityIds)
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

                            this.getPageAmphitheater(this.currentPage);
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
      debugger
        this.numberDeleteItems = 0;
        this.amphitheaterInfo.content.forEach(item => {
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

    search(amphitheater: Amphitheater, page: number) {
        this.filterObject = amphitheater;
        this.switchGetAmphitheater = true;
        debugger;
        this.amphitheaterService.advanceSearch(amphitheater, page)
            .then(amphitheaterInfo => {
                debugger;
                this.amphitheaterInfo = amphitheaterInfo.data;
                this.amphitheaters = this.amphitheaterInfo.content;
                this.pageLength = this.amphitheaterInfo.content.length;
                this.totalElements = this.amphitheaterInfo.totalElements;
                this.totalPages = this.amphitheaterInfo.totalPages;
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
    getNumberDeleteItems(): number {
        return this.numberDeleteItems;
    }
}
