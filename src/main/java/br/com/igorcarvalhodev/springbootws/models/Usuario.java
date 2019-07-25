package br.com.igorcarvalhodev.springbootws.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.igorcarvalhodev.springbootws.models.abstracts.AbstractModel;

/*
 * implements UserDetails pra poder utilizar autenticação do springSecurity
 * */

@Entity
@Table(name = "Usuario")
// implementa interface UserDetaisl pois o spring precisa dela para autenticacao
public class Usuario extends AbstractModel implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3716926018499781024L;

	private String nome;
	private String email;
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	// Entidade que contem as permissoes de usuario para uso do springSecurity
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	// Spring Security
	@Override
	public String getPassword() {
		return this.senha;
	}

	// Spring Security
	@Override
	public String getUsername() {
		return this.email;
	}

	// Spring Security
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// Spring Security
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// Spring Security
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// Spring Security
	@Override
	public boolean isEnabled() {
		return true;
	}

}
