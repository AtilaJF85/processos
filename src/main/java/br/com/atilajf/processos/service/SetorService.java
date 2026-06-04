package br.com.atilajf.processos.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.atilajf.processos.dto.SetorDTO;
import br.com.atilajf.processos.exception.IdNaoExisteException;
import br.com.atilajf.processos.mapper.SetorMapper;
import br.com.atilajf.processos.repository.SetorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SetorService {

	private final SetorRepository setorRepository;
	private final SetorMapper setorMapper;

	public List<SetorDTO> listarTodos() {
		return setorRepository.findAll().stream()
				                        .map(setorMapper :: toDtoSetor)
				                        .toList();
	}
	
	
	public SetorDTO buscarPorId(Long id) {
		final var setorEntity = setorRepository.findById(id).orElseThrow(() -> new IdNaoExisteException("Não existe SETOR com o ID informado."));
		return setorMapper.toDtoSetor(setorEntity);
		
	}

	@Transactional
	public SetorDTO cadastrar(SetorDTO setorDto) {

		final var setorEntity = setorMapper.toEntitySetorCadastro(setorDto);

		setorRepository.save(setorEntity);
		setorDto.setId(setorEntity.getId());

		return setorDto;

	}
}
