import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { UserService } from 'src/app/services/user.service';
import { User } from './User';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {
  username = '';
  user = new User();
  message: string | undefined;
  //form parameters
  UserUpdateForm: FormGroup;
  showModal = false ;
  constructor(private route: ActivatedRoute,
              private location: Location,
              private userService: UserService,
              private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.loadUserDetail();
    this.loadUserUpdateForm();
  }

  loadUserDetail() {
    this.route.params.subscribe(params => {
      this.username = params['username'];
    });
    this.userService.getByName(this.username).subscribe(
      res => {
        this.user = res;
        this.loadUserUpdateForm();
      },
      error => {
        this.LoadBackPage();
      }
    );

  }

  loadUserUpdateForm(){
    this.UserUpdateForm = this.formBuilder.group({
      'username':     [this.user.username, [Validators.required]],
      'firstname':    [this.user.firstname, [Validators.required]],
      'lastname':     [this.user.lastname, [Validators.required]],
      'email':        [this.user.email, [Validators.required, Validators.email]],
    });
  }
  openLoadUserUpdateForm(){
    this.showModal = true ;
    this.loadUserUpdateForm();
  }
  closeLoadUserUpdateForm(){
    this.showModal = false ;

  }
  updateUser(){
    if (!this.UserUpdateForm.valid) {
      return;
    }
    this.userService.put(this.username,this.UserUpdateForm.value).subscribe(
      res => {
        this.router.navigate(['/login']);
      },
      error => {
        this.message = error;
      }


    );
  }
  get uf() { return this.UserUpdateForm.controls; }

  LoadBackPage() {
    this.location.back();
  }
}
