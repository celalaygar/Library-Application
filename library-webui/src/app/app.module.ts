import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './common/header/header.component';
import { AuthorComponent } from './common/api/author/author.component';
import { BookComponent } from './common/api/book/book.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { NotfoundComponent } from './shared/notfound/notfound.component';
import { AppRoutingModule } from './app.routing.module';
import { MainComponent } from './common/main/main.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthorService } from './services/author.service';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { ApiService } from './services/general/api.service';
import { BookService } from './services/book.service';
import { StudentService } from './services/student.service';
import { JwtInterceptor } from './security/jwt.interceptor';
import { AuthGuard } from './security/auth.guard';
import { AuthenticationService } from './security/authentication.service';
import { ErrorInterceptor } from './security/authentication.interceptor';
import { AlertifyService } from './services/alertify.service';
import { EditorModule } from '@tinymce/tinymce-angular';
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    NotfoundComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    AppRoutingModule,
    EditorModule ,
    NgxDatatableModule
  ],
  providers: [
    AuthorService, 
    BookService, 
    StudentService,
    AlertifyService,
    ApiService,
    AuthGuard,
    AuthenticationService,
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
