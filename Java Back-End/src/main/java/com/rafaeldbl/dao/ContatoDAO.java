package com.rafaeldbl.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rafaeldbl.entity.Contatos;

@Transactional
@Repository
public class ContatoDAO implements IContatoDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Contatos getContatoById(int contatoId) {
		return entityManager.find(Contatos.class, contatoId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contatos> getAllContatos() {
		String hql = "FROM Contatos as atcl ORDER BY atcl.contatoId DESC";
		return entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void createContato(Contatos article) {
		entityManager.persist(article);
	}

	@Override
	public void updateContato(Contatos contato) {
		Contatos artcl = getContatoById(contato.getContatoId());
		artcl.setNome(contato.getNome());
		artcl.setNumero(contato.getNumero());
		artcl.setEmail(contato.getEmail());
		entityManager.flush();
	}

	@Override
	public void deleteContato(int contatoId) {
		entityManager.remove(getContatoById(contatoId));
	}

	@Override
	public boolean contatoExists(String nome, String numero, String email) {
		String hql = "FROM Contatos as ctt WHERE ctt.nome = ? and ctt.numero = ? and ctt.email = ?";
		int count = entityManager.createQuery(hql).setParameter(1, nome).setParameter(2, numero).setParameter(3, email)
				.getResultList().size();
		return count > 0 ? true : false;
	}
}
