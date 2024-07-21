import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ElementRef } from '@angular/core';
import { IUser } from '../../interfaces/user/user.interface';
import { UserService } from '../../services/users.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-users-form',
  templateUrl: './users-form.component.html',
  styleUrls: ['./users-form.component.scss']
})
export class UsersFormComponent implements OnInit, OnChanges {

  @Input() userSelected: IUser = {} as IUser;

  @Output('onFormSubmit') onFormSubmitEmitt = new EventEmitter<IUser>();

  displayedColumns: string[] = ['id', 'typeBoardingPass', 'number', 'status', 'actions' ];

  constructor(
    private userService: UserService, 
    private snackBar: MatSnackBar, 
    private readonly _elRef: ElementRef
  ) {}
  
  ngOnInit() {}

  ngOnChanges(changes: SimpleChanges): void {
    console.log("userSelected", this.userSelected);
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

  onFormSubmit(form: NgForm) {
    if (form.invalid) {
      this.focusOnInvalidControl(form);
      return;
    }

    this.onFormSubmitEmitt.emit(this.userSelected);
  }

  focusOnInvalidControl(form: NgForm) {
    for (const control of Object.keys(form.controls)) {
      if(form.controls[control].invalid) {
        const invalidControl: HTMLElement = this._elRef.nativeElement.querySelector(`[name=${control}]`);
        invalidControl.focus();
        break;
      }
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

  handleActionClick(event: MouseEvent): void {
    event.preventDefault();
    event.stopPropagation();
  }
}
