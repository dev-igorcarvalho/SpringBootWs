package br.com.igorcarvalhodev.springbootws.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.igorcarvalhodev.springbootws.models.abstracts.AbstractModel;

@Entity
@Table(name = "Cursos")
public class Curso extends AbstractModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 901474704154986963L;
	
	private String nome;
	
	private String categoria;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	

}
