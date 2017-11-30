package com.rafaeldbl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaeldbl.dao.IContatoDAO;
import com.rafaeldbl.entity.Contatos;

@Service
public class ContatoService implements IContatoService {
	@Autowired
	private IContatoDAO contatoDAO;

	@Override
	public Contatos getContatoById(int articleId) {
		Contatos obj = contatoDAO.getContatoById(articleId);
		return obj;
	}

	@Override
	public List<Contatos> getAllContatos() {
		return contatoDAO.getAllContatos();
	}

	@Override
	public synchronized boolean createContato(Contatos contato) {
		if (contatoDAO.contatoExists(contato.getNome(), contato.getNumero(), contato.getEmail())) {
			return false;
		} else {
			contatoDAO.createContato(contato);
			return true;
		}
	}

	@Override
	public void updateContato(Contatos contato) {
		contatoDAO.updateContato(contato);
	}

	@Override
	public void deleteContato(int contatoId) {
		contatoDAO.deleteContato(contatoId);
	}
}
