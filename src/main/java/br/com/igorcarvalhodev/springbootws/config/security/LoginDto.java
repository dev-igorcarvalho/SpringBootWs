package br.com.igorcarvalhodev.springbootws.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDto {

	private String login;
	private String senha;

	public LoginDto(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(this.login, this.senha);
	}

}
