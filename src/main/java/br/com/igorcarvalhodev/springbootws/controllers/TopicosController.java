package br.com.igorcarvalhodev.springbootws.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.igorcarvalhodev.springbootws.models.Topico;
import br.com.igorcarvalhodev.springbootws.models.dtos.DetalhesDoTopicoDto;
import br.com.igorcarvalhodev.springbootws.models.dtos.TopicoDto;
import br.com.igorcarvalhodev.springbootws.models.dtos.TopicoFormDto;
import br.com.igorcarvalhodev.springbootws.models.dtos.TopicoUpdateDto;
import br.com.igorcarvalhodev.springbootws.repositories.CursoRepository;
import br.com.igorcarvalhodev.springbootws.repositories.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	TopicoRepository repository;

	@Autowired
	CursoRepository cursoRepository;

	/*
	 * Usando o pageable programaticamente, controlado pelo programador
	 * 
	 * @RequestParam para receber valores por param get na requisiçao. Pageable
	 * interface do spring que faz os ajustes de paginaçao. Page classe generic do
	 * spring que trabalha com entidades e paginaçao
	 * 
	 * @Params Page = numero da pagina, size = quantidade de intes exibidos,
	 * Direction = ascendente/descendente, ordenarPor = atributo da entidade que vai
	 * ser usado pra ordenaçao
	 */

//	@GetMapping("/filtrar") mapping comentado para evitar o rastreio dele pelo spring
	public Page<TopicoDto> listarComFiltros(@RequestParam(required = false) String nomeCurso, @RequestParam int page,
			@RequestParam int size, @RequestParam String ordenarPor) {
		Pageable paginacao = PageRequest.of(page, size, Direction.ASC, ordenarPor);

		if (nomeCurso != null && !nomeCurso.isBlank() && !nomeCurso.isEmpty()) {
			Page<Topico> topicos = repository.findByCurso_Nome(nomeCurso, paginacao);
			return TopicoDto.converter(topicos);
		} else {
			Page<Topico> topicos = repository.findAll(paginacao);
			return TopicoDto.converter(topicos);
		}
	}

	/*
	 * Usando o pageable automatico, controlado pelo spring, vira url get param
	 * 
	 * @RequestParam para receber valores por param get na requisiçao. Pageable
	 * interface do spring que faz os ajustes de paginaçao. Page classe generic do
	 * spring que trabalha com entidades e paginaçao
	 * 
	 * passar os params via url<url?page=0&size=4&sort=titulo.asc> page = numero da
	 * pagina a ser exibida size = quantidade de itens a serem exibidos por pagina
	 * sort = campo da entidade pelo qual o spring vai ordenar + ,asc ou ,desc obs:
	 * o param sort é opcional e pode se repitir quantas vezes forem necessarias
	 * para fazer ordenaçao por multiplos campos
	 * 
	 * @PageableDefault faz com que existam params default caso algum ou nehum o
	 * param seja forneceido via url
	 */

	@GetMapping("/filtrar")
	public Page<TopicoDto> listarComFiltros(@RequestParam(required = false) String nomeCurso,
			@PageableDefault(size = 4, sort = "id", direction = Direction.DESC) Pageable paginacao) {

		if (nomeCurso != null && !nomeCurso.isBlank() && !nomeCurso.isEmpty()) {
			Page<Topico> topicos = repository.findByCurso_Nome(nomeCurso, paginacao);
			return TopicoDto.converter(topicos);
		} else {
			Page<Topico> topicos = repository.findAll(paginacao);
			return TopicoDto.converter(topicos);
		}
	}

	@Cacheable(value = "listaTopicos") // guarda o resultado em cache
	@GetMapping
	public List<TopicoDto> listar(@RequestParam(required = false) String nomeCurso) {
		if (nomeCurso != null && !nomeCurso.isBlank() && !nomeCurso.isEmpty()) {
			return TopicoDto.converter(repository.findByCurso_Nome(nomeCurso));
		} else {
			return TopicoDto.converter(repository.findAll());
		}
	}

	/*
	 * @RequestBody para receber o parametro no corpo da requisição http
	 * ResponseEntity devolve como retorno uma uri(endpoint) e o recurso que acabou
	 * de ser criado UriComponentsBuilder como parametro do metodo faz com que o
	 * spring injete ele para ser usado automaticamente
	 * 
	 * @Valid faz com que o spring chame o bean validator
	 * 
	 */

	@Transactional
	@PostMapping
	@CacheEvict(value = "listaTopicos") // apaga o cache escolhido
	public ResponseEntity<Topico> salvar(@RequestBody @Valid TopicoFormDto topicoForm,
			UriComponentsBuilder componentsBuilder) {
		Topico topico = topicoForm.converter(cursoRepository);
		repository.save(topico);

		URI location = componentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(location).body(topico);
	}

	/*
	 * Usando Optional<> do java 8 para fazer tratamento caso nao exista o recurso
	 * no banco
	 */
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
		Optional<Topico> topico = repository.findById(id);
		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * @Transactional ao fim da execução se não houver erros ele automaticamente
	 * comita as modificações da entidade no banco
	 * 
	 */
	@Transactional
	@PutMapping("/{id}")
	@CacheEvict(value = "listaTopicos") // apaga o cache escolhido
	public ResponseEntity<TopicoDto> editar(@PathVariable Long id, @RequestBody @Valid TopicoUpdateDto topicoUpdateDto,
			UriComponentsBuilder componentsBuilder) {
		Topico topico = topicoUpdateDto.update(id, repository);
		return ResponseEntity.ok(new TopicoDto(topico));
	}

	@Transactional
	@DeleteMapping("/{id}")
	@CacheEvict(value = "listaTopicos") // apaga o cache escolhido
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.ok("Topico removido com sucesso");
	}

}
