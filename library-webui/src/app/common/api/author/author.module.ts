import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthorRoutingModule } from './author.routing.module';
import { AuthorComponent } from './author.component';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';

@NgModule({
  declarations: [AuthorComponent],
  imports: [
    CommonModule,
    AuthorRoutingModule,
    NgxDatatableModule
  ]
})
export class AuthorModule { }
