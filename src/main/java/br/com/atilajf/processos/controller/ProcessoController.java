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
import br.com.atilajf.processos.dto.ProcessoDTO;
import br.com.atilajf.processos.service.ProcessoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "📑 Processo", description = "Listar/Buscar/Cadastrar PROCESSO")
@RequestMapping("/processo")
@RequiredArgsConstructor
public class ProcessoController {

	private final ProcessoService processoService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProcessoDTO>> listar(){
		final var processo = processoService.Listar();
		return ResponseEntity.ok(processo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProcessoDTO> recuperarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(processoService.buscarPorId(id));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> cadastrarProcesso(@RequestBody ProcessoDTO processoDto) {
		final var id = processoService.cadastrar(processoDto);
		final var uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	
}
