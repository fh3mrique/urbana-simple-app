import { NgModule } from "@angular/core";
import { FormsModule } from '@angular/forms'
import { BrowserModule } from "@angular/platform-browser";
import { AngularMaterialModule } from "../angular-material.module";
import { DirectiveModule } from "../directives/directive.module";
import { PipesModule } from "../pipes/pipes.module";
import { UsersCardListComponent } from './users-card-list/users-card-list.component';
import { UsersFormComponent } from './users-form/users-form.component';
@NgModule(
    {
        declarations: [
            UsersCardListComponent,
            UsersFormComponent
        ],
        imports: [
            FormsModule,
            BrowserModule,
            AngularMaterialModule,
            DirectiveModule,
            PipesModule
        ],
        exports: [
            UsersCardListComponent,
            UsersFormComponent
        ],
    }
)
export class ComponentsModule { }