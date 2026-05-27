package br.com.atilajf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.atilajf.dto.TipoParteDTO;
import br.com.atilajf.repository.TipoParteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class TipoParteService {

	private TipoParteRepository tipoParteRepository;
	
	
	public List<TipoParteDTO> listarTodos(){
		return tipoParteRepository.findAll().stream()
				                            .map(entityTipoParte -> TipoParteDTO.builder()
				                            		                            .id(entityTipoParte.getId())
				                            		                            .descricaoTipoParte(entityTipoParte.getDescricaoTipoParte())
				                            		                            .registroAtivo(entityTipoParte.getRegistroAtivo())
				                            		                            .build())
				                            		         
				                            .toList();
				
	}
	
}
