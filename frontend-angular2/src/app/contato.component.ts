import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { ContatoService } from './contato.service';
import { Contato } from './contato';

@Component({
   selector: 'app-contato',
   templateUrl: './contato.component.html',
   styleUrls: ['./contato.component.css']
})
export class ContatoComponent implements OnInit {
   //Component properties
   allContatos: Contato[];
   statusCode: number;
   requestProcessing = false;
   contatoIdToUpdate = null;
   processValidation = false;
   //Create form
   contatoForm = new FormGroup({
       nome: new FormControl('', Validators.required),
       numero: new FormControl('', Validators.required),
       email: new FormControl('', Validators.required)
   });
   //Create constructor to get service instance
   constructor(private contatoService: ContatoService) {
   }
   //Create ngOnInit() and and load contatos
   ngOnInit(): void {
     this.getAllcontatos();
   }
   //Fetch all contatos
   getAllcontatos() {
        this.contatoService.getAllContatos()
		  .subscribe(
                data => this.allContatos = data,
                errorCode =>  this.statusCode = errorCode);
   }
   //Handle create and update contato
   onContatoFormSubmit() {
	  this.processValidation = true;
	  if (this.contatoForm.invalid) {
	       return; //Validation failed, exit from method.
	  }
	  //Form is valid, now perform create or update
      this.preProcessConfigurations();
	     let nome = this.contatoForm.get('nome').value.trim();
      let numero = this.contatoForm.get('numero').value.trim();
      let email = this.contatoForm.get('email').value.trim();
	  if (this.contatoIdToUpdate === null) {
	    //Handle create contato
	    let contato = new Contato(null, nome, numero, email);
	    this.contatoService.createContato(contato)
	      .subscribe(successCode => {
		            this.statusCode = successCode;
				    this.getAllcontatos();
					this.backToCreatecontato();
			    },
		        errorCode => this.statusCode = errorCode);
	  } else {
   	    //Handle update contato
	    let contato= new Contato(this.contatoIdToUpdate, nome, numero, email);
	    this.contatoService.updateContato(contato)
	      .subscribe(successCode => {
		            this.statusCode = successCode;
				    this.getAllcontatos();
					this.backToCreatecontato();
			    },
		        errorCode => this.statusCode = errorCode);
	  }
   }
   //Load contato by id to edit
   loadContatoToEdit(contatoId: string) {
      this.preProcessConfigurations();
      this.contatoService.getContatoById(contatoId)
	      .subscribe(contato => {
		            this.contatoIdToUpdate = contato.contatoId;
		            this.contatoForm.setValue({ nome: contato.nome, numero: contato.numero, email: contato.email });
					this.processValidation = true;
					this.requestProcessing = false;
		        },
		        errorCode =>  this.statusCode = errorCode);
   }
   //Delete contato
   deleteContato(contatoId: string) {
      this.preProcessConfigurations();
      this.contatoService.deleteContato(contatoId)
	      .subscribe(successCode => {
		            this.statusCode = successCode;
				    this.getAllcontatos();
				    this.backToCreatecontato();
			    },
		        errorCode => this.statusCode = errorCode);
   }
   //Perform preliminary processing configurations
   preProcessConfigurations() {
      this.statusCode = null;
	  this.requestProcessing = true;
   }
   //Go back from update to create
   backToCreatecontato() {
      this.contatoIdToUpdate = null;
      this.contatoForm.reset();
	  this.processValidation = false;
   }
}
