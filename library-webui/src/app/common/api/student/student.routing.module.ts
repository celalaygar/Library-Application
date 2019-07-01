import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { StudentComponent } from './student.component';
import { StudentDetailsComponent } from './student-details/student-details.component';

const routes: Routes = [
  {
    path: '', component: StudentComponent
  },
  {
    path: 'student-detail/:id', component: StudentDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
// tslint:disable-next-line:eofline
export class StudentRoutingModule {}