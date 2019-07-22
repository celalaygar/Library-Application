import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { UserDetailComponent } from './user-detail.component';

const routes: Routes = [
  {
    path: 'user-detail/:username', component: UserDetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
// tslint:disable-next-line:eofline
export class UserRoutingModule {}