import { Component, OnInit, ViewContainerRef } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";

import { StaffInchargeService } from "./staff-incharge.service";
import { DialogService } from "../../common/dialog/dialog.service";
import { DataTable } from "angular2-datatable";
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { Toast } from "ng2-toastr";

import { StaffInchargePageInfo } from "./staffInchargePageInfo";
import { StaffIncharge } from "./staff-incharge";
import { TranslateService } from "@ngx-translate/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-staff-incharge",
  templateUrl: "./staff-incharge-list.component.html",
  providers: [StaffInchargeService, DialogService, DataTable]
})
export class StaffInchargeListComponent implements OnInit {
  
  staffInchargePageInfo: StaffInchargePageInfo;

  currentPage = 0;
  filterForm: FormGroup;
  // search restriction
  searchObject: StaffIncharge;
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

  // list staffIncharge to export file excel
  staffIncharges: StaffIncharge[];
  fromNumber: number;
  toNumber: number;
  filterObject: StaffIncharge;
  

  constructor(private staffInchargeService: StaffInchargeService) {}

  ngOnInit() {
    
  }

  getPageStaffIncharge(staffIncharge: StaffIncharge, page: number){
    this.searchObject = staffIncharge;

    this.staffInchargeService.getPageStaffIncharge(staffIncharge,page)
      .then(response=>{
        this.staffInchargePageInfo = response.;
        console.log(this.staffInchargePageInfo.content);
        this.pageLength = this.staffInchargePageInfo.content.length;
        this.totalElements = this.staffInchargePageInfo.totalElements;
        this.totalPages = this.staffInchargePageInfo.totalPages;
        if(!(this.totalPages>0)){
          this.currentPage = -1;
        }

        this.setCurrentPage();
        
      }).catch(
        error => {
            console.log(error);
        });
  }

  /**
   * set the information of the page
   */
  private setCurrentPage(){
    if(this.staffInchargePageInfo.numberOfElements > 0){
      this.currentPageView = this.staffInchargePageInfo.number + 1;
    } else {
      this.currentPageView = 0;
    }
    var numberOfElements = this.staffInchargePageInfo.numberOfElements;
        var size = this.staffInchargePageInfo.size;
        this.fromNumber = (this.currentPageView - 1) * size + 1;
        this.toNumber = (this.currentPageView - 1) * size + numberOfElements;
        if (this.toNumber < 1) {
            this.fromNumber = 0;
            this.toNumber = 0;
        }
  }

  /**
   * Count the number of objects checked
   */
  countNumberDeleteItems(){
    this.numberDeleteItems = 0;
    this.staffInchargePageInfo.content.forEach(item=>{
      if(item.checked){
        this.numberDeleteItems += 1;
      }
    });
  }

  
}
