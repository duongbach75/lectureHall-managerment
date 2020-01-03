

import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { Toast } from 'ng2-toastr';
import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { AmphitheaterService } from '../amphitheater.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Amphitheater } from '../amphitheater';


@Component({
  selector: 'app-amphitheater-detail',
  templateUrl: './amphitheater-detail.component.html',
  providers: [AmphitheaterService]
})

/**
 * @description: Component management show detail
 */
export class AmphitheaterDetailComponent implements OnInit {
  private sub: any;
  id: number;
  amphitheater: Amphitheater;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private amphitheaterService: AmphitheaterService,
    public vcr: ViewContainerRef
  ) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      this.amphitheaterService.findOne(this.id)
        .then(response => {
          this.amphitheater = response.data;
        })
        .catch(error => {
          console.log("errors: " + error);
        })
    });
  }
  goBack() {
    this.location.back();
  }

}
