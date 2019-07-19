package br.com.igorcarvalhodev.springbootws.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.igorcarvalhodev.springbootws.models.abstracts.AbstractModel;

@Entity
@Table(name = "Respostas")
public class Resposta extends AbstractModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -164045377946171050L;
	private String mensagem;
	
	@ManyToOne
	private Topico topico;
	
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	@ManyToOne
	private Usuario autor;
	
	private Boolean solucao = false;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Boolean getSolucao() {
		return solucao;
	}

	public void setSolucao(Boolean solucao) {
		this.solucao = solucao;
	}

}
