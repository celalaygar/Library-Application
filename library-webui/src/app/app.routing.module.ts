import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { NotfoundComponent } from './shared/notfound/notfound.component';
import { MainComponent } from './common/main/main.component';

const routes: Routes = [
    {
      path: '',    component: MainComponent,
      children: [
        {path: '', pathMatch: 'full', redirectTo: 'author'},
        {path: 'author', loadChildren: './common/api/author/author.module#AuthorModule'},
        {path: 'book', loadChildren: './common/api/book/book.module#BookModule'},
      ]
    },
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    { path: '**',    component: NotfoundComponent }
  ];

@NgModule({

    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}


