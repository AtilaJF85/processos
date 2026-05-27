package br.com.atilajf.processos.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.atilajf.processos.dto.SituacaoProcessoDTO;
import br.com.atilajf.processos.service.SituacaoProcessoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "♦️ Situacao Processo", description = "CRUD de Situacao Processo")
@RequestMapping("/situacao_processo")
@RequiredArgsConstructor
public class SituacaoProcessoController {

	private final SituacaoProcessoService situacaoProcessoService;

	public ResponseEntity<List<SituacaoProcessoDTO>> listar() {
		final var situacaoProcesso = situacaoProcessoService.listarTodos();
		return ResponseEntity.ok(situacaoProcesso);
	}
}
