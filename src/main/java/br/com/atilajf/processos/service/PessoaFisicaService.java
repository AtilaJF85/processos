package br.com.atilajf.processos.service;

import java.util.List;
import br.com.atilajf.processos.mapper.PessoaFisicaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.atilajf.processos.dto.PessoaFisicaDTO;
import br.com.atilajf.processos.entity.PessoaFisicaEntity;
import br.com.atilajf.processos.exception.IdNaoExisteException;
import br.com.atilajf.processos.repository.PessoaFisicaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class PessoaFisicaService {

	private final PessoaFisicaRepository pessoaFisicaRepository;
	private final PessoaFisicaMapper pessoaFisicaMapper;

	public List<PessoaFisicaDTO> listarTodos() {
		
		return pessoaFisicaRepository.findAll().stream()
				                               .map(pessoaFisicaMapper::toDtoPessoaFisica)
				                               .toList();
	}
	
	
	
	public PessoaFisicaDTO buscarPorId(Long id) {
		
		final var pessoaFisicaEntity = pessoaFisicaRepository.findById(id)
				                                             .orElseThrow(() -> new IdNaoExisteException("Não existe PESSOA FISICA com o ID informado."));
		
		 return pessoaFisicaMapper.toDtoPessoaFisica(pessoaFisicaEntity);
	}

	
	
	@Transactional
	public PessoaFisicaDTO cadastrar(PessoaFisicaDTO pessoaFisicaDto) {

		PessoaFisicaEntity pessoaFisicaEntity = pessoaFisicaMapper.toEntityPessoaFisicaCadastro(pessoaFisicaDto);

		pessoaFisicaRepository.save(pessoaFisicaEntity);
		pessoaFisicaDto.setId(pessoaFisicaEntity.getId());

		return pessoaFisicaDto;

	}
}
