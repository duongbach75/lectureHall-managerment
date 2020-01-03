import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule} from '@angular/router';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DataTableModule } from "angular2-datatable";
import { ResponseMessageModule } from '../../common/util/response-message/response-message.module';
import { ToastModule } from 'ng2-toastr/ng2-toastr';
import { TranslateModule, TranslateLoader, MissingTranslationHandler } from '@ngx-translate/core';
import { createTranslateLoader, CustomHandler } from '../../../i18n-setting';
import { HttpClient } from '@angular/common/http';

const routes: Routes = [
  // { path: '', component: FacultyListComponent, pathMatch: 'full'},
]

@NgModule({
  imports: [
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
  declarations: [],
  exports: [RouterModule],
    providers: []
    // providers: [AuthGuardSubmenu]
})
export class StaffInchargeModule { }
