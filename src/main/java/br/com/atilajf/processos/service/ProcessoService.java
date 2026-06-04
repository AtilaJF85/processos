package br.com.atilajf.processos.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.atilajf.processos.dto.ProcessoDTO;
import br.com.atilajf.processos.exception.IdNaoExisteException;
import br.com.atilajf.processos.mapper.ProcessoMapper;
import br.com.atilajf.processos.repository.ProcessoRepository;
import br.com.atilajf.processos.repository.SituacaoProcessoRepository;
import br.com.atilajf.processos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
@Service
public class ProcessoService {

	private final ProcessoRepository processoRepository;
	private final UsuarioRepository usuarioRepository;
	private final SituacaoProcessoRepository situacaoProcessoRepository;
	private final ProcessoMapper processoMapper;
	
	
	public List<ProcessoDTO> Listar(){
		
		return processoRepository.findAll().stream()
				                           .map(processoMapper :: toDtoProcesso)
				                           .toList();
				                              
	}
	
	public ProcessoDTO buscarPorId(Long id) {
		
		final var processoEntity = processoRepository.findById(id)
				                                     .orElseThrow(() -> new IdNaoExisteException("Não existe PESSOA FISICA com o ID informado."));
		
		return processoMapper.toDtoProcesso(processoEntity);
	}
	
	
	@Transactional
	public ProcessoDTO cadastrar(ProcessoDTO processoDto) {
		
		final var usuarioAberturaEntity = usuarioRepository.findById(processoDto.getUsuarioAbertura()
				                                                                .getId())
				                                                                .orElseThrow(() -> new IdNaoExisteException("Não existe USUARIO ABERTURA com o ID informado."));
		
		final var situacaoProcessoEntity = situacaoProcessoRepository.findById(processoDto.getSituacaoProcesso()
				                                                                          .getId())
				                                                                          .orElseThrow(() -> new IdNaoExisteException("Não existe SITUAÇÃO PROCESSO com o ID informado."));
		
		final var processoEntity = processoMapper.toEntityProcessoCadastro(processoDto, usuarioAberturaEntity, situacaoProcessoEntity);
	        
		processoRepository.save(processoEntity);
	    processoDto.setId(processoEntity.getId());
	    
	    return processoDto;

	}

}
