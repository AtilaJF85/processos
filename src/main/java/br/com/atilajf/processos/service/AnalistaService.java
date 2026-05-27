package br.com.atilajf.processos.service;

import java.util.List;

import br.com.atilajf.processos.mapper.PessoaFisicaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.atilajf.processos.dto.AnalistaDTO;
import br.com.atilajf.processos.dto.SetorDTO;
import br.com.atilajf.processos.dto.PessoaFisicaDTO;
import br.com.atilajf.processos.entity.AnalistaEntity;
import br.com.atilajf.processos.exception.IdNaoExisteException;
import br.com.atilajf.processos.repository.AnalistaRepository;
import br.com.atilajf.processos.repository.PessoaFisicaRepository;
import br.com.atilajf.processos.repository.SetorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class AnalistaService {

	private final AnalistaRepository analistaRepository;
	private final PessoaFisicaRepository pessoaFisicaRepository;
	private final SetorRepository setorRepository;

    private final PessoaFisicaMapper pessoaFisicaMapper;


	public List<AnalistaDTO> listarTodos() {
		return analistaRepository.findAll().stream()
				                           .map(analistaEntity -> AnalistaDTO.builder()
				                        		                             .id(analistaEntity.getId())
				                        		                             .dataVinculo(analistaEntity.getDataVinculo())
				                        		                             .registroAtivo(analistaEntity.getRegistroAtivo())

				                        		                             .pessoaFisica(pessoaFisicaMapper.toDto(analistaEntity.getPessoaFisica()))
				                        		                             .setor(SetorDTO.builder()
				                        		                            		        .id(analistaEntity.getSetor().getId())
				                        		                            		        .nomeSetor(analistaEntity.getSetor().getNomeSetor())
				                        		                            		        .build())
				                        		                             .build())
				                           .toList();


	}

    public AnalistaDTO recuperarPorId(Long id) {
        final var analistaEntity = analistaRepository.findById(id).orElseThrow(() -> new IdNaoExisteException("Não existe analista com o ID informado."));
        return AnalistaDTO.builder()
                .id(analistaEntity.getId())
                .dataVinculo(analistaEntity.getDataVinculo())
                .registroAtivo(analistaEntity.getRegistroAtivo())
                .setor(SetorDTO.builder()
                        .id(analistaEntity.getSetor().getId())
                        .nomeSetor(analistaEntity.getSetor().getNomeSetor())
                        .siglaSetor(analistaEntity.getSetor().getSiglaSetor())
                        .descricaoSetor(analistaEntity.getSetor().getDescricaoSetor())
                        .registroAtivo(analistaEntity.getSetor().getRegistroAtivo())
                        .build())
                .pessoaFisica(pessoaFisicaMapper.toDto(analistaEntity.getPessoaFisica()))
                .build();


    }


	@Transactional
	public AnalistaDTO cadastro(AnalistaDTO analistaDto) {

		final var analistaEntity = new AnalistaEntity();

		analistaEntity.setPessoaFisica(pessoaFisicaRepository.findById(analistaDto.getPessoaFisica()
				                                                                  .getId())
				                                                                  .orElseThrow(() -> new IdNaoExisteException("PESSOA FISICA com ID " + analistaDto.getPessoaFisica().getId() + " Não existe!")));

		analistaEntity.setSetor(setorRepository.findById(analistaDto.getSetor()
				                                                    .getId())
				                                                    .orElseThrow(() -> new IdNaoExisteException("SETOR com ID " + analistaDto.getSetor().getId() + " Não existe!")));

		analistaEntity.setDataVinculo(analistaDto.getDataVinculo());
		analistaEntity.setRegistroAtivo(analistaDto.getRegistroAtivo());

		analistaRepository.save(analistaEntity);
		analistaDto.setId(analistaEntity.getId());

        return analistaDto;

	}

}
