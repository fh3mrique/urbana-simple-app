import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { IUser } from '../../interfaces/user/user.interface';

@Component({
  selector: 'app-users-form',
  templateUrl: './users-form.component.html',
  styleUrl: './users-form.component.scss'
})
export class UsersFormComponent implements OnInit, OnChanges {
  @Input() userSelected: IUser = {} as IUser;
  
  
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
}
