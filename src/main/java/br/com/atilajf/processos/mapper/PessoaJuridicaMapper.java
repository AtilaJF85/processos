package br.com.atilajf.processos.mapper;

import org.springframework.stereotype.Component;
import br.com.atilajf.processos.dto.PessoaJuridicaDTO;
import br.com.atilajf.processos.entity.PessoaJuridicaEntity;

@Component
public class PessoaJuridicaMapper {

	public PessoaJuridicaDTO toDtoPessoaJuridica(PessoaJuridicaEntity pessoaJuridicaEntity) {

		if(pessoaJuridicaEntity == null) {
			return null;
		}
		
		return PessoaJuridicaDTO.builder()
				                .id(pessoaJuridicaEntity.getId())
								.nomeRazaoSocial(pessoaJuridicaEntity.getNomeRazaoSocial())
								.nomeFantasia(pessoaJuridicaEntity.getNomeFantasia())
								.cnpj(pessoaJuridicaEntity.getCnpj())
								.email(pessoaJuridicaEntity.getEmail())
								.numeroTelefone(pessoaJuridicaEntity.getNumeroTelefone())
								.logradouro(pessoaJuridicaEntity.getLogradouro())
								.complemento(pessoaJuridicaEntity.getComplemento())
								.nomeBairro(pessoaJuridicaEntity.getNomeBairro())
								.nomeMunicipio(pessoaJuridicaEntity.getNomeMunicipio())
								.uf(pessoaJuridicaEntity.getUf())
								.cep(pessoaJuridicaEntity.getCep())
								.registroAtivo(pessoaJuridicaEntity.getRegistroAtivo())
								.build();
								
	}
	
	public PessoaJuridicaEntity toEntityPessoaJuridicaCadastro(PessoaJuridicaDTO pessoaJuridicaDto) {
		
		final var pessoaJuridicaEntity = new PessoaJuridicaEntity();
		
		pessoaJuridicaEntity.setNomeRazaoSocial(pessoaJuridicaDto.getNomeRazaoSocial());
		pessoaJuridicaEntity.setNomeFantasia(pessoaJuridicaDto.getNomeFantasia());
		pessoaJuridicaEntity.setCnpj(pessoaJuridicaDto.getCnpj());
		pessoaJuridicaEntity.setEmail(pessoaJuridicaDto.getEmail());
		pessoaJuridicaEntity.setNumeroTelefone(pessoaJuridicaDto.getNumeroTelefone());
		pessoaJuridicaEntity.setLogradouro(pessoaJuridicaDto.getLogradouro());
		pessoaJuridicaEntity.setComplemento(pessoaJuridicaDto.getComplemento());
		pessoaJuridicaEntity.setNomeBairro(pessoaJuridicaDto.getNomeBairro());
		pessoaJuridicaEntity.setNomeMunicipio(pessoaJuridicaDto.getNomeMunicipio());
		pessoaJuridicaEntity.setUf(pessoaJuridicaDto.getUf());
		pessoaJuridicaEntity.setCep(pessoaJuridicaDto.getCep());
		pessoaJuridicaEntity.setRegistroAtivo(pessoaJuridicaDto.getRegistroAtivo());
		return pessoaJuridicaEntity;
		
	}
}
