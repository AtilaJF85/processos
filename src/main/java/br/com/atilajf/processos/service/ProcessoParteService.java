package br.com.atilajf.processos.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.atilajf.processos.dto.ProcessoParteDTO;
import br.com.atilajf.processos.exception.IdNaoExisteException;
import br.com.atilajf.processos.mapper.ProcessoParteMapper;
import br.com.atilajf.processos.repository.ProcessoParteRepository;
import br.com.atilajf.processos.repository.ProcessoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProcessoParteService {

	private final ProcessoParteRepository processoParteRepository;
	private final ProcessoRepository processoRepository;

    private final ProcessoParteMapper processoParteMapper;

	
    public ProcessoParteDTO listaPartesProcesso(Long id) {

		final var processoParteEntity = processoParteRepository.findById(id)
				                                               .orElseThrow(() -> new IdNaoExisteException("Não existe PROCESSO com o ID informado."));

		return processoParteMapper.toDtoProcessoParte(processoParteEntity);

	}

	
    @Transactional
    public ProcessoParteDTO buscarPorIdAssociarParte(ProcessoParteDTO processoParteDto) {

    	final var processoEntity = processoRepository.findById(processoParteDto.getProcesso()
    			                                                               .getId())
    			                                                               .orElseThrow(() -> new IdNaoExisteException("Não existe PROCESSO com o ID informado."));
    			
		final var processoParteEntity = processoParteMapper.toEntityProcessoParteCadastroPartes(processoParteDto, processoEntity);
		
		processoParteRepository.save(processoParteEntity);
		processoParteDto.setId(processoParteEntity.getId());
		
		return processoParteDto;
	

	}

}
