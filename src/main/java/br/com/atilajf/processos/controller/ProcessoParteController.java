package br.com.atilajf.processos.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.atilajf.processos.dto.ProcessoParteDTO;
import br.com.atilajf.processos.service.ProcessoParteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "📑 Processo Parte", description = "Listar Partes / Associar Partes PROCESSO PARTE")
@RequestMapping("/processo_parte")
@RequiredArgsConstructor
public class ProcessoParteController {

	private final ProcessoParteService processoParteService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ProcessoParteDTO> recuperarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(processoParteService.listaPartesProcesso(id));
	}



	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> associarParteEmUmProcesso(@RequestBody ProcessoParteDTO processoParteDto) {
		final var processoParte = processoParteService.buscarPorIdAssociarParte(processoParteDto);
		final var uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(processoParte.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
