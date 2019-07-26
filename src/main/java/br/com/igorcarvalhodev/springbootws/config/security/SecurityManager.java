package br.com.igorcarvalhodev.springbootws.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * Classe de configuração so springSecurity
 * é obrigatorio o uso das anotations abaixo
 * */

@EnableWebSecurity
@Configuration
public class SecurityManager extends WebSecurityConfigurerAdapter {

	@Autowired
	TokenService tokenService;

	@Autowired
	private AutenticacaoService autenticacaoService;

	/*
	 * Fabrica o AuthenticationManager para ser injeto em dependencias do projeto
	 */
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// conviguraçoes de autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * Faz a autenticação usando o usuario encontrado pelo service, e faz a
		 * conferencia de senhas após encriptação com o padrao escolhido
		 */
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// conviguraçoes de autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		Autenticação tradicional usando login na aplicação e jsessionId 
//		http.authorizeRequests().antMatchers(HttpMethod.GET, "/topicos").permitAll()
//				.antMatchers(HttpMethod.GET, "/topicos/*").permitAll().anyRequest().authenticated().and().formLogin();

		// Autenticação via token
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/topicos").permitAll()
				.antMatchers(HttpMethod.GET, "/actuator/**").permitAll().antMatchers(HttpMethod.GET, "/topicos/*")
				.permitAll().antMatchers(HttpMethod.POST, "/auth").permitAll().anyRequest().authenticated().and().csrf()
				.disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(new AutenticacaoTonkenFilter(tokenService),
						UsernamePasswordAuthenticationFilter.class);
	}

	// conviguraçoes de recursos estaticos
	@Override
	public void configure(WebSecurity web) throws Exception {
		// liberando o acesso as paginas de documentaç~ao do Swagger
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
				"/swagger-resources/**");
	}

}
