import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentComponent } from './student.component';
import { StudentDetailsComponent } from './student-details/student-details.component';
import { StudentRoutingModule } from './student.routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';

@NgModule({
  declarations: [StudentComponent, StudentDetailsComponent],
  imports: [
    CommonModule,
    StudentRoutingModule,
    FormsModule,
    NgxDatatableModule,
    ReactiveFormsModule
  ]
})
export class StudentModule { }
