import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AlertModule } from 'ngx-bootstrap';

import { AppComponent }  from './app.component';
import { ContatoComponent }  from './contato.component';
import { ContatoService } from './contato.service';

@NgModule({
  imports: [
        BrowserModule,
		HttpModule,
		ReactiveFormsModule,
    AlertModule.forRoot()
  ],
  declarations: [
        AppComponent,
		ContatoComponent
  ],
  providers: [
        ContatoService
  ],
  bootstrap: [
        AppComponent
  ]
})
export class AppModule { }
