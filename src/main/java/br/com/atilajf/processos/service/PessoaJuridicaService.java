package br.com.atilajf.processos.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.atilajf.processos.dto.PessoaJuridicaDTO;
import br.com.atilajf.processos.entity.PessoaJuridicaEntity;
import br.com.atilajf.processos.repository.PessoaJuridicaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class PessoaJuridicaService {

	private final PessoaJuridicaRepository pessoaJuridicaRepository;
	
	public List<PessoaJuridicaDTO> listarTodos(){
		return pessoaJuridicaRepository.findAll().stream()
				                                 .map(pessoaJuridicaEntity -> PessoaJuridicaDTO.builder()
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
				                                		                                       .build())
				                                 .toList();
				                                 
	}
	
	
	@Transactional
	public PessoaJuridicaDTO cadastro(PessoaJuridicaDTO pessoaJuridicaDto) {
		
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
		
        if (pessoaJuridicaEntity.getId() != null && pessoaJuridicaEntity.getCnpj() != null) {
			pessoaJuridicaRepository.save(pessoaJuridicaEntity);
			pessoaJuridicaDto.setId(pessoaJuridicaEntity.getId());
		}
        else {
			throw new RuntimeException("Informação divergente");
		}
		return pessoaJuridicaDto;

	}
	
}
