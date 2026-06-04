package br.com.atilajf.processos.service;

import java.util.List;
import br.com.atilajf.processos.mapper.AnalistaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.atilajf.processos.dto.AnalistaDTO;
import br.com.atilajf.processos.exception.IdNaoExisteException;
import br.com.atilajf.processos.repository.AnalistaRepository;
import br.com.atilajf.processos.repository.PessoaFisicaRepository;
import br.com.atilajf.processos.repository.SetorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class AnalistaService {

	private final AnalistaRepository analistaRepository;
    private final AnalistaMapper analistaMapper;
    private final SetorRepository setorRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;


	public List<AnalistaDTO> listarTodos() {
		
		return analistaRepository.findAll().stream()
				                           .map(analistaMapper :: toDtoAnalista)
				                           .toList();


	}

    public AnalistaDTO recuperarPorId(Long id) {
       
    	final var analistaEntity = analistaRepository.findById(id)
        		                                     .orElseThrow(() -> new IdNaoExisteException("Não existe analista com o ID informado."));
        
    	return analistaMapper.toDtoAnalista(analistaEntity);

    }


	@Transactional
	public AnalistaDTO cadastro(AnalistaDTO analistaDto) {
		
		final var setorEntity = setorRepository.findById(analistaDto.getSetor()
				                                                    .getId())
				                                                    .orElseThrow(() -> new IdNaoExisteException("Setor não encontrado com o ID informado"));
		
		final var pessoaFisicaEntity = pessoaFisicaRepository.findById(analistaDto.getPessoaFisica()
				                                                                  .getId())
				                                                                  .orElseThrow(() -> new IdNaoExisteException("Pessoa Física não encontrada para o ID informado.")); 
		
		final var analistaEntity = analistaMapper.toEntityAnalistaCadastro(analistaDto, setorEntity, pessoaFisicaEntity);
		
		analistaRepository.save(analistaEntity);
		analistaDto.setId(analistaEntity.getId());

        return analistaDto;

	}

}
