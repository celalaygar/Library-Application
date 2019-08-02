import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserDetailComponent } from './user-detail.component';
import { UserRoutingModule } from './user.routing.module';
import { UserService } from 'src/app/services/user.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AlertifyService } from 'src/app/services/alertify.service';

@NgModule({
  declarations: [UserDetailComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers : [
    UserService,
    AlertifyService
  ]
})
export class UserModule { }
