package br.com.atilajf.processos.controller;

import br.com.atilajf.processos.dto.AnalistaDTO;
import br.com.atilajf.processos.service.AnalistaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "🤷‍♂️ Analista", description = "CRUD de Analista")
@RequestMapping("/analista")
@RequiredArgsConstructor
public class AnalistaController {

	private final AnalistaService analistaService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AnalistaDTO>> listar() {
		final var analista = analistaService.listarTodos();
		return ResponseEntity.ok(analista);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> cadastrarAnalista(@RequestBody AnalistaDTO analistaDto) {
		final var analistaDTO = analistaService.cadastro(analistaDto);
		final var uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(analistaDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AnalistaDTO> recuperarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(analistaService.recuperarPorId(id));
	}
}
