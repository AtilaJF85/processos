package br.com.atilajf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.atilajf.dto.AnalistaDTO;
import br.com.atilajf.dto.ProcessoDTO;
import br.com.atilajf.dto.SetorDTO;
import br.com.atilajf.dto.TramitacaoDTO;
import br.com.atilajf.dto.UsuarioDTO;
import br.com.atilajf.entity.TramitacaoEntity;
import br.com.atilajf.exception.IdNaoExisteException;
import br.com.atilajf.repository.AnalistaRepository;
import br.com.atilajf.repository.ProcessoRepository;
import br.com.atilajf.repository.SetorRepository;
import br.com.atilajf.repository.TramitacaoRepository;
import br.com.atilajf.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class TramitacaoService {

	private TramitacaoRepository tramitacaoRepository;
	private ProcessoRepository processoRepository;
	private SetorRepository setorRepository;
	private AnalistaRepository analistaRepository;
	private UsuarioRepository usuarioRepository;

	public List<TramitacaoDTO> listar() {
		return tramitacaoRepository.findAll().stream()
				                             .map(tramitacaoEntity -> TramitacaoDTO.builder()
				                            		                               .id(tramitacaoEntity.getId())
                                                                                   .dataTramitacao(tramitacaoEntity.getDataTramitacao())
                                                                                   .descricaoObservacao(tramitacaoEntity.getDescricaoObservacao())
                                                                
                                                                                   .processo(ProcessoDTO.builder()
                                                                		                                .id(tramitacaoEntity.getProcesso().getId())
                                                                		                                .build())
						                                                           .setorOrigem(SetorDTO.builder()
						                                        		                                .id(tramitacaoEntity.getSetorOrigem().getId())
						                                        		                                .build())
						                                                           .setorDestino(SetorDTO.builder()
						                                        		                                 .id(tramitacaoEntity.getSetorDestino().getId())
						                                                                                 .build())
						                                                           .analista(AnalistaDTO.builder()
						                                        		                                .id(tramitacaoEntity.getAnalista().getId())
						                                        		                                .build())
						                                                           .usuario(UsuarioDTO.builder()
						                                        		                              .id(tramitacaoEntity.getUsuario().getId())
						                                        		                              .build())
						                                                           .build())
				                              .toList();

	}
	

	public TramitacaoDTO pesquisarPorId(Long id) {
		final var tramitacaoEntity = tramitacaoRepository.findById(id)
				                                         .orElseThrow(() -> new IdNaoExisteException("TRAMITAÇÃO com ID " + id + " Não existe!"));
		return TramitacaoDTO.builder()
				            .id(tramitacaoEntity.getId())
				            .dataTramitacao(tramitacaoEntity.getDataTramitacao())
				            .descricaoObservacao(tramitacaoEntity.getDescricaoObservacao())
				
				            .processo(ProcessoDTO.builder()
						                         .id(tramitacaoEntity.getProcesso().getId())
						                         .numeroProcesso(tramitacaoEntity.getProcesso().getNumeroProcesso())
						                         .build())
				            .setorOrigem(SetorDTO.builder()
						                         .id(tramitacaoEntity.getSetorOrigem().getId())
						                         .nomeSetor(tramitacaoEntity.getSetorOrigem().getNomeSetor())
						                         .build())
				            .setorDestino(SetorDTO.builder()
						                          .id(tramitacaoEntity.getSetorDestino().getId())
						                          .nomeSetor(tramitacaoEntity.getSetorDestino().getNomeSetor())
						                          .build())
				            .analista(AnalistaDTO.builder()
						                         .id(tramitacaoEntity.getAnalista().getId())
                                                 .build())
				            .usuario(UsuarioDTO.builder()
						                       .id(tramitacaoEntity.getUsuario().getId())
                                               .build())

				            .build();
	}

	@Transactional
	public TramitacaoDTO cadastro(TramitacaoDTO tramitacaoDto) {

		final var tramitacaoEntity = new TramitacaoEntity();

		tramitacaoEntity.setProcesso(processoRepository.findById(tramitacaoDto.getProcesso()
				                                                              .getId())
				                                                              .orElseThrow(() -> new IdNaoExisteException("PROCESSO com ID " + tramitacaoDto.getProcesso().getId() + " Não existe!")));
		
		tramitacaoEntity.setSetorOrigem(setorRepository.findById(tramitacaoDto.getSetorOrigem()
				                                                              .getId())
				                                                              .orElseThrow(() -> new IdNaoExisteException("SETOR DE ORIGEM com ID " + tramitacaoDto.getSetorOrigem().getId() + " Não existe!")));
		
		tramitacaoEntity.setSetorDestino(setorRepository.findById(tramitacaoDto.getSetorDestino()
				                                                               .getId())
				                                                               .orElseThrow(() -> new IdNaoExisteException("SETOR DE DESTINO com ID " + tramitacaoDto.getSetorDestino().getId() + " Não existe!")));
		
		tramitacaoEntity.setAnalista(analistaRepository.findById(tramitacaoDto.getAnalista()
				                                                              .getId())
				                                                              .orElseThrow(() -> new IdNaoExisteException("ANALISTA com ID " + tramitacaoDto.getAnalista().getId() + " Não existe!")));
		
		tramitacaoEntity.setDataTramitacao(tramitacaoDto.getDataTramitacao());
		tramitacaoEntity.setDescricaoObservacao(tramitacaoDto.getDescricaoObservacao());
		
		tramitacaoEntity.setUsuario(usuarioRepository.findById(tramitacaoDto.getUsuario()
				                                                            .getId())
				                                                            .orElseThrow(() -> new IdNaoExisteException("USUARIO com ID " + tramitacaoDto.getUsuario().getId() + " Não existe!")));

		
		tramitacaoRepository.save(tramitacaoEntity);
		tramitacaoDto.setId(tramitacaoEntity.getId());
		return tramitacaoDto;

	}

}
