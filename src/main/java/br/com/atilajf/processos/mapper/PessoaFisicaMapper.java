package br.com.atilajf.processos.mapper;

import br.com.atilajf.processos.dto.PessoaFisicaDTO;
import br.com.atilajf.processos.entity.PessoaFisicaEntity;
import org.springframework.stereotype.Component;

@Component
public class PessoaFisicaMapper {

    public PessoaFisicaDTO toDtoPessoaFisica(PessoaFisicaEntity pessoaFisicaEntity) {
    	
    	if(pessoaFisicaEntity == null) {
    		return null;
    	}
    	
        return PessoaFisicaDTO.builder()
                .id(pessoaFisicaEntity.getId())
                .nomePessoaFisica(pessoaFisicaEntity.getNomePessoaFisica())
                .cpf(pessoaFisicaEntity.getCpf())
                .dataDeNascimento(pessoaFisicaEntity.getDataDeNascimento())
                .email(pessoaFisicaEntity.getEmail())
                .numeroTelefone(pessoaFisicaEntity.getNumeroTelefone())
                .logradouro(pessoaFisicaEntity.getLogradouro())
                .complemento(pessoaFisicaEntity.getComplemento())
                .nomeBairro(pessoaFisicaEntity.getNomeBairro())
                .nomeMunicipio(pessoaFisicaEntity.getNomeMunicipio())
                .uf(pessoaFisicaEntity.getUf())
                .cep(pessoaFisicaEntity.getCep())
                .servicoAtivo(pessoaFisicaEntity.getServicoAtivo())
                .build();
    }

    public PessoaFisicaEntity toEntityPessoaFisicaCadastro(PessoaFisicaDTO pessoaFisicaDto) {
       
    	final var pessoaFisicaEntity = new PessoaFisicaEntity();

        pessoaFisicaEntity.setNomePessoaFisica(pessoaFisicaDto.getNomePessoaFisica());
        pessoaFisicaEntity.setCpf(pessoaFisicaDto.getCpf());
        pessoaFisicaEntity.setDataDeNascimento(pessoaFisicaDto.getDataDeNascimento());
        pessoaFisicaEntity.setEmail(pessoaFisicaDto.getEmail());
        pessoaFisicaEntity.setNumeroTelefone(pessoaFisicaDto.getNumeroTelefone());
        pessoaFisicaEntity.setLogradouro(pessoaFisicaDto.getLogradouro());
        pessoaFisicaEntity.setComplemento(pessoaFisicaDto.getComplemento());
        pessoaFisicaEntity.setNomeBairro(pessoaFisicaDto.getNomeBairro());
        pessoaFisicaEntity.setNomeMunicipio(pessoaFisicaDto.getNomeMunicipio());
        pessoaFisicaEntity.setUf(pessoaFisicaDto.getUf());
        pessoaFisicaEntity.setCep(pessoaFisicaDto.getCep());
        pessoaFisicaEntity.setServicoAtivo(pessoaFisicaDto.getServicoAtivo());

        return pessoaFisicaEntity;
    }

}
