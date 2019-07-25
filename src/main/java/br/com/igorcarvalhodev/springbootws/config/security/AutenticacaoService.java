package br.com.igorcarvalhodev.springbootws.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.igorcarvalhodev.springbootws.models.Usuario;
import br.com.igorcarvalhodev.springbootws.repositories.UsuarioRepository;

/*
 * classe reposnavel pela logica de autenticção que sera usada no springSecurity
 * */

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;

	/*
	 * Verifica no banco se existe usuario e se o login e senha são validos
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		throw new UsernameNotFoundException("Usuário inexistente");

	}

}
