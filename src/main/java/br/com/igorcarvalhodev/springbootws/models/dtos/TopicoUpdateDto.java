package br.com.igorcarvalhodev.springbootws.models.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.igorcarvalhodev.springbootws.models.Topico;
import br.com.igorcarvalhodev.springbootws.repositories.TopicoRepository;

public class TopicoUpdateDto {

	@NotNull
	@NotEmpty
	@Length(min = 5, max = 30)
	private String titulo;

	@NotNull
	@NotEmpty
	@Length(min = 5, max = 100)
	private String mensagem;
	
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

	public Topico update(Long id, TopicoRepository repository) {
		Topico topico = repository.getOne(id);
		topico.setTitulo(titulo);
		topico.setMensagem(mensagem);
		return topico;
	}

}
