package br.com.atilajf.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.atilajf.dto.PessoaFisicaDTO;
import br.com.atilajf.entity.PessoaFisicaEntity;
import br.com.atilajf.repository.PessoaFisicaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
@Service
public class PessoaFisicaService {

	private final PessoaFisicaRepository pessoaFisicaRepository;
	
	
	public List<PessoaFisicaDTO> ListarTodos(){
		return pessoaFisicaRepository.findAll().stream()
				                               .map(pessoaFisicaEntity -> PessoaFisicaDTO.builder()
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
				                            		                                     .build())
				                                                                         .toList();
	}
	
	
	@Transactional
	public PessoaFisicaDTO cadastro(PessoaFisicaDTO pessoaFisicaDto) {
		
		PessoaFisicaEntity pessoaFisicaEntity = new PessoaFisicaEntity();
		
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
       
		if (pessoaFisicaEntity.getId() != null && pessoaFisicaEntity.getCpf() != null) {
			 pessoaFisicaRepository.save(pessoaFisicaEntity);
			 
		}
        else {
			throw new RuntimeException("Informação divergente");
		}
		return pessoaFisicaDto;

	}
}
