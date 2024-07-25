import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { BrowserModule } from "@angular/platform-browser";
import { AngularMaterialModule } from "../angular-material.module";
import { NavComponent } from './nav/nav.component';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from "../app-routing.module";
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { UsersCreateComponent } from './user/users-create/users-create.component';
import { UsersListComponent } from "./user/users-list/users-list.component";
import { UserUpdateComponent } from './user/user-update/user-update.component';
import { UsersDeleteComponent } from './user/users-delete/users-delete.component';
import { PipesModule } from "../pipes/pipes.module";
@NgModule(
    {
        declarations: [
            NavComponent,
            HomeComponent,
            HeaderComponent,
            LoginComponent,
            UsersCreateComponent,
            UsersListComponent,
            UserUpdateComponent,
            UsersDeleteComponent
        ],
        imports: [
            FormsModule,
            ReactiveFormsModule,
            BrowserModule,
            AngularMaterialModule,
            AppRoutingModule,
            /* aula 37 11.22min */
            /* ToastrModule  */
            PipesModule
        ],
        exports: [
            AngularMaterialModule,
            AppRoutingModule,
            PipesModule
        ],
    }
)
export class ComponentsModule { }