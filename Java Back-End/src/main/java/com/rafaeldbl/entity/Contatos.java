package com.rafaeldbl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contatos")
public class Contatos implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contato_id")
	private int contatoId;
	@Column(name = "nome")
	private String nome;
	@Column(name = "numero")
	private String numero;
	@Column(name = "email")
	private String email;

	public int getContatoId() {
		return contatoId;
	}

	public void setContatoId(int contatoId) {
		this.contatoId = contatoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}