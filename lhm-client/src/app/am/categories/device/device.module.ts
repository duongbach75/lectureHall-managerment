import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule, Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DataTableModule } from "angular2-datatable";
import { ToastModule } from 'ng2-toastr/ng2-toastr';
import { TranslateModule, TranslateLoader, MissingTranslationHandler } from '@ngx-translate/core';
import { createTranslateLoader, CustomHandler } from '../../../i18n-setting';
import { HttpClient } from '@angular/common/http';

import { DeviceBusinessComponent } from './device-business/device-business.component';
import { DeviceListComponent } from './device-list.component';
import { DeviceDetailComponent } from './device-detail/device-detail.component';

import { SelectModule } from 'ng2-select';
const routes: Routes = [

  { path: '', component: DeviceListComponent, pathMatch: 'full' },
  { path: 'detail/:id', component: DeviceDetailComponent, pathMatch: 'full' },
  { path: ':business/:id', canActivate: [], component: DeviceBusinessComponent, pathMatch: 'full' },
  { path: ':business', canActivate: [], component: DeviceBusinessComponent, pathMatch: 'full' },
]

@NgModule({
  imports: [
    SelectModule,
    RouterModule.forChild(routes),
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    DataTableModule,
    ToastModule.forRoot(),
    TranslateModule.forChild({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      },
      missingTranslationHandler: { provide: MissingTranslationHandler, useClass: CustomHandler },
      isolate: false
    })
  ],
  declarations: [
    DeviceListComponent,
    DeviceBusinessComponent,
    DeviceDetailComponent,
    DeviceDetailComponent,
    DeviceListComponent,
  ],
  exports: [RouterModule],
  providers: []
})
export class DeviceModule { }
