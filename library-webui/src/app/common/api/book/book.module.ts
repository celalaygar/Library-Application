import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookRoutingModule } from './book.routing.module';
import { BookComponent } from './book.component';
import { AuthorService } from 'src/app/services/author.service';
import { BookService } from 'src/app/services/book.service';
import { ApiService } from 'src/app/services/general/api.service';

@NgModule({
  declarations: [BookComponent],
  imports: [
    CommonModule,
    BookRoutingModule
  ],
  providers: [AuthorService]
})
export class BookModule { }
