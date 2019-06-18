import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthorRoutingModule } from './author.routing.module';
import { AuthorComponent } from './author.component';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { BsModalRef, ModalModule, BsModalService} from 'ngx-bootstrap';
import { AuthorService } from 'src/app/services/author.service';
import { FormsModule } from '@angular/forms';
import { AuthorDetailComponent } from './author-detail/author-detail.component';

@NgModule({
  declarations: [AuthorComponent, AuthorDetailComponent],
  imports: [
    CommonModule,
    AuthorRoutingModule,
    FormsModule,
    NgxDatatableModule
  ],
  providers: [AuthorService]
})
export class AuthorModule { }
