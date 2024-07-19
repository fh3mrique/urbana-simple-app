import { NgModule } from "@angular/core";
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatDividerModule} from '@angular/material/divider';
import { MatTableModule } from '@angular/material/table';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatIconModule} from '@angular/material/icon';




@NgModule(
  {
    imports: [MatCardModule, MatFormFieldModule, MatInputModule, MatDividerModule, MatTableModule, MatSnackBarModule,MatIconModule],
    exports: [MatCardModule, MatFormFieldModule, MatInputModule, MatDividerModule, MatTableModule, MatSnackBarModule, MatIconModule],
  }
)
export class AngularMaterialModule { }