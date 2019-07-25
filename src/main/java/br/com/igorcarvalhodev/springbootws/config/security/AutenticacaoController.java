package br.com.igorcarvalhodev.springbootws.config.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginDto login) {
		// converte o DTO de login em uma classe do tipo
		// UsernamePasswordAuthenticationToken para ser usada na autenticacao
		UsernamePasswordAuthenticationToken dadosLogin = login.converter();
		try {
			// autenticador de login, se falhar lança exception
			Authentication auth = authManager.authenticate(dadosLogin); // autenticador de login
			String token = tokenService.gerarToken(auth); // gerador do token
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			// Bearer é um dos mecanismos de autenticação utilizados no protocolo HTTP
		} catch (AuthenticationException e) {
			// printar esception via logger depois
			return ResponseEntity.badRequest().body(new TokenDto("Token Invalido", "Bearer"));
		}
	}
}
