import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthorRoutingModule } from './author.routing.module';
import { AuthorComponent } from './author.component';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { BsModalRef, ModalModule, BsModalService} from 'ngx-bootstrap';
import { AuthorService } from 'src/app/services/author.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthorDetailComponent } from './author-detail/author-detail.component';
import { BookService } from 'src/app/services/book.service';
import { ApiService } from 'src/app/services/general/api.service';
import { AlertifyService } from 'src/app/services/alertify.service';

import { EditorModule } from '@tinymce/tinymce-angular';
@NgModule({
  declarations: [AuthorComponent, AuthorDetailComponent],
  imports: [
    CommonModule,
    AuthorRoutingModule,
    FormsModule,
    NgxDatatableModule,
    ReactiveFormsModule,
    EditorModule
  ],
  providers: [
    AuthorService,
    BookService,
    AlertifyService,
    ApiService,
    BsModalRef
  ]
})
export class AuthorModule { }
