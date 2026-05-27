package br.com.atilajf.processos.service;

import java.util.List;

import br.com.atilajf.processos.mapper.PessoaFisicaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.atilajf.processos.dto.PessoaFisicaDTO;
import br.com.atilajf.processos.entity.PessoaFisicaEntity;
import br.com.atilajf.processos.repository.PessoaFisicaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
@Service
public class PessoaFisicaService {

	private final PessoaFisicaRepository pessoaFisicaRepository;
	private final PessoaFisicaMapper pessoaFisicaMapper;
	
	
	public List<PessoaFisicaDTO> listarTodos(){
		return pessoaFisicaRepository.findAll()
				.stream()
			    .map(pessoaFisicaMapper::toDto)
			    .toList();
	}
	
	
	@Transactional
	public PessoaFisicaDTO cadastro(PessoaFisicaDTO pessoaFisicaDto) {
		
		PessoaFisicaEntity pessoaFisicaEntity = pessoaFisicaMapper.toEntity(pessoaFisicaDto);
       
		if (pessoaFisicaEntity.getId() != null && pessoaFisicaEntity.getCpf() != null) {
			 pessoaFisicaRepository.save(pessoaFisicaEntity);
			 
		}
        else {
			throw new RuntimeException("Informação divergente");
		}
		return pessoaFisicaDto;

	}
}
