package br.com.atilajf.processos.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.atilajf.processos.dto.SetorDTO;
import br.com.atilajf.processos.entity.SetorEntity;
import br.com.atilajf.processos.repository.SetorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SetorService {

	private final SetorRepository setorRepository;
	
	public List<SetorDTO>listarTodos(){
		final var setorEntity = setorRepository.findAll();
		return setorEntity.stream()
				          .map(entitySetor -> new SetorDTO(entitySetor.getId(),
				        		                           entitySetor.getNomeSetor(), 
				        		                           entitySetor.getSiglaSetor(), 
				        		                           entitySetor.getDescricaoSetor(), 
				        		                           entitySetor.getRegistroAtivo()))
				          .toList();
	}
	
	
	@Transactional
	public SetorDTO cadastrar(SetorDTO setorDto) {
		final var setorEntity = new SetorEntity();
		setorEntity.setNomeSetor(setorDto.getNomeSetor());
		setorEntity.setSiglaSetor(setorDto.getSiglaSetor());
		setorEntity.setDescricaoSetor(setorDto.getDescricaoSetor());
		setorEntity.setRegistroAtivo(setorDto.getRegistroAtivo());
		
        if (setorEntity.getId() != null && setorEntity.getNomeSetor() != null) {
			setorRepository.save(setorEntity);
			setorDto.setId(setorEntity.getId());
		}
        else {
			throw new RuntimeException("Informação divergente");
		}
		return setorDto;

	}
}
