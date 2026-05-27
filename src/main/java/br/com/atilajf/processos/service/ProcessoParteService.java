package br.com.atilajf.processos.service;

import java.util.List;
import org.springframework.stereotype.Service;

import br.com.atilajf.processos.dto.PessoaFisicaDTO;
import br.com.atilajf.processos.dto.PessoaJuridicaDTO;
import br.com.atilajf.processos.dto.ProcessoParteDTO;
import br.com.atilajf.processos.entity.ProcessoParteEntity;
import br.com.atilajf.processos.exception.IdNaoExisteException;
import br.com.atilajf.processos.repository.PessoaFisicaRepository;
import br.com.atilajf.processos.repository.PessoaJuridicaRepository;
import br.com.atilajf.processos.repository.ProcessoParteRepository;
import br.com.atilajf.processos.repository.ProcessoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProcessoParteService {

	private ProcessoParteRepository processoParteRepository;
	private PessoaFisicaRepository pessoaFisicaRepository;
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	private ProcessoRepository processoRepository;
	
	
	public List<ProcessoParteDTO> listarPartesPorProcesso(ProcessoParteDTO processoParteDto){
		
		return processoParteRepository.findByProcessoId(processoParteDto.getProcesso().getId()).stream()
				                                                                               .map(processoParteEntity -> ProcessoParteDTO.builder()
				                                                                            		                                       .pessoaFisica(PessoaFisicaDTO.builder()
				                                                                            		                                    		   .nomePessoaFisica(processoParteEntity.getPessoaFisica().getNomePessoaFisica())
				                                                                            		                                    		   .cpf(processoParteEntity.getPessoaFisica().getCpf())
				                                                                            		                                    		   .email(processoParteEntity.getPessoaFisica().getEmail())
				                                                                            		                                    		   .logradouro(processoParteEntity.getPessoaFisica().getLogradouro())
				                                                                            		                                    		   .cep(processoParteEntity.getPessoaFisica().getCep())
				                                                                            		                                    		   .nomeBairro(processoParteEntity.getPessoaFisica().getNomeBairro())
				                                                                            		                                    		   .nomeMunicipio(processoParteEntity.getPessoaFisica().getNomeMunicipio())
				                                                                            		                                    		   .build())
				                                                                            		                                       .pessoaJuridica(PessoaJuridicaDTO.builder()
				                                                                            		                                    		   .nomeRazaoSocial(processoParteEntity.getPessoaJuridica().getNomeRazaoSocial())
				                                                                            		                                    		   .cnpj(processoParteEntity.getPessoaJuridica().getCnpj())
				                                                                            		                                    		   .email(processoParteEntity.getPessoaJuridica().getEmail())
				                                                                            		                                    		   .logradouro(processoParteEntity.getPessoaJuridica().getLogradouro())
				                                                                            		                                    		   .cep(processoParteEntity.getPessoaJuridica().getCep())
				                                                                            		                                    		   .nomeBairro(processoParteEntity.getPessoaJuridica().getNomeBairro())
				                                                                            		                                    		   .nomeMunicipio(processoParteEntity.getPessoaJuridica().getNomeMunicipio())
				                                                                            		                                    		   .build())
				                                                                            		                                       .build())
				                                                                               .toList();
				                                                             
		
	}
	
	public ProcessoParteDTO associarParteProcesso(ProcessoParteDTO processoParteDto) {
		
		final var processoParteEntity = new ProcessoParteEntity();
		
		processoParteEntity.setProcesso(processoRepository.findById(processoParteDto.getProcesso()
				                                                                    .getId())
				                                                                    .orElseThrow(() -> new IdNaoExisteException("PROCESSO com ID " + processoParteDto.getProcesso().getId() + " Não existe!")));
		
//		processoParteEntity.setPessoaFisica(processoParteDto.getPessoaFisica().builder())
				                                                              
				                                                              
		
		if (processoParteDto.getProcesso().getId() .equals(processoParteEntity.getProcesso().getId())) {
			
			
			
		}
		
		return null;
		
	}
}
