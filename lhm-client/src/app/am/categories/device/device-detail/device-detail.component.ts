import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { Toast } from 'ng2-toastr';
import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { DeviceService } from '../device.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Device } from '../device';


@Component({
  selector: 'app-device-detail',
  templateUrl: './device-detail.component.html',
  providers: [DeviceService]
})

/**
 * @description: Component management show detail
 */
export class DeviceDetailComponent implements OnInit {
  private sub: any;
  id: number;
  device: Device;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private deviceService: DeviceService,
    public vcr: ViewContainerRef
  ) { }

  ngOnInit() {
    debugger
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      this.deviceService.findOne(this.id)
        .then(response => {
          this.device = response.data;
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
