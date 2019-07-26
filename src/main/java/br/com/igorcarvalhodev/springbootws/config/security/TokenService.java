package br.com.igorcarvalhodev.springbootws.config.security;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.igorcarvalhodev.springbootws.models.Perfil;
import br.com.igorcarvalhodev.springbootws.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * Responsavel por produzir o token do JWT
 * */
@Service
public class TokenService {

	// validade em milisecs
	private final String expiration = "86400000";

	// chave da encryptação
	private final String secret = "$2a$10$TDvDs8RQCKGRzrQVZmVZeIQr8K6WZTxtwNFipe.GFuNJVlzm11u$2a$10$TDvDs8RQCKGRzrQVZmVZeIQr8K6WZTxtwNFipe.GFuNJVlzm11u";

	public String gerarToken(Authentication auth) {

		Usuario usuario = (Usuario) auth.getPrincipal();// pega o usuario ja autenticado
		Date hoje = new Date();
		Date exp = new Date(hoje.getTime() + Long.parseLong(expiration));

		/*
		 * Monta a string de token do JWT setIssuer("SpringBootWs") -> Quem gerou o
		 * token (nome da aplicação) .setSubject(usuario.getId().toString()) -> dado do
		 * usuario que vai ficar no token .claim() -> informaçoes extras q o token vai
		 * carregar .setIssuedAt(hoje) -> data criação .setExpiration(exp) -> data
		 * expiração .signWith(SignatureAlgorithm.HS256, secret) -> encriptação usando
		 * (metodo de encrypt, e chave da encrypt) .compact() -> Monta o token e
		 * serializa ele em uma url-safe string de acordo com as regras de serialização
		 * do JWT
		 */
		return Jwts.builder().setIssuer("SpringBootWs").setSubject(usuario.getId().toString())
				.claim("nome", usuario.getNome()).claim("permissoes", usuario.getAuthorities()).setIssuedAt(hoje)
				.setExpiration(exp).setAudience("SpringBootWs").signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValido(String token) {
		try {
			// Confere o token, se nao for igual lança uma exception
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			// printar com um logger depois
			return false;
		}
	}

	public Long getUsuarioId(String token) {
		// pega o conteudo do corpo da requisição referente ao token
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		// pega o subject usado no token
		return Long.parseLong(claims.getSubject());
	}

	@SuppressWarnings("unchecked")
	public Usuario getUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		Usuario usuario = new Usuario();
		usuario.setNome(claims.get("nome", String.class));
		List<Perfil> perfis = claims.get("permissoes", List.class);
		usuario.setPerfis(perfis);
		return usuario;
	}

}
