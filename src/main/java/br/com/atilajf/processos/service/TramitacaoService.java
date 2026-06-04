package br.com.atilajf.processos.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.atilajf.processos.dto.TramitacaoDTO;
import br.com.atilajf.processos.exception.IdNaoExisteException;
import br.com.atilajf.processos.mapper.TramitacaoMapper;
import br.com.atilajf.processos.repository.AnalistaRepository;
import br.com.atilajf.processos.repository.ProcessoRepository;
import br.com.atilajf.processos.repository.SetorRepository;
import br.com.atilajf.processos.repository.TramitacaoRepository;
import br.com.atilajf.processos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class TramitacaoService {

	private final TramitacaoRepository tramitacaoRepository;
	private final ProcessoRepository processoRepository;
	private final SetorRepository setorRepository;
	private final AnalistaRepository analistaRepository;
	private final UsuarioRepository usuarioRepository;
	private final TramitacaoMapper tramitacaoMapper;

	
	
	public List<TramitacaoDTO> listar() {
		return tramitacaoRepository.findAll()
				                   .stream()
				                   .map(tramitacaoMapper :: toDtoTramitacao)
				                   .toList();

	}
	
	

	public TramitacaoDTO pesquisarPorId(Long id) {
		
		final var tramitacaoEntity = tramitacaoRepository.findById(id)
				                                         .orElseThrow(() -> new IdNaoExisteException("TRAMITAÇÃO com ID " + id + " Não existe!"));
		
		return tramitacaoMapper.toDtoTramitacao(tramitacaoEntity);
	}

	
	
	
	@Transactional
	public TramitacaoDTO cadastro(TramitacaoDTO tramitacaoDto) {

		final var processoEntity = processoRepository.findById(tramitacaoDto.getProcesso().getId()).orElseThrow(() -> new IdNaoExisteException("Não existe PROCESSO com o ID informado."));
		final var setorOrigemEntity = setorRepository.findById(tramitacaoDto.getSetorOrigem().getId()).orElseThrow(() -> new IdNaoExisteException("Não existe SETOR DE ORIGEM com o ID informado."));
		final var setorDestinoEntity = setorRepository.findById(tramitacaoDto.getSetorDestino().getId()).orElseThrow(() -> new IdNaoExisteException("Não existe SETOR DE DESTINO com o ID informado."));
		final var analistaEntity = analistaRepository.findById(tramitacaoDto.getAnalista().getId()).orElseThrow(() -> new IdNaoExisteException("Não existe ANALISTA com o ID informado."));
		final var usuarioEntity = usuarioRepository.findById(tramitacaoDto.getUsuario().getId()).orElseThrow(() -> new IdNaoExisteException("Não existe USUARIO com o ID informado."));
		
		final var tramitacaoEntity = tramitacaoMapper.toEntityTramitacao(tramitacaoDto, processoEntity, setorOrigemEntity, setorDestinoEntity, analistaEntity, usuarioEntity);

		tramitacaoRepository.save(tramitacaoEntity);
		tramitacaoDto.setId(tramitacaoEntity.getId());
		
		return tramitacaoDto;

	}

}
