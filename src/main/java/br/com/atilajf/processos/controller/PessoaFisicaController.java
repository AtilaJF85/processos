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

import br.com.atilajf.processos.dto.PessoaFisicaDTO;
import br.com.atilajf.processos.service.PessoaFisicaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "👤 Pessoa Fisica", description = "CRUD de Pessoa Fisica")
@RequestMapping("/tb_pessoa_fisica")
@RequiredArgsConstructor
public class PessoaFisicaController {
 
	private final PessoaFisicaService pessoaFisicaService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PessoaFisicaDTO>> listar(){
		final var pessoaFisica = pessoaFisicaService.listarTodos();
		return ResponseEntity.ok(pessoaFisica);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> cadastrarPessoaFisica(@RequestBody PessoaFisicaDTO pessoaFisica) {
		final var id = pessoaFisicaService.cadastro(pessoaFisica);
		final var uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
