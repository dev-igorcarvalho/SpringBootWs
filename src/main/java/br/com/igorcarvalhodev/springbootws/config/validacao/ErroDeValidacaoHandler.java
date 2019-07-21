package br.com.igorcarvalhodev.springbootws.config.validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * intercepta os metodos de qualquer @RestControler
*/
@RestControllerAdvice
public class ErroDeValidacaoHandler {

	/*
	 * classe do spring q gerencia erros e mensagens de erros utiliza o locale para
	 * fazer traduç~ao autmatica das mesngans de acordo com o idioma do sistema
	 * operacional que o usuario esta usando
	 */
	@Autowired
	private MessageSource mensagemErro;

	/*
	 * @ExceptionHandler busca a excecao do tipo passado no parametro
	 * 
	 * @ResponseStatus(code = HttpStatus.BAD_REQUEST) muda o status da resposta para
	 * o status passado no parametro. O default e 200ok
	 */
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException ex) {
		List<ErroDeFormularioDto> listaErros = new ArrayList<>();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String erro = mensagemErro.getMessage(e, LocaleContextHolder.getLocale());
			listaErros.add(new ErroDeFormularioDto(e.getField(), erro));
		});
		return listaErros;
	}

}
