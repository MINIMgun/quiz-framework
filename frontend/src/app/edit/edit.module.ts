import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QuizOverviewComponent } from './quiz-overview/quiz-overview.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from '../app-routing.module';
import { CreateQuizComponent } from './create-quiz/create-quiz.component';
import { CookieService } from 'ngx-cookie-service';
import { EditQuizComponent } from './edit-quiz/edit-quiz.component';
import { EditAuthorizationComponent } from './edit-quiz/edit-authorization/edit-authorization.component';

@NgModule({
  declarations: [QuizOverviewComponent, CreateQuizComponent, EditQuizComponent, EditAuthorizationComponent],
  imports: [CommonModule, ReactiveFormsModule, AppRoutingModule, FormsModule],
  providers: [CookieService],
})
export class EditModule {}
