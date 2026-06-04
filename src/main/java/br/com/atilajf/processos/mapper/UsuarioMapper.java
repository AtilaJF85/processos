package br.com.atilajf.processos.mapper;

import org.springframework.stereotype.Component;

import br.com.atilajf.processos.dto.UsuarioDTO;
import br.com.atilajf.processos.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UsuarioMapper {

	private final PessoaFisicaMapper pessoaFisicaMapper;
	
	public UsuarioDTO toDtoUsuario(UsuarioEntity usuarioEntity) {
		
		return UsuarioDTO.builder()
				         .id(usuarioEntity.getId())
				         .pessoaFisica(pessoaFisicaMapper.toDtoPessoaFisica(usuarioEntity.getPessoaFisica()))
				         .dataPrimeiroAcesso(usuarioEntity.getDataPrimeiroAcesso())
				         .dataUltimoAcesso(usuarioEntity.getDataUltimoAcesso())
				         .registroAtivo(usuarioEntity.getRegistroAtivo())
				         .build();
	}
}
