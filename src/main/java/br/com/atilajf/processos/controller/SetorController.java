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
import br.com.atilajf.processos.dto.SetorDTO;
import br.com.atilajf.processos.service.SetorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "🏢 Setor", description = "Listar/Buscar/Cadastrar SETOR")
@RequestMapping("/setor")
@RequiredArgsConstructor
public class SetorController {

	private final SetorService setorService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SetorDTO>> listar(){
		final var setor = setorService.listarTodos();
		return ResponseEntity.ok(setor);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SetorDTO> recuperarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(setorService.buscarPorId(id));
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> cadastrarSetor(@RequestBody SetorDTO setorDto) {
		final var id = setorService.cadastrar(setorDto);
		final var uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
