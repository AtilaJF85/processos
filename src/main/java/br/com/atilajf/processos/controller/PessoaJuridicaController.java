package br.com.atilajf.processos.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.atilajf.processos.dto.PessoaJuridicaDTO;
import br.com.atilajf.processos.service.PessoaJuridicaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "👤🏢 Pessoa Juridica", description = "CRUD de Pessoa Juridica")
@RequestMapping("/pessoa_juridica")
@RequiredArgsConstructor
public class PessoaJuridicaController {

	private final PessoaJuridicaService pessoaJuridicaService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PessoaJuridicaDTO>> listar(){
		final var pessoaJuridica = pessoaJuridicaService.listarTodos();
		return ResponseEntity.ok(pessoaJuridica);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> cadastrarPessoaJuridica(@RequestBody PessoaJuridicaDTO pessoaJuridicaDto) {
		final var id = pessoaJuridicaService.cadastro(pessoaJuridicaDto);
		final var uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
