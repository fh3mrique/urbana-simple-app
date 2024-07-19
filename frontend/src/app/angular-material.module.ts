import { NgModule } from "@angular/core";
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatDividerModule} from '@angular/material/divider';

@NgModule(
  {
    imports: [MatCardModule, MatFormFieldModule, MatInputModule, MatDividerModule],
    exports: [MatCardModule, MatFormFieldModule, MatInputModule, MatDividerModule],
  }
)
export class AngularMaterialModule { }