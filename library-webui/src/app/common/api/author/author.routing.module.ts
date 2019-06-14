import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { AuthorComponent } from './author.component';

const routes: Routes = [
  {
    path: '', component: AuthorComponent
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
export class AuthorRoutingModule {}