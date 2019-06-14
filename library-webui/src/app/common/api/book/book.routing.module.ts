import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { BookComponent } from './book.component';

const routes: Routes = [
  {
    path: '', component: BookComponent
  },
//   {
//     path: 'author-detail/:id', component: IssueDetailComponent
//   }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
// tslint:disable-next-line:eofline
export class BookRoutingModule {}