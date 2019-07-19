package br.com.igorcarvalhodev.springbootws.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.igorcarvalhodev.springbootws.models.Curso;
import br.com.igorcarvalhodev.springbootws.models.Topico;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@GetMapping
	public List<Topico> listar() {
		Topico topico = new Topico("Titulo teste", "Mensagem teste", new Curso("", ""));
		return Arrays.asList(topico, topico, topico);
	}

}
