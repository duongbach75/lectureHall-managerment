import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { Toast } from 'ng2-toastr';
import { TranslateService } from '@ngx-translate/core';
import { browser } from 'protractor';
import { ClassroomService } from '../classroom.service';
import { ClassroomForm } from '../classroom-form.component';
import { Classroom } from '../classroom';
import { AmphitheaterService } from '../../amphitheater/amphitheater.service';
import { Amphitheater } from '../../amphitheater/amphitheater';
import { Action } from 'rxjs/scheduler/Action';
import { Constants } from '../../../common/util/constants';

@Component({
  selector: 'app-classroom-business',
  templateUrl: './classroom-business.component.html',
  providers: [ClassroomService, AmphitheaterService]
})

/**
 * @description: Component management create, update
 */
export class ClassroomBusinessComponent implements OnInit {
  private sub: any;
  /**the id of object */
  id: number;
  idAmphitheater: number;
  amphitheater: Amphitheater;
  /** the name of business */
  business: string;
  /** the form object */
  classroomForm: FormGroup;
  classroom: Classroom;
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
    private classroomService: ClassroomService,
    private fb: FormBuilder,
    private translate: TranslateService,
    private amphitheaterService: AmphitheaterService,
    public toastr: ToastsManager, vcr: ViewContainerRef
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
    // Lấy bản ghi theo 'id' từ @PathParamf

    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      this.business = params['business'];
      this.classroomForm = ClassroomForm.classroomForm(this.fb, this.business);
      if (this.business == 'create') {
        debugger
        this.isUpdate = false;
        this.getListAmphitheater();
        // console.log()
      }
      if (this.business == 'update') {
        this.isUpdate = true;
        this.getListAmphitheater();
        this.bindingData(this.classroomForm, this.id);
      }
    });
  }

  isEqualOld(thenew, type) {
    try {
      var old;
      if(type == "name") {
        old = this.classroom.nameClassroom;
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


  bindingData(classroomForm, id) {
    this.classroomService.findOne(id)
    .then(response => {
      debugger
      this.classroom = JSON.parse(JSON.stringify(response.data));
      ClassroomForm.bindingData(classroomForm, this.classroom);
      this.getListAmphitheater();
      // this.getListClassroom();
      })
      .catch(error => console.log("errors: " + error));

  }

  /**
   * @description : submit data
   * @param Province : the data
   */
  submit(Classroom) {
    if (this.isUpdate) {
      console.log(Classroom);
      debugger
      this.updateClassroom(Classroom);
    } else {
      this.createClassroom(Classroom);
    }
  }

  /**
   * Creat a new object
   */
  private createClassroom(Classroom) {

    this.classroomService.create(Classroom)
    .then(response => {
      debugger
        this.getListAmphitheater();
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
  private updateClassroom(Classroom) {
    this.classroomService.update(Classroom)
      .then(response => {
        this.getListAmphitheater();
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
    if (this.classroomForm.get('nameClassroom').invalid) {
      if (this.classroomForm.get('nameClassroom').errors.required) {
        return false;
      }
      if (this.classroomForm.get('nameClassroom').errors.pattern != null) {
        return false;
      }
      if (this.classroomForm.get('nameClassroom').errors.maxlength != null) {
        return false;
      }
    }
  if (this.classroomForm.get('symbol').invalid) {
    if (this.classroomForm.get('symbol').errors.required) {
      return false;
    }
    if (this.classroomForm.get('symbol').errors.pattern != null) {
      return false;
    }
    if (this.classroomForm.get('symbol').errors.maxlength != null) {
      return false;
    }
  }

    // check Province name is valid
    if (this.classroomForm.get('size').invalid) {
      if (this.classroomForm.get('size').errors.required) {
        return false;
      }
      if (this.classroomForm.get('size').errors.pattern != null) {
        return false;
      }
      if (this.classroomForm.get('size').errors.maxlength != null) {
        return false;
    }
  }

    // // check Province name is valid
    if (this.classroomForm.get('chucNang').invalid) {
        if (this.classroomForm.get('chucNang').errors.required) {
          return false;
        }
        if (this.classroomForm.get('chucNang').errors.pattern != null) {
          return false;
        }
        if (this.classroomForm.get('chucNang').errors.maxlength != null) {
          return false;
       }
    }

    // check Province name is valid
    if (this.classroomForm.get('amount').invalid) {
      if (this.classroomForm.get('amount').errors.required) {
        return false;
      }
      if (this.classroomForm.get('amount').errors.pattern != null) {
        return false;
      }
      if (this.classroomForm.get('amount').errors.maxlength != null) {
        return false;
     }
  }

    return true;

  }

  private getListAmphitheater() {
    this.amphitheaterService.getListAmphitheater()
      .then(response => {
        this.listAmphitheater = response.data;

          this.initializeAmphitheaterSelection(0);


      }).catch(error => {
        console.log(error)
      });
}

private initializeAmphitheaterSelection(selectItem: number) {
  debugger
    let amphitheater_datas = []
    var countItems = 0;
    if (this.listAmphitheater) {
      this.listAmphitheater.forEach(element => {
        var item = {
          id: null, text: null
        };
        item.text = element.nameAmphitheater
        item.id = element.idAmphitheater;
        amphitheater_datas.push(item)
        if(this.classroom!=undefined&&this.classroom!=null&&item.id == this.classroom.amphitheater.idAmphitheater){
          // this.indexPolicySelection = countItems;
          this.indexAmphitheaterSelection = countItems
        }
        countItems += 1
      });
    }
    this.amphitheaterSelections = amphitheater_datas
    console.log(this.amphitheaterSelections)
}

amphitheaterChanged(id: number) {
    // this.ClassroomForm.get('classroom').setValue(new Classroom())
    debugger
    this.classroomForm.get('amphitheater.idAmphitheater').setValue(id)
    if(!id) {
      debugger
        this.listClassroom = []
        this.classroomSelections = []
        this.indexClassroomSelection = 0
        this.amphitheaterChanged(0)
    } else {
        // this.getListAmphitheater()
    }

  }

    public refreshPolicyValue(value:any):void {
      this.indexAmphitheaterSelection = null;
      this.classroomForm.get('amphitheater.idAmphitheater').setValue(null);
    }




// private getListClassroom(classRoomId: number) {
//     this.classroomService.findOne(this.id)
//     .then(response => {
//         this.listClassroom = response.data
//         this.initializeClassroomSelection(0)
//     }).catch(error => {
//         console.log(error)
//     });
// }

// private initializeClassroomSelection(selectItem: number) {
//     let classroom_datas = []
//     var countItems = 0;
//     if (this.listClassroom) {
//       this.listClassroom.forEach(element => {
//         var item = {
//           id: null, text: null
//         };
//         item.text = element.classRoomName
//         item.id = element.classroomId;
//         classroom_datas.push(item)
//         if (item.id == selectItem) {
//           this.indexClassroomSelection = countItems
//         }
//         countItems += 1
//       });
//     }
//     this.classroomSelections = classroom_datas
//     console.log(this.classroomSelections)
// }

// classroomChanged(id: number) {
//     debugger
//     this.ClassroomForm.get('classroom.classRoomId').setValue(id)

// }

  /**
   * return to the previous page
  */
 goBack() {
    this.location.back();
  }
}
