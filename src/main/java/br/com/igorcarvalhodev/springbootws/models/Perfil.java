package br.com.igorcarvalhodev.springbootws.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import br.com.igorcarvalhodev.springbootws.models.abstracts.AbstractModel;


@Entity
@Table(name = "PermissoesAcesso")
public class Perfil  extends AbstractModel implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7127802023000373394L;

	private Long id;
	
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		return this.nome;
	}
	
	
}
