package br.com.igorcarvalhodev.springbootws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.igorcarvalhodev.springbootws.models.Topico;
import br.com.igorcarvalhodev.springbootws.repositories.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	TopicoRepository repository;

	@GetMapping
	public List<Topico> listar(String nomeCurso) {
		if (nomeCurso != null && !nomeCurso.isBlank() && !nomeCurso.isEmpty()) {
			return repository.findByCurso_Nome(nomeCurso);
		} else {
			return repository.findAll();
		}
	}
	
	/*
	 * @RequestBody para receber o parametro no corpo da requisição http
	 *
	*/
	
	@PostMapping
	public void salvar(@RequestBody Topico topico) {
		repository.save(topico);
	}

}
