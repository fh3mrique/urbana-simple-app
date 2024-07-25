import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatusLabelPipe } from './status-label.pipe';



@NgModule({
  declarations: [
    StatusLabelPipe
  ],
  imports: [
    CommonModule
  ],
  exports:[
    StatusLabelPipe
  ]
})
export class PipesModule { }
