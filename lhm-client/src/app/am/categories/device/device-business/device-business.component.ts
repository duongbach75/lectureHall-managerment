import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { Toast } from 'ng2-toastr';
import { TranslateService } from '@ngx-translate/core';
import { browser } from 'protractor';
import { DeviceService } from '../device.service';
import { DeviceForm } from '../device-form.component';
import { Device } from '../device';
import { ClassroomService } from '../../classroom/classroom.service'
import { AmphitheaterService } from '../../amphitheater/amphitheater.service';
import { Amphitheater } from '../../amphitheater/amphitheater';
import { Action } from 'rxjs/scheduler/Action';
import { Constants } from '../../../common/util/constants';

@Component({
  selector: 'app-Device-business',
  templateUrl: './Device-business.component.html',
  providers: [DeviceService, AmphitheaterService, ClassroomService]
})

/**
 * @description: Component management create, update
 */
export class DeviceBusinessComponent implements OnInit {
  private sub: any;
  /**the id of object */
  id: number;
  idDevice: number;
  amphitheater: Amphitheater;
  /** the name of business */
  business: string;
  /** the form object */
  DeviceForm: FormGroup;
  Device: Device;
  AmphitheaterService: AmphitheaterService;
  isUpdate: boolean = true;
  listAmphitheater = null;
  indexAmphitheaterSelection = null;
  amphitheaterSelections = null;
  listClassroom = null;
  indexClassroomSelection = null;
  classroomSelections = null;
  listStatus = Constants.STATUS_LIST;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private deviceService: DeviceService,
    private fb: FormBuilder,
    private translate: TranslateService,
    private classroomService: ClassroomService,
    private amphitheaterService: AmphitheaterService,
    public toastr: ToastsManager, vcr: ViewContainerRef
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
    // Lấy bản ghi theo 'id' từ @PathParam

    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      this.business = params['business'];
      this.DeviceForm = DeviceForm.DeviceForm(this.fb, this.business);
      if (this.business == 'create') {
        debugger
        this.isUpdate = false;
        this.getListClassroom();
        // console.log()
      }
      if (this.business == 'update') {
        this.isUpdate = true;
        this.getListClassroom();
        this.bindingData(this.DeviceForm, this.id);
      }
    });
  }

  isEqualOld(thenew, type) {
    try {
      var old;
      if(type == "name") {
        old = this.Device.nameDeivce;
      }
      if(old != thenew && old == this.standardized(thenew, type)) {
        return false;
      } else return true;
    } catch(e) {}
  }
  standardized(thenew, type) {
    thenew = thenew.trim();
    if(type == "name") {
      thenew = thenew.split(" ").join("");
    } else {
      // console.log('xsa');
      thenew = thenew.replace(/\s+/g, ' ');
    }
    return thenew;
  }


  bindingData(deviceForm, id) {
    this.deviceService.findOne(id)
    .then(response => {
      debugger
      this.Device = JSON.parse(JSON.stringify(response.data));
      DeviceForm.bindingData(deviceForm, this.Device);
      // this.getListAmphitheater();
      this.getListClassroom();
      })
      .catch(error => console.log("errors: " + error));

  }

  /**
   * @description : submit data
   * @param Province : the data
   */
  submit(Device) {
    if (this.isUpdate) {
      console.log(Device);
      debugger
      this.updateDevice(Device);
    } else {
      this.createClassroom(Device);
    }
  }

  /**
   * Creat a new object
   */
  private createClassroom(Device) {

    this.deviceService.create(Device)
    .then(response => {
      debugger
        this.getListClassroom();
        this.goBack();
      })
      .catch(error => {
        let message;
        this.translate.get('Message.CreateFail').subscribe((res: string) => {
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

  /**
   * Update a object
   * @param Province
   */
  private updateDevice(Device) {
    this.deviceService.update(Device)
      .then(response => {
        // this.getListAmphitheater();
        this.goBack();
      })
      .catch(error => {
        let message;
        this.translate.get('Message.UpdateFail').subscribe((res: string) => {
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

  /**
   * Check data is valid
   */
  isValidForm() {

    // check Province name is valid
    if (this.DeviceForm.get('nameDevice').invalid) {
      if (this.DeviceForm.get('nameDevice').errors.required) {
        return false;
      }
      if (this.DeviceForm.get('nameDevice').errors.pattern != null) {
        return false;
      }
      if (this.DeviceForm.get('nameDevice').errors.maxlength != null) {
        return false;
      }
    }
    // check Province name is valid
    if (this.DeviceForm.get('amount').invalid) {
      if (this.DeviceForm.get('amount').errors.required) {
        return false;
      }
      if (this.DeviceForm.get('amount').errors.pattern != null) {
        return false;
      }
      if (this.DeviceForm.get('amount').errors.maxlength != null) {
        return false;
      }
    }

    return true;
  }

  private getListClassroom() {
    this.classroomService.getListClassroom()
        .then(response => {
            this.listClassroom = response.data;

            this.initializeClassroomSelection(0);


        }).catch(error => {
            console.log(error)
        });
}

private initializeClassroomSelection(selectItem: number) {
    debugger
    let classroom_datas = []
    var countItems = 0;
    if (this.listClassroom) {
        this.listClassroom.forEach(element => {
            var item = {
                id: null, text: null
            };
            item.text = element.nameClassroom
            item.id = element.idClassroom;
            classroom_datas.push(item)
            if (this.Device != undefined && this.Device != null && item.id == this.Device.classroom.idClassroom) {
                // this.indexPolicySelection = countItems;
                this.indexClassroomSelection = countItems
            }
            countItems += 1
        });
    }
    this.classroomSelections = classroom_datas
    console.log(this.classroomSelections)
}

classroomChanged(id: number) {
    // this.ClassroomForm.get('classroom').setValue(new Classroom())
    debugger
    this.DeviceForm.get('classroom.idClassroom').setValue(id)
    if (!id) {
        debugger
        this.listClassroom = []
        this.classroomSelections = []
        this.indexClassroomSelection = 0
        this.classroomChanged(0)
    } else {
        // this.getListClassroom()
    }

}

public refreshPolicyValue(value: any): void {
    this.indexClassroomSelection = null;
    this.DeviceForm.get('classRoom.idClassroom').setValue(null);
}

  /**
   * return to the previous page
  */
 goBack() {
    this.location.back();
  }
}
