import { Component } from '@angular/core';
import { Credencials } from '../../types/login/credencials.interface';
import { FormControl, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {


  constructor(private readonly _authService: AuthService, private router: Router) {

  }

  creds: Credencials = {
    email: '',
    password: ''
  }

  email = new FormControl(null, Validators.email);
  password = new FormControl(null, Validators.minLength(4));


  validatedFields(): boolean {
    if (this.email.valid && this.password.valid) {
      return true;
    }
    else {
      return false;
    }
  }

  login() {
    if (this.validatedFields()) {
      this._authService.authenticate(this.creds).subscribe(response => {
        const authHeader = response.headers.get('Authorization');
        if (authHeader) {
          this._authService.successfulLogin(authHeader.substring(7));
          this.router.navigate(['']);
        } else {
          console.error('Authorization header not found in response.');
        }
      }, error => {
        console.error('Error during login:', error);
      });
    } else {
      console.log('Invalid login fields');
    }
  }
}

