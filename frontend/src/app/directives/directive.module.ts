import { NgModule } from "@angular/core";
import { EmailPatternValidatorDirective } from './email-pattern-validator.directive';
import { PasswordStrenthValidatorDirective } from './password-strenth-validator.directive';

@NgModule({
    declarations:[
    EmailPatternValidatorDirective,
    PasswordStrenthValidatorDirective
  ],
    exports:[
      EmailPatternValidatorDirective,
      PasswordStrenthValidatorDirective
    ]
})
export class DirectiveModule {}