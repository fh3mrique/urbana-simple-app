import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { IUser } from '../../../types/user/IUser';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-users-create',
  templateUrl: './users-create.component.html',
  styleUrl: './users-create.component.css'
})
export class UsersCreateComponent {

  user: IUser = {
    name: '',
    email: '',
    password: '',
  }

  name: FormControl = new FormControl(null, Validators.minLength(3));
  email: FormControl = new FormControl(null, Validators.email);
  password: FormControl = new FormControl(null, Validators.minLength(3));
  
  constructor(
    private readonly _userService: UserService,
    private readonly _route: Router,
  ){}
  
  validateFields(): boolean {
    return !this.name.valid && this.email.valid && this.password.valid;
  }

  create(): void {
    this._userService.create(this.user).subscribe( response =>{
      console.log(response);
      this._route.navigate(["users"])
    }, err =>{
      console.log(err);
    })
  }
}
