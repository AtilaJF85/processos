package br.com.atilajf.processos.mapper;

import org.springframework.stereotype.Component;
import br.com.atilajf.processos.dto.TramitacaoDTO;
import br.com.atilajf.processos.entity.AnalistaEntity;
import br.com.atilajf.processos.entity.ProcessoEntity;
import br.com.atilajf.processos.entity.SetorEntity;
import br.com.atilajf.processos.entity.TramitacaoEntity;
import br.com.atilajf.processos.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TramitacaoMapper {

	private final ProcessoMapper processoMapper;
	private final SetorMapper setorMapper;
	private final AnalistaMapper analistaMapper;
	private final UsuarioMapper usuarioMapper;
	
	
	public TramitacaoDTO toDtoTramitacao(TramitacaoEntity tramitacaoEntity) {
		
		return TramitacaoDTO.builder()
				            .id(tramitacaoEntity.getId())
				            .processo(processoMapper.toDtoProcesso(tramitacaoEntity.getProcesso()))
				            .setorOrigem(setorMapper.toDtoSetor(tramitacaoEntity.getSetorOrigem()))
				            .setorDestino(setorMapper.toDtoSetor(tramitacaoEntity.getSetorDestino()))
				            .analista(analistaMapper.toDtoAnalista(tramitacaoEntity.getAnalista()))
				            .dataTramitacao(tramitacaoEntity.getDataTramitacao())
				            .usuario(usuarioMapper.toDtoUsuario(tramitacaoEntity.getUsuario()))
				            .descricaoObservacao(tramitacaoEntity.getDescricaoObservacao())
				            .build();
	}
	
	
	public TramitacaoEntity toEntityTramitacao(TramitacaoDTO tramitacaoDto, ProcessoEntity processoEntity, SetorEntity setorOrigemEntity, SetorEntity setorDestinoEntity, AnalistaEntity analistaEntity, UsuarioEntity usuarioEntity) {
		
		final var tramitacaoEntity = new TramitacaoEntity();

		tramitacaoEntity.setProcesso(processoEntity);		                                                              
		tramitacaoEntity.setSetorOrigem(setorOrigemEntity);
		tramitacaoEntity.setSetorDestino(setorDestinoEntity);
        tramitacaoEntity.setAnalista(analistaEntity);	
        tramitacaoEntity.setUsuario(usuarioEntity);
		tramitacaoEntity.setDataTramitacao(tramitacaoDto.getDataTramitacao());
		tramitacaoEntity.setDescricaoObservacao(tramitacaoDto.getDescricaoObservacao());
		
		return tramitacaoEntity;
	}
}
