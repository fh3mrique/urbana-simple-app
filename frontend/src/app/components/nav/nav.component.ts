import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from '../../types/user/IUser';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})
export class NavComponent implements OnInit {
  userSelected: IUser = {} as IUser;
  constructor(private readonly _router: Router){}

  ngOnInit(): void {
    this._router.navigate(['users'])
  }

 

}
