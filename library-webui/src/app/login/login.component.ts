import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../security/authentication.service';
import { first } from 'rxjs/operators';
import { AlertifyService } from '../services/alertify.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  //form parameters
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';
  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private alert: AlertifyService,
              private router: Router,
              private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      'username': [null, [Validators.required]],
      'password': [null, [Validators.required]]
    });
    // reset login status
    this.authenticationService.logout();

    // return url var ise al yoksa / linkine git
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }
  login() {
    this.submitted = true;
    if (!this.loginForm.valid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.f.username.value, this.f.password.value)
    .pipe(first())
    .subscribe(
      data => {
        this.router.navigate([this.returnUrl]);
        this.alert.success('Hoşgeldiniz ' + this.f.username.value);
      },
      error => {
        this.error = error;
        this.alert.success('Hata : ' + error);
        this.loading = false;
      }
    );
  }
  get f() { return this.loginForm.controls; }
}
