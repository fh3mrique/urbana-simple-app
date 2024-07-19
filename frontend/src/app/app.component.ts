import { Component, OnInit } from '@angular/core';
import { UserService } from './services/users.service';
import { boardingPassService } from './services/boading-pass.service';
import { UserListResponse } from './types/users-list-response';
import { IUser } from './interfaces/user/user.interface';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit{

  userSelected: IUser = {} as IUser;
  userSelectedIndex: number | undefined;

  usersList: UserListResponse = []; 
  boardingPassList: any = []; 

  constructor(
    private readonly _usersService: UserService,
    private readonly _usersBoadingPass: boardingPassService,
  ) {}

  ngOnInit() {
    this.getUsers()
  }

  onUserSelected(userIndex: number){
    const userFound = this.usersList[userIndex];

    if (userFound){
      this.userSelectedIndex = userIndex;
      this.userSelected = structuredClone(userFound);
    }
  }

  private getUsers() {
    this._usersService.getUsers().subscribe((usersListResponse) => {
      this.usersList = usersListResponse;
    });
  }

  private getBoadingPasses (){
    this._usersBoadingPass.getBoardingPasses().subscribe((boardingPassResponse) =>{
      this.boardingPassList = boardingPassResponse;
    })
  }
}
