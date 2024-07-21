import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ElementRef } from '@angular/core';
import { IUser } from '../../interfaces/user/user.interface';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-users-form',
  templateUrl: './users-form.component.html',
  styleUrls: ['./users-form.component.scss']
})
export class UsersFormComponent implements OnInit, OnChanges {

  @Input() userSelected: IUser = {} as IUser;
  @Output() onFormSubmit = new EventEmitter<IUser>();
  @Output() onDeleteBoardingPass = new EventEmitter<number>();
  @Output() onToggleBoardingPassStatus = new EventEmitter<{ boardingPassId: number, status: boolean }>();

  displayedColumns: string[] = ['id', 'typeBoardingPass', 'number', 'status', 'actions' ];

  constructor(private readonly _elRef: ElementRef) {}

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

  submitForm(form: NgForm) { // Renomeie aqui
    if (form.invalid) {
      this.focusOnInvalidControl(form);
      return;
    }

    this.onFormSubmit.emit(this.userSelected);
  }

  focusOnInvalidControl(form: NgForm) {
    for (const control of Object.keys(form.controls)) {
      if (form.controls[control].invalid) {
        const invalidControl: HTMLElement = this._elRef.nativeElement.querySelector(`[name=${control}]`);
        invalidControl.focus();
        break;
      }
    }
  }

  deleteBoardingPass(boardingPassId: number): void {
    this.onDeleteBoardingPass.emit(boardingPassId);
  }

  toggleBoardingPassStatus(boardingPassId: number, status: boolean): void {
    this.onToggleBoardingPassStatus.emit({ boardingPassId, status });
  }

  handleActionClick(event: MouseEvent): void {
    event.preventDefault();
    event.stopPropagation();
  }
}
