package br.com.igorcarvalhodev.springbootws.models.dtos;

import br.com.igorcarvalhodev.springbootws.models.Curso;
import br.com.igorcarvalhodev.springbootws.models.Topico;
import br.com.igorcarvalhodev.springbootws.repositories.CursoRepository;

public class TopicoFormDto {

	private String titulo;
	private String mensagem;
	private String nomeCurso;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Topico converter(CursoRepository repository) {
		Curso curso = repository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}

}
