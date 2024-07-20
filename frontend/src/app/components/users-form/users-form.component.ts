import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { IUser } from '../../interfaces/user/user.interface';
import { UserService } from '../../services/users.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-users-form',
  templateUrl: './users-form.component.html',
  styleUrl: './users-form.component.scss'
})
export class UsersFormComponent implements OnInit, OnChanges {
  @Input() userSelected: IUser = {} as IUser;

  displayedColumns: string[] = ['id', 'typeBoardingPass', 'number', 'status', 'actions' ];

  constructor(private userService: UserService, private snackBar: MatSnackBar) {}
  
  
  ngOnInit() {
  }
  ngOnChanges(changes: SimpleChanges): void {
    console.log("userSelected", this.userSelected)
  }

  getImageForType(type: string): string {
    switch (type) {
      case 'TRABALHADOR':
        return 'https://www.urbana-pe.com.br/wp-content/uploads/2019/09/cartao-vem-trabalhador.png';
      case 'COMUM':
        return 'https://www.urbana-pe.com.br/wp-content/uploads/2019/09/cartao-vem-comum.png';
      case 'ESTUDANTE':
        return 'https://www.urbana-pe.com.br/wp-content/uploads/2019/09/cartao-vem-estudante.png';
      default:
        return 'path/to/default-image.png';
    }
  }

  deleteBoardingPass(boardingPassId: number): void {
    if (this.userSelected && this.userSelected.id) {
      this.userService.deleteBoardingPass(this.userSelected.id, boardingPassId).subscribe(() => {
        this.snackBar.open('Cartão excluído com sucesso!', 'Fechar', { duration: 3000 });
        this.userSelected.boardingPasses = this.userSelected.boardingPasses.filter(pass => pass.id !== boardingPassId);
      });
    }
  }

  toggleBoardingPassStatus(boardingPassId: number, status: boolean): void {
    if (this.userSelected && this.userSelected.id) {
      if (status) {
        this.userService.deactivateBoardingPass(this.userSelected.id, boardingPassId).subscribe(() => {
          this.snackBar.open('Cartão desativado com sucesso!', 'Fechar', { duration: 3000 });
          this.updateBoardingPassStatus(boardingPassId, false);
        });
      } else {
        this.userService.activateBoardingPass(this.userSelected.id, boardingPassId).subscribe(() => {
          this.snackBar.open('Cartão ativado com sucesso!', 'Fechar', { duration: 3000 });
          this.updateBoardingPassStatus(boardingPassId, true);
        });
      }
    }
  }

  private updateBoardingPassStatus(boardingPassId: number, status: boolean): void {
    const pass = this.userSelected.boardingPasses.find(pass => pass.id === boardingPassId);
    if (pass) {
      pass.status = status;
    }
  }
}
