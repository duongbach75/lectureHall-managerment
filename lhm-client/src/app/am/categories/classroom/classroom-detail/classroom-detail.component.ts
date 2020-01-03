

import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { Toast } from 'ng2-toastr';
import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { ClassroomService } from '../classroom.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Classroom } from '../classroom';
import { DeviceService } from './../../device/device.service';
import { Device } from './../../device/Device';


@Component({
  selector: 'app-classroom-detail',
  templateUrl: './classroom-detail.component.html',
  providers: [ClassroomService, DeviceService]
})

/**
 * @description: Component management show detail
 */
export class ClassroomDetailComponent implements OnInit {
  private sub: any;
  id: number;
  classroom: Classroom;
  device: Device

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private classroomService: ClassroomService,
    private deviceService: DeviceService,
    public vcr: ViewContainerRef
  ) { }

  ngOnInit() {
    debugger
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      this.classroomService.findOne(this.id)
        .then(response => {
          this.classroom = response.data;
        })
        .catch(error => {
          console.log("errors: " + error);
        }),
        this.deviceService.findClassromByDevice(this.id)
        .then(responseDevie => {
          this.device = responseDevie.data.content;
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
