import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GameManagementComponent } from './game-management/game-management.component';
import { LobbyComponent } from './lobby/lobby.component';
import { PlayComponent } from './play/play.component';
import { QuestionComponent } from './question/question.component';
import { OptionResponseComponent } from './responses/option-response/option-response.component';
import { RangeResponseComponent } from './responses/range-response/range-response.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BuzzerResponseComponent } from './responses/buzzer-response/buzzer-response.component';
import { ResultComponent } from './result/result.component';

@NgModule({
  declarations: [
    GameManagementComponent,
    LobbyComponent,
    PlayComponent,
    QuestionComponent,
    OptionResponseComponent,
    RangeResponseComponent,
    BuzzerResponseComponent,
    ResultComponent,
  ],
  imports: [CommonModule, ReactiveFormsModule],
})
export class PlayModule {}
