package br.com.igorcarvalhodev.springbootws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.igorcarvalhodev.springbootws.models.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	/*
	 *Query automatica do spring data via conveção de nome:
	 *findBy + <nomeAtributo> ou 
	 *findBy + <nomeAtributoPai> + _ + <nomeAtributoFilho>
	*/
	List<Topico> findByCurso_Nome(String nomeCurso);

	//Query feita manualmente com JPQL
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
	List<Topico> buscarCursoPorNome(@Param(":nomeCurso") String nomeCurso);
}
