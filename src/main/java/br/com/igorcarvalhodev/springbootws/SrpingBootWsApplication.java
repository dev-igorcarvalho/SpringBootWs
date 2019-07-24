package br.com.igorcarvalhodev.springbootws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport //necessário para poder usar o paginator nas pesquisas e outros recursos do spring data
public class SrpingBootWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrpingBootWsApplication.class, args);
	}

}
