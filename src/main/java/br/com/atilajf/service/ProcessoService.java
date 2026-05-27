package br.com.atilajf.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.atilajf.dto.ProcessoDTO;
import br.com.atilajf.dto.SituacaoProcessoDTO;
import br.com.atilajf.dto.UsuarioDTO;
import br.com.atilajf.entity.ProcessoEntity;
import br.com.atilajf.exception.IdNaoExisteException;
import br.com.atilajf.repository.ProcessoRepository;
import br.com.atilajf.repository.SituacaoProcessoRepository;
import br.com.atilajf.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
@Service
public class ProcessoService {

	private ProcessoRepository processoRepository;
	private UsuarioRepository usuarioRepository;
	private SituacaoProcessoRepository situacaoProcessoRepository;
	
	public List<ProcessoDTO> Listar(){
		return processoRepository.findAll().stream()
				                           .map(entityProcesso -> ProcessoDTO.builder()
				                        		                             .id(entityProcesso.getId())
				                        		                             .numeroProcesso(entityProcesso.getNumeroProcesso())
				                        		                             .descricaoAssunto(entityProcesso.getDescricaoAssunto())
				                        		                             .dataAbertura(entityProcesso.getDataAbertura())
				                        		                             .registroAtivo(entityProcesso.getRegistroAtivo())
				                        		                             
				                        		                             .usuarioAbertura(UsuarioDTO.builder()
				                        		                            		                    .id(entityProcesso.getUsuarioAbertura().getId())
				                        		                            		                    .build())
				                        		                             .situacaoProcesso(SituacaoProcessoDTO.builder()
				                        		                            		                    .id(entityProcesso.getSituacaoProcesso().getId())
				                        		                            		                    .build())
				                        		                             
				                        		                             .build())
				                           .toList();
	}
	
	
	
	
	@Transactional
	public ProcessoDTO cadastro(ProcessoDTO processoDto) {
		
		final var processoEntity = new ProcessoEntity();
		
		processoEntity.setNumeroProcesso(processoDto.getNumeroProcesso());
		processoEntity.setDescricaoAssunto(processoDto.getDescricaoAssunto());
		processoEntity.setDataAbertura(processoDto.getDataAbertura());
		
		processoEntity.setUsuarioAbertura(usuarioRepository.findById(processoDto.getUsuarioAbertura()
				                                                                .getId())
				                                                                .orElseThrow(() -> new IdNaoExisteException("SETOR DE ORIGEM com ID " + processoDto.getUsuarioAbertura().getId() + " Não existe!")));
		
		processoEntity.setSituacaoProcesso(situacaoProcessoRepository.findById(processoDto.getSituacaoProcesso()
				                                                                          .getId())
				                                                                          .orElseThrow(() -> new IdNaoExisteException("SETOR DE ORIGEM com ID " + processoDto.getSituacaoProcesso().getId() + " Não existe!")));
		
		processoEntity.setRegistroAtivo(processoDto.getRegistroAtivo());
		
        if (processoEntity.getId() != null && processoEntity.getNumeroProcesso() != null) {
			 processoRepository.save(processoEntity);
			 processoDto.setId(processoEntity.getId());
		}
        else {
			throw new RuntimeException("Informação divergente");
		}
		return processoDto;

	}

}
