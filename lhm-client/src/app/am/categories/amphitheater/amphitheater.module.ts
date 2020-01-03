import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule, Router } from '@angular/router';
import { AmphitheaterListComponent } from './amphitheater-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DataTableModule } from "angular2-datatable";

import { ToastModule } from 'ng2-toastr/ng2-toastr';
import { TranslateModule, TranslateLoader, MissingTranslationHandler } from '@ngx-translate/core';
import { createTranslateLoader, CustomHandler } from '../../../i18n-setting';
import { HttpClient } from '@angular/common/http';
import { AmphitheaterDetailComponent } from './amphitheater-detail/Amphitheater-detail.component';
import { AmphitheaterBusinessComponent } from './amphitheater-business/amphitheater-business.component';
import { ResponseMessageModule } from '../../common/util/response-message/response-message.module';

const routes: Routes = [

  { path: '', component: AmphitheaterListComponent, pathMatch: 'full' },
  { path: 'detail/:id', component: AmphitheaterDetailComponent, pathMatch: 'full' },
  { path: ':business/:id', canActivate: [], component: AmphitheaterBusinessComponent, pathMatch: 'full' },
  { path: ':business', canActivate: [], component: AmphitheaterBusinessComponent, pathMatch: 'full' },
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
  declarations: [
    AmphitheaterListComponent,
    AmphitheaterBusinessComponent,
    AmphitheaterDetailComponent,
  ],
  exports: [RouterModule],
  providers: []
})
export class AmphitheaterModule { }
