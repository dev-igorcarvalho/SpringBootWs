package br.com.igorcarvalhodev.springbootws.config.sawgger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.igorcarvalhodev.springbootws.models.Usuario;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	/*
	 * DocumentationType e o tipo de documentaçao a ser usado
	 * RequestHandlerSelectors sao os pacotes q serao escaneados,nao coloca caso
	 * exista algum pacote com informaçao sensivel ou acesso privado Path sao os
	 * endpois que devem ser rastreados ignoresParameterTypes -> as classes que
	 * devem ser ignoradas, por motivos de informaç~ao sensivel ou acesso apenas
	 * administrativo
	 */
	@Bean
	public Docket apiDocs() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.igorcarvalhodev.springbootws"))
				.paths(PathSelectors.ant("/**")).build().ignoredParameterTypes(Usuario.class).globalOperationParameters(
						Arrays.asList(new ParameterBuilder().name("Authorization").description("Header para token JWT")
								.modelRef(new ModelRef("string")).parameterType("header").required(false).build()));

	}
}
