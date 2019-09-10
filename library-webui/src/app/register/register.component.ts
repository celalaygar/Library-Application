import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../security/authentication.service';
import { first } from 'rxjs/operators';
import { AlertifyService } from '../services/alertify.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  error = '';

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private alert: AlertifyService,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });
    this.authenticationService.logout();
  }
  get f() { return this.registerForm.controls; }
  register() {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }
    this.loading = true;
    this.authenticationService.register(this.registerForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.alert.success('Kayıt işlemi başarılı...');
          this.router.navigate(['/login']);
        },
        error => {
          this.alert.error('Hata : ' + error);
          this.error = error;
          this.loading = false;
        });
  }
}
