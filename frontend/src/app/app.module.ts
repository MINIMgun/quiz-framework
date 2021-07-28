import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StartScreenComponent } from './start-screen/start-screen.component';
import { JoinComponent } from './join/join.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EditModule } from './edit/edit.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [AppComponent, StartScreenComponent, JoinComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    EditModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
