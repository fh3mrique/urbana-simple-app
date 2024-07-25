import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, Validators } from '@angular/forms';
import { IUser } from '../../../types/user/IUser';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrl: './user-update.component.css'
})
export class UserUpdateComponent implements OnInit {
  user: IUser = {
    name: '',
    email: '',
    password: '',
    boardingPasses: []
  }

  displayedColumns: string[] = ['id', 'typeBoardingPass', 'number', 'status', 'actions'];

  name: FormControl = new FormControl(null, Validators.minLength(3));
  email: FormControl = new FormControl(null, Validators.email);
  password: FormControl = new FormControl(null, Validators.minLength(3));

  constructor(
    private readonly _userService: UserService,
    private readonly _router: Router,
    private readonly _route: ActivatedRoute,
    private _snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.user.id = this._route.snapshot.paramMap.get('id');
    this.findByid();
  }

  validateFields(): boolean {
    return !this.name.valid || !this.email.valid || !this.password.valid;
  }

  findByid(): void {
    this._userService.findById(this.user.id).subscribe(response => {
      this.user = response;
    })
  }

  update(): void {
    this._userService.update(this.user).subscribe(response => {
      console.log(response);
      this._snackBar.open("Usuário atualizado com sucesso", 'Fechar')
      this._router.navigate(["users"])
    }, err => {
      console.log(err);
    })
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

  toggleBoardingPassStatus(pass: any): void {
    const newStatus = !pass.status;
    if (newStatus) {
      this._userService.activateBoardingPass(this.user.id, pass.id).subscribe(() => {
        pass.status = newStatus;
        this._snackBar.open("Cartão Ativado", "Fechar", {
          duration: 2000,
        })
      });
    } else {
      this._userService.deactivateBoardingPass(this.user.id, pass.id).subscribe(() => {
        pass.status = newStatus;
        this._snackBar.open("Cartão Desativado", "Fechar", {
          duration: 2000,
        })
      });
    }
  }

  deleteBoardingPass(pass: any): void {
    this._userService.deleteBoardingPass(this.user.id, pass.id).subscribe(() => {
      this.user.boardingPasses = this.user.boardingPasses?.filter(bp => bp.id !== pass.id);
      this._snackBar.open("Cartão excluido com sucesso.", "Fechar")
    });
  }

  handleActionClick(event: MouseEvent): void {
    event.preventDefault();
    event.stopPropagation();
  }
}
