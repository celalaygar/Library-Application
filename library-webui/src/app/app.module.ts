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
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthorService } from './services/author.service';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { ApiService } from './services/general/api.service';
import { BookService } from './services/book.service';

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
    NgxDatatableModule
  ],
  providers: [AuthorService, BookService, ApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
