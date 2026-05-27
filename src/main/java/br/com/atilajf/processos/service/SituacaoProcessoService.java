package br.com.atilajf.processos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.atilajf.processos.dto.SituacaoProcessoDTO;
import br.com.atilajf.processos.repository.SituacaoProcessoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SituacaoProcessoService {

	private final SituacaoProcessoRepository situacaoProcessoRepository;

	public List<SituacaoProcessoDTO> listarTodos() {
		return situacaoProcessoRepository.findAll().stream()
				                                   .map(entitySituacaoProcesso -> SituacaoProcessoDTO.builder()
				                                		                                             .id(entitySituacaoProcesso.getId())
						                                                                             .descricaoSituacao(entitySituacaoProcesso.getDescricaoSituacao())
						                                                                             .registroAtivo(entitySituacaoProcesso.getRegistroAtivo())
						                                                                             .build())
						                                                                             
				                                  .toList();
	}

}
