package br.com.atilajf.processos.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.atilajf.processos.dto.TramitacaoDTO;
import br.com.atilajf.processos.service.TramitacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "Tramitação", description = "CRUD de Tramitação")
@RequestMapping("/th_tramitacao")
@RequiredArgsConstructor
public class TramitacaoController {

	private final TramitacaoService tramitacaoService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TramitacaoDTO>> listar(){
		final var tramitacao = tramitacaoService.listar();
		return ResponseEntity.ok(tramitacao);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TramitacaoDTO> recuperarPorId(@PathVariable Long id) {
		final var tramitacao = tramitacaoService.pesquisarPorId(id);
		return ResponseEntity.ok(tramitacao);
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> cadastrarTramitacao(@RequestBody TramitacaoDTO tramitacaoDto) { 
		final var id = tramitacaoService.cadastro(tramitacaoDto);
		final var uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
