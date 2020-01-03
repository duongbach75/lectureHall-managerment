import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule, Router } from '@angular/router';
import { ClassroomListComponent } from './classroom-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DataTableModule } from "angular2-datatable";

import { ToastModule } from 'ng2-toastr/ng2-toastr';
import { TranslateModule, TranslateLoader, MissingTranslationHandler } from '@ngx-translate/core';
import { createTranslateLoader, CustomHandler } from '../../../i18n-setting';
import { HttpClient } from '@angular/common/http';
import { ClassroomDetailComponent } from './classroom-detail/Classroom-detail.component';


import { SelectModule } from 'ng2-select';
import { ClassroomBusinessComponent } from './classroom-business/classroom-business.component';
import { ResponseMessageModule } from '../../common/util/response-message/response-message.module';

const routes: Routes = [

  { path: '', component: ClassroomListComponent, pathMatch: 'full' },
  { path: 'detail/:id', component: ClassroomDetailComponent, pathMatch: 'full' },
  { path: ':business', canActivate: [], component: ClassroomBusinessComponent, pathMatch: 'full' },
  { path: ':business/:id', canActivate: [], component: ClassroomBusinessComponent, pathMatch: 'full' },
]

@NgModule({
  imports: [
    SelectModule,
    RouterModule.forChild(routes),
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    ResponseMessageModule,
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
    ClassroomListComponent,
    ClassroomBusinessComponent,
    ClassroomDetailComponent,
  ],
  exports: [RouterModule],
  providers: []
})
export class ClassroomModule { }
