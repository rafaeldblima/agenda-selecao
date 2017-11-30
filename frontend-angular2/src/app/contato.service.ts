import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';

import { Contato } from './contato';

@Injectable()
export class ContatoService {
    //URLs for CRUD operations
    allContatosUrl = "http://localhost:8080/user/all-contatos";
	contatoUrl = "http://localhost:8080/user/contato";
	//Create constructor to get Http instance
	constructor(private http:Http) {
	}
	//GET: contatos
    getAllContatos(): Observable<Contato[]> {
        return this.http.get(this.allContatosUrl)
		   		.map(this.extractData)
		        .catch(this.handleError);

    }
	//POST: contato
    createContato(contato: Contato):Observable<number> {
	    let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.post(this.contatoUrl, contato, options)
               .map(success => success.status)
               .catch(this.handleError);
    }
	//GET: contato
    getContatoById(contatoId: string): Observable<Contato> {
		let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
		let cpParams = new URLSearchParams();
		cpParams.set('id', contatoId);
		let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
		return this.http.get(this.contatoUrl, options)
			   .map(this.extractData)
			   .catch(this.handleError);
    }
	//PUT: contato
    updateContato(contato: Contato):Observable<number> {
	    let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.put(this.contatoUrl, contato, options)
               .map(success => success.status)
               .catch(this.handleError);
    }
    //DELETE: contato
    deleteContato(contatoId: string): Observable<number> {
		let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
		let cpParams = new URLSearchParams();
		cpParams.set('id', contatoId);
		let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
		return this.http.delete(this.contatoUrl, options)
			   .map(success => success.status)
			   .catch(this.handleError);
    }
	private extractData(res: Response) {
	    let body = res.json();
        return body;
    }
    private handleError (error: Response | any) {
		console.error(error.message || error);
		return Observable.throw(error.status);
    }
}
