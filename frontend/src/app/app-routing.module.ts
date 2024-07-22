import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavComponent } from './components/nav/nav.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { UsersListComponent } from './components/user/users-list/users-list.component';
import { UsersCreateComponent } from './components/user/users-create/users-create.component';
import { UserUpdateComponent } from './components/user/user-update/user-update.component';
import { UsersDeleteComponent } from './components/user/users-delete/users-delete.component';

const routes: Routes = [
{
  path: 'login', component: LoginComponent
},
{
  path: '', component: NavComponent, children:[
    {path: "home", component: HomeComponent},
    {path: "users", component: UsersListComponent},
    {path: "users/create", component: UsersCreateComponent},
    {path: "users/update/:id", component: UserUpdateComponent},
  ]
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
