import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { AuthorComponent } from './author.component';
import { AuthorDetailComponent } from './author-detail/author-detail.component';

const routes: Routes = [
  {
    path: '', component: AuthorComponent
  },
  {
    path: 'author-detail/:id', component: AuthorDetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthorRoutingModule {}