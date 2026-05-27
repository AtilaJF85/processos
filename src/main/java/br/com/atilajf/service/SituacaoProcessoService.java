package br.com.atilajf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.atilajf.dto.SituacaoProcessoDTO;
import br.com.atilajf.repository.SituacaoProcessoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SituacaoProcessoService {

	private SituacaoProcessoRepository situacaoProcessoRepository;

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
