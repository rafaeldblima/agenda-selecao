package com.rafaeldbl.dao;

import java.util.List;

import com.rafaeldbl.entity.Contatos;

public interface IContatoDAO {
	List<Contatos> getAllContatos();

	Contatos getContatoById(int contatoId);

	void createContato(Contatos contato);

	void updateContato(Contatos contato);

	void deleteContato(int contatoId);

	boolean contatoExists(String nome, String numero, String email);
}
