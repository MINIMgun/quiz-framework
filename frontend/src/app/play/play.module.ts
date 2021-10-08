import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GameManagementComponent } from './game-management/game-management.component';
import { LobbyComponent } from './lobby/lobby.component';



@NgModule({
  declarations: [
    GameManagementComponent,
    LobbyComponent
  ],
  imports: [
    CommonModule
  ]
})
export class PlayModule { }
