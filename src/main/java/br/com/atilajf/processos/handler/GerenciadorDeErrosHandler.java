package br.com.atilajf.processos.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.com.atilajf.processos.exception.IdNaoExisteException;

@RestControllerAdvice
public class GerenciadorDeErrosHandler {

	@ExceptionHandler(IdNaoExisteException.class)
	public ResponseEntity<String> tratarIdInexistente(IdNaoExisteException ex){
	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
