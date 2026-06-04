package br.com.atilajf.processos.mapper;

import org.springframework.stereotype.Component;

import br.com.atilajf.processos.dto.SetorDTO;
import br.com.atilajf.processos.entity.SetorEntity;

@Component
public class SetorMapper {

	public SetorDTO toDtoSetor(SetorEntity setorEntity) {
		if (setorEntity == null) {
			return null;
		}
		
		return SetorDTO.builder()
				       .id(setorEntity.getId())
				       .nomeSetor(setorEntity.getNomeSetor())
				       .siglaSetor(setorEntity.getSiglaSetor())
				       .descricaoSetor(setorEntity.getDescricaoSetor())
				       .registroAtivo(setorEntity.getRegistroAtivo())
				       .build();
	}
	
	public SetorEntity toEntitySetorCadastro(SetorDTO setorDto) {
		
		final var setorEntity = new SetorEntity();
		
		setorEntity.setNomeSetor(setorDto.getNomeSetor());
		setorEntity.setSiglaSetor(setorDto.getSiglaSetor());
		setorEntity.setDescricaoSetor(setorDto.getDescricaoSetor());
		setorEntity.setRegistroAtivo(setorDto.getRegistroAtivo());
		return setorEntity;
	}
}
