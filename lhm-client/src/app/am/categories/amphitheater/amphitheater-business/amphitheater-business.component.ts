import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { Toast } from 'ng2-toastr';
import { TranslateService } from '@ngx-translate/core';
import { browser } from 'protractor';

import { Amphitheater } from '../Amphitheater';
import { Constants } from '../../../common/util/constants';
import { AmphitheaterForm } from '../amphitheater-form.component';
import { AmphitheaterService } from '../amphitheater.service';

@Component({
  selector: 'app-Amphitheater-business',
  templateUrl: './Amphitheater-business.component.html',
  providers: [AmphitheaterService]
})

/**
 * @description: Component management create, update
 */
export class AmphitheaterBusinessComponent implements OnInit {
  private sub: any;
  /**the id of object */
  id: number;
  /** the name of business */
  business: string;
  /** the form object */
  AmphitheaterForm: FormGroup;
  Amphitheater: Amphitheater;

  isUpdate: boolean = true;

  listStatus = Constants.STATUS_LIST;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private amphitheaterService: AmphitheaterService,
    private fb: FormBuilder,
    private translate: TranslateService,
    public toastr: ToastsManager, vcr: ViewContainerRef
  ) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
    // Lấy bản ghi theo 'id' từ @PathParam
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      this.business = params['business'];
      this.AmphitheaterForm = AmphitheaterForm.AmphitheaterForm(this.fb, this.business);
      if (this.business == 'create') {
        this.isUpdate = false;
      }
      if (this.business == 'update') {
        this.isUpdate = true;
        this.bindingData(this.AmphitheaterForm, this.id);
      }
    });
  }

  isEqualOld(thenew, type) {
    try {
      var old;
      if(type == "name") {
        old = this.Amphitheater.nameAmphitheater;
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


  bindingData(amphitheaterForm, id) {
    debugger
    this.amphitheaterService.findOne(id)
      .then(response => {
        debugger
        this.Amphitheater = JSON.parse(JSON.stringify(response.data));
        AmphitheaterForm.bindingData(amphitheaterForm, this.Amphitheater);
      })
      .catch(error => console.log("errors: " + error));
  }

  /**
   * @description : submit data
   * @param Province : the data
   */
  submit(Amphitheater) {
    if (this.isUpdate) {
      console.log(Amphitheater);
      debugger
      this.updateAmphitheater(Amphitheater);
    } else {
      this.createAmphitheater(Amphitheater);
    }
  }

  /**
   * Creat a new object
   */
  private createAmphitheater(Amphitheater) {
    debugger
    this.amphitheaterService.create(Amphitheater)
      .then(response => {
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
  private updateAmphitheater(Amphitheater) {
    this.amphitheaterService.update(Amphitheater)
      .then(response => {

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
    if (this.AmphitheaterForm.get('nameAmphitheater').invalid) {
      if (this.AmphitheaterForm.get('nameAmphitheater').errors.required) {
        return false;
      }
      if (this.AmphitheaterForm.get('nameAmphitheater').errors.pattern != null) {
        return false;
      }
      if (this.AmphitheaterForm.get('nameAmphitheater').errors.maxlength != null) {
        return false;
      }
    }


    return true;
  }

  /**
   * return to the previous page
  */
  goBack() {
    this.location.back();
  }
}
