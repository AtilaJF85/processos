package br.com.atilajf.processos.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.atilajf.processos.dto.PessoaJuridicaDTO;
import br.com.atilajf.processos.exception.IdNaoExisteException;
import br.com.atilajf.processos.mapper.PessoaJuridicaMapper;
import br.com.atilajf.processos.repository.PessoaJuridicaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class PessoaJuridicaService {

	private final PessoaJuridicaRepository pessoaJuridicaRepository;
	private final PessoaJuridicaMapper pessoaJuridicaMapper;

	public List<PessoaJuridicaDTO> listarTodos() {
		
		return pessoaJuridicaRepository.findAll().stream()
				                                 .map(pessoaJuridicaMapper::toDtoPessoaJuridica)
				                                 .toList();

	}
	
	
	public PessoaJuridicaDTO buscarPorId(Long id) {
		
		final var pessoaJuridicaEntity = pessoaJuridicaRepository.findById(id)
				                                                 .orElseThrow(() -> new IdNaoExisteException("Não existe PESSOA JURIDICA com o ID informado."));
		
		return pessoaJuridicaMapper.toDtoPessoaJuridica(pessoaJuridicaEntity);
	}

	
	@Transactional
	public PessoaJuridicaDTO cadastro(PessoaJuridicaDTO pessoaJuridicaDto) {

		final var pessoaJuridicaEntity = pessoaJuridicaMapper.toEntityPessoaJuridicaCadastro(pessoaJuridicaDto);

		pessoaJuridicaRepository.save(pessoaJuridicaEntity);
		pessoaJuridicaDto.setId(pessoaJuridicaEntity.getId());

		return pessoaJuridicaDto;

	}

}
