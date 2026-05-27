package br.com.atilajf.processos.mapper;

import br.com.atilajf.processos.dto.PessoaFisicaDTO;
import br.com.atilajf.processos.entity.PessoaFisicaEntity;
import org.springframework.stereotype.Component;

@Component
public class PessoaFisicaMapper {

    public PessoaFisicaDTO toDto(PessoaFisicaEntity entity) {
        return PessoaFisicaDTO.builder()
                .id(entity.getId())
                .nomePessoaFisica(entity.getNomePessoaFisica())
                .cpf(entity.getCpf())
                .dataDeNascimento(entity.getDataDeNascimento())
                .email(entity.getEmail())
                .numeroTelefone(entity.getNumeroTelefone())
                .logradouro(entity.getLogradouro())
                .complemento(entity.getComplemento())
                .nomeBairro(entity.getNomeBairro())
                .nomeMunicipio(entity.getNomeMunicipio())
                .uf(entity.getUf())
                .cep(entity.getCep())
                .servicoAtivo(entity.getServicoAtivo())
                .build();
    }

    public PessoaFisicaEntity toEntity(PessoaFisicaDTO pessoaFisicaDto) {
        final PessoaFisicaEntity pessoaFisicaEntity = new PessoaFisicaEntity();

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
