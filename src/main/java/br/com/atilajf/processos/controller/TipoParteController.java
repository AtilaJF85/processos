package br.com.atilajf.processos.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.atilajf.processos.dto.TipoParteDTO;
import br.com.atilajf.processos.service.TipoParteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "\uD83C\uDF55 Tipo Parte", description = "Listar TIPO PARTE")
@RequestMapping("/tipo_parte")
@RequiredArgsConstructor
public class TipoParteController {

	private final TipoParteService tipoParteService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoParteDTO>> listagem() {
		final var tipoParte = tipoParteService.listarTodos();
		return ResponseEntity.ok(tipoParte);
	}
}
