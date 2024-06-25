import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import {FormsModule, NgControl} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {Store} from "@ngrx/store";

@NgModule({
  declarations: [],
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,
    FormsModule
  ],
  providers: [Store],
  bootstrap: []
})
export class AppModule { }
