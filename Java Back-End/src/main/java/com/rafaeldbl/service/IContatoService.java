package com.rafaeldbl.service;

import java.util.List;

import com.rafaeldbl.entity.Contatos;

public interface IContatoService {
	List<Contatos> getAllContatos();

	Contatos getContatoById(int contatoId);

	boolean createContato(Contatos contato);

	void updateContato(Contatos contato);

	void deleteContato(int contatoId);
}
