package br.com.igorcarvalhodev.springbootws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igorcarvalhodev.springbootws.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nomeCurso);

}
