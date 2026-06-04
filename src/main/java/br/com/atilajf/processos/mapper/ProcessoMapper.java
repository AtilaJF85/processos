package br.com.atilajf.processos.mapper;

import org.springframework.stereotype.Component;
import br.com.atilajf.processos.dto.ProcessoDTO;
import br.com.atilajf.processos.dto.SituacaoProcessoDTO;
import br.com.atilajf.processos.entity.ProcessoEntity;
import br.com.atilajf.processos.entity.SituacaoProcessoEntity;
import br.com.atilajf.processos.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProcessoMapper {

	private final UsuarioMapper usuarioMapper;
	

	
	public ProcessoDTO toDtoProcesso(ProcessoEntity processoEntity) {
		
		return ProcessoDTO.builder()
				          .id(processoEntity.getId())
				          .numeroProcesso(processoEntity.getNumeroProcesso())
				          .descricaoAssunto(processoEntity.getDescricaoAssunto())
				          .dataAbertura(processoEntity.getDataAbertura())
				          .registroAtivo(processoEntity.getRegistroAtivo())
				          .usuarioAbertura(usuarioMapper.toDtoUsuario(processoEntity.getUsuarioAbertura()))
				          .situacaoProcesso(SituacaoProcessoDTO.builder()
				        		                               .id(processoEntity.getSituacaoProcesso().getId())
				        		                               .descricaoSituacao(processoEntity.getSituacaoProcesso().getDescricaoSituacao())
				        		                               .registroAtivo(processoEntity.getSituacaoProcesso().getRegistroAtivo())
				        		                               .build())
				          .build();
	}
	
	
	public ProcessoEntity toEntityProcessoCadastro(ProcessoDTO processoDto, UsuarioEntity ususuarioAberturaEntity, SituacaoProcessoEntity situacaoProcessoEntity) {
		
		final var processoEntity = new ProcessoEntity();
	
		processoEntity.setNumeroProcesso(processoDto.getNumeroProcesso());
		processoEntity.setDescricaoAssunto(processoDto.getDescricaoAssunto());
		processoEntity.setDataAbertura(processoDto.getDataAbertura());
		processoEntity.setUsuarioAbertura(ususuarioAberturaEntity);
		processoEntity.setSituacaoProcesso(situacaoProcessoEntity);
		processoEntity.setRegistroAtivo(processoDto.getRegistroAtivo());
		
		return processoEntity;
	}
}
