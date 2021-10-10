import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateQuizComponent } from './edit/create-quiz/create-quiz.component';
import { EditAuthorizationComponent } from './edit/edit-quiz/edit-authorization/edit-authorization.component';
import { EditQuizComponent } from './edit/edit-quiz/edit-quiz.component';
import { QuizOverviewComponent } from './edit/quiz-overview/quiz-overview.component';
import { JoinComponent } from './join/join.component';
import { GameManagementComponent } from './play/game-management/game-management.component';
import { StartScreenComponent } from './start-screen/start-screen.component';

const routes: Routes = [
  { path: '', redirectTo: '/start', pathMatch: 'full' },
  { path: 'start', component: StartScreenComponent },
  { path: 'join', component: JoinComponent },
  { path: 'join/:sessionID', component: JoinComponent },
  { path: 'edit', component: QuizOverviewComponent },
  { path: 'create', component: CreateQuizComponent },
  { path: 'edit/:id', component: EditQuizComponent },
  { path: 'edit/:id/authorize', component: EditAuthorizationComponent },
  { path: 'play', component: GameManagementComponent },
  { path: '**', redirectTo: '/start', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
