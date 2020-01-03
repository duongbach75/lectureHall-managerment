import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MdDialogModule, MdButtonModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { DialogService } from './am/common/dialog/dialog.service';

// import { AuthRoutingModule } from './authentication/auth-routing.module';
import { AppRoutingModule } from './app-routing.module';

import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { MissingTranslationHandler, MissingTranslationHandlerParams, TranslateCompiler, TranslateParser } from '@ngx-translate/core';
import {  CustomHandler, createTranslateLoader } from './i18n-setting';
import { LocationStrategy, PathLocationStrategy } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,

    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      },
      missingTranslationHandler: { provide: MissingTranslationHandler, useClass: CustomHandler },
      isolate: true
    }),
    FormsModule,
    HttpModule,
    MdDialogModule,
    MdButtonModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    // AuthRoutingModule,
  ],
  providers: [
    {
      provide: LocationStrategy, useClass: PathLocationStrategy
    },
  ],

  bootstrap: [AppComponent]

})
export class AppModule { }

