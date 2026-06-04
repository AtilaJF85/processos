package br.com.atilajf.processos.mapper;

import org.springframework.stereotype.Component;
import br.com.atilajf.processos.dto.AnalistaDTO;
import br.com.atilajf.processos.entity.AnalistaEntity;
import br.com.atilajf.processos.entity.PessoaFisicaEntity;
import br.com.atilajf.processos.entity.SetorEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AnalistaMapper {
	
	private final PessoaFisicaMapper pessoaFisicaMapper;
	private final SetorMapper setorMapper;
	
	

	public AnalistaDTO toDtoAnalista(AnalistaEntity analistaEntity) {
		
		return AnalistaDTO.builder()
				          .id(analistaEntity.getId())
				          .pessoaFisica(pessoaFisicaMapper.toDtoPessoaFisica(analistaEntity.getPessoaFisica()))
				          .setor(setorMapper.toDtoSetor(analistaEntity.getSetor()))
				          .dataVinculo(analistaEntity.getDataVinculo())
				          .registroAtivo(analistaEntity.getRegistroAtivo())
				          .build();
		
	}
	
	public AnalistaEntity toEntityAnalistaCadastro(AnalistaDTO analistaDto, SetorEntity setorEntity, PessoaFisicaEntity pessoaFisicaEntity) {
		
		
		final var analistaEntity = new AnalistaEntity();
		
		
        analistaEntity.setDataVinculo(analistaDto.getDataVinculo());
        analistaEntity.setRegistroAtivo(analistaDto.getRegistroAtivo());
        analistaEntity.setSetor(setorEntity);
        analistaEntity.setPessoaFisica(pessoaFisicaEntity);
        
        return analistaEntity;
		
	}
}
