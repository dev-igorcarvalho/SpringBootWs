package br.com.igorcarvalhodev.springbootws.models.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.igorcarvalhodev.springbootws.models.Curso;
import br.com.igorcarvalhodev.springbootws.models.Topico;
import br.com.igorcarvalhodev.springbootws.repositories.CursoRepository;

public class TopicoFormDto {

	@NotNull @NotEmpty @Length(min = 5, max = 30)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min = 5, max = 100)
	private String mensagem;
	
	@NotNull @NotEmpty @Length(min = 5, max = 30)
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
