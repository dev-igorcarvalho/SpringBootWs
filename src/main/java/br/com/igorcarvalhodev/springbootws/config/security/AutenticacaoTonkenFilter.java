package br.com.igorcarvalhodev.springbootws.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.igorcarvalhodev.springbootws.models.Usuario;
import br.com.igorcarvalhodev.springbootws.repositories.UsuarioRepository;

public class AutenticacaoTonkenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UsuarioRepository repository;

	public AutenticacaoTonkenFilter(TokenService tokenService, UsuarioRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		boolean valid = tokenService.isTokenValido(token);
		if (valid) {
			autenticarUsuario(token);
		}
		filterChain.doFilter(request, response);
	}

	private void autenticarUsuario(String token) {
		Long usuarioId = tokenService.getUsuarioId(token); // extrai o id do usuario do corpo do token
		Usuario usuario = repository.findById(usuarioId).get(); // busca o usuario no banco
		// faz a autenticação usando o usuario , credenciais, e os perfis de acesso
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
				usuario.getAuthorities());
		// faz a autenticação do springSecurity
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isBlank() || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length()); // Remove o "Bearer " do token
	}

}
