import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookRoutingModule } from './book.routing.module';
import { BookComponent } from './book.component';
import { AuthorService } from 'src/app/services/author.service';
import { BookService } from 'src/app/services/book.service';
import { ApiService } from 'src/app/services/general/api.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { BsModalRef } from 'ngx-bootstrap';
import { BookDetailsComponent } from './book-details/book-details.component';
import { AlertifyService } from 'src/app/services/alertify.service';

@NgModule({
  declarations: [BookComponent, BookDetailsComponent],
  imports: [
    CommonModule,
    BookRoutingModule,
    FormsModule,
    NgxDatatableModule,
    ReactiveFormsModule
  ],
  providers: [
    AuthorService,
    BookService,
    AlertifyService,
    BsModalRef,
    ApiService
  ]
})
export class BookModule { }
