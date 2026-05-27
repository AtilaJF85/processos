package br.com.atilajf.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.atilajf.dto.TipoParteDTO;
import br.com.atilajf.service.TipoParteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = " Tipo Parte", description = "CRUD de Tipo Parte")
@RequestMapping("/tb_tipo_parte")
@RequiredArgsConstructor
public class TipoParteController {

	private final TipoParteService tipoParteService;

	public ResponseEntity<List<TipoParteDTO>> listagem() {
		final var tipoParte = tipoParteService.listarTodos();
		return ResponseEntity.ok(tipoParte);
	}
}
