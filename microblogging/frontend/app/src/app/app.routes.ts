import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent} from "./views/register/register.component";
import { HomeComponent} from "./views/home/home.component";
import { ProfileComponent } from './views/profile/profile.component';
import { MyPublicationsComponent} from "./views/my-publications/my-publications.component";
import { PublicationComponent} from "./views/publication/publication.component";

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'my-publications', component: MyPublicationsComponent },
  { path: 'publication/:id', component: PublicationComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
