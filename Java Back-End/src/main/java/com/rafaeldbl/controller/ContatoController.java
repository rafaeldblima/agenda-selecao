package com.rafaeldbl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.rafaeldbl.entity.Contatos;
import com.rafaeldbl.service.IContatoService;

@Controller
@RequestMapping("user")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ContatoController {
	@Autowired
	private IContatoService contatoService;

	@GetMapping("contato")
	public ResponseEntity<Contatos> getContatoById(@RequestParam("id") String id) {
		Contatos contato = contatoService.getContatoById(Integer.parseInt(id));
		return new ResponseEntity<Contatos>(contato, HttpStatus.OK);
	}

	@GetMapping("all-contatos")
	public ResponseEntity<List<Contatos>> getAllContatos() {
		List<Contatos> list = contatoService.getAllContatos();
		return new ResponseEntity<List<Contatos>>(list, HttpStatus.OK);
	}

	@PostMapping("contato")
	public ResponseEntity<Void> createContato(@RequestBody Contatos contato, UriComponentsBuilder builder) {
		boolean flag = contatoService.createContato(contato);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/article?id={id}").buildAndExpand(contato.getContatoId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("contato")
	public ResponseEntity<Contatos> updateContato(@RequestBody Contatos contato) {
		contatoService.updateContato(contato);
		return new ResponseEntity<Contatos>(contato, HttpStatus.OK);
	}

	@DeleteMapping("contato")
	public ResponseEntity<Void> deleteContato(@RequestParam("id") String id) {
		contatoService.deleteContato(Integer.parseInt(id));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}