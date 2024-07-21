import { NgModule } from "@angular/core";
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatDividerModule} from '@angular/material/divider';
import { MatTableModule } from '@angular/material/table';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatIconModule} from '@angular/material/icon';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';




@NgModule(
  {
    imports: [MatCardModule, MatFormFieldModule, MatInputModule, MatDividerModule, MatTableModule, 
      MatSnackBarModule,MatIconModule, MatDialogTitle, MatDialogContent, MatDialogActions, MatDialogClose],
    exports: [MatCardModule, MatFormFieldModule, MatInputModule, 
      MatDividerModule, MatTableModule, MatSnackBarModule, MatIconModule, MatDialogTitle, MatDialogContent, MatDialogActions, MatDialogClose],
  }
)
export class AngularMaterialModule { }