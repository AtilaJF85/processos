package br.com.atilajf.processos.mapper;

import org.springframework.stereotype.Component;
import br.com.atilajf.processos.dto.ProcessoDTO;
import br.com.atilajf.processos.dto.ProcessoParteDTO;
import br.com.atilajf.processos.entity.ProcessoEntity;
import br.com.atilajf.processos.entity.ProcessoParteEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProcessoParteMapper {

	private final PessoaFisicaMapper pessoaFisicaMapper;
	private final PessoaJuridicaMapper pessoaJuridicaMapper;

	public ProcessoParteDTO toDtoProcessoParte(ProcessoParteEntity processoParteEntity) {

		return ProcessoParteDTO.builder()
				.processo(ProcessoDTO.builder().id(processoParteEntity.getProcesso().getId())
						.numeroProcesso(processoParteEntity.getProcesso().getNumeroProcesso())
						.descricaoAssunto(processoParteEntity.getProcesso().getDescricaoAssunto()).build())
				.pessoaFisica(pessoaFisicaMapper.toDtoPessoaFisica(processoParteEntity.getPessoaFisica()))
				.pessoaJuridica(pessoaJuridicaMapper.toDtoPessoaJuridica(processoParteEntity.getPessoaJuridica()))
				.build();
	}

	public ProcessoParteEntity toEntityProcessoParteCadastroPartes(ProcessoParteDTO processoParteDto, ProcessoEntity processoEntity) {

		final var processoParteEntity = new ProcessoParteEntity();

		processoParteEntity.setProcesso(processoEntity);
		processoParteEntity.setPessoaFisica(pessoaFisicaMapper.toEntityPessoaFisicaCadastro(processoParteDto.getPessoaFisica()));
		processoParteEntity.setPessoaJuridica(pessoaJuridicaMapper.toEntityPessoaJuridicaCadastro(processoParteDto.getPessoaJuridica()));
		
		return processoParteEntity;
	}
}
