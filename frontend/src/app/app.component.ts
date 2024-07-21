import { Component, OnInit } from '@angular/core';
import { UserService } from './services/users.service';
import { boardingPassService } from './services/boading-pass.service';
import { UserListResponse } from './types/users-list-response';
import { IUser } from './interfaces/user/user.interface';

import { UserBeforeAndAfterDialogComponent } from './components/user-before-and-after-dialog/user-before-and-after-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {

  userSelected: IUser = {} as IUser;
  userSelectedIndex: number | undefined;

  usersList: UserListResponse = [];
  boardingPassList: any = [];

  constructor(
    private readonly _usersService: UserService,
    private readonly _usersBoadingPass: boardingPassService,
    private readonly _matDialog: MatDialog,
  ) { }

  ngOnInit() {
    this.getUsers()
  }

  onUserSelected(userIndex: number) {
    const userFound = this.usersList[userIndex];

    if (userFound) {
      this.userSelectedIndex = userIndex;
      this.userSelected = structuredClone(userFound);
    }
  }

  onFormSubmit() {
    if (this.userSelectedIndex === undefined) return;

    const originalUser = this.usersList[this.userSelectedIndex];
    this.opeBeforeAndDialog(originalUser, this.userSelected, this.userSelectedIndex);
  }

  opeBeforeAndDialog(originalUser: IUser, updatedUser: IUser, userSelectedIndex: number) {
    const dialogRef = this._matDialog.open(UserBeforeAndAfterDialogComponent, {
      data:{
        originalUser: originalUser,
        updatedUser: updatedUser
      },
      minWidth: '80%',
    }
    )

    dialogRef.afterClosed().subscribe((result) => {
      if(result){
        this.confirmUserUpdate(updatedUser, userSelectedIndex)
      }
    })
  }

  confirmUserUpdate(updatedUser: IUser, userSelectedIndex: number) {
      this.usersList[userSelectedIndex] = structuredClone(updatedUser);

      console.group('ALTERAÇÃO FINALIZADA - Lista de usúarios atualizada');

      console.log('Lista de usuários atual', this.usersList)
      console.groupEnd()
  }

  private getUsers() {
    this._usersService.getUsers().subscribe((usersListResponse) => {
      this.usersList = usersListResponse;
    });
  }

  private getBoadingPasses() {
    this._usersBoadingPass.getBoardingPasses().subscribe((boardingPassResponse) => {
      this.boardingPassList = boardingPassResponse;
    })
  }
}
