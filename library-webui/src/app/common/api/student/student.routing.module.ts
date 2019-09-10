import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { StudentComponent } from './student.component';
import { StudentDetailsComponent } from './student-details/student-details.component';
import { GetBookComponent } from './get-book/get-book.component';

const routes: Routes = [
  {
    path: '', component: StudentComponent
  },
  {
    path: 'student-detail/:id', component: StudentDetailsComponent
  },
  {
    path: 'get-book/:id', component: GetBookComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule {}