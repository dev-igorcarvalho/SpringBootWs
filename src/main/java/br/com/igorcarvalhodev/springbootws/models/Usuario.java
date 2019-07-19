package br.com.igorcarvalhodev.springbootws.models;

import br.com.igorcarvalhodev.springbootws.models.abstracts.AbstractModel;

public class Usuario extends AbstractModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3716926018499781024L;

	
	private String nome;
	private String email;
	private String senha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}


}
