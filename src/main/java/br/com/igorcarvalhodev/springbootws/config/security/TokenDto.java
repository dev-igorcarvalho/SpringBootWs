package br.com.igorcarvalhodev.springbootws.config.security;

public class TokenDto {

	// token gerado
	private String token;

	// Tipo de autenticação a ser feita pelo cliente com o token que lhe foi
	// devolvido
	private String authType;

	public TokenDto(String token, String string) {
		this.token = token;
		this.authType = string;
	}

	public String getToken() {
		return token;
	}

	public String getString() {
		return authType;
	}

}
