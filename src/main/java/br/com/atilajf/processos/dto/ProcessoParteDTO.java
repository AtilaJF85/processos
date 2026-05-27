package br.com.atilajf.processos.dto;

import java.security.Timestamp;
import br.com.atilajf.processos.entity.TipoParteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoParteDTO {

	private Long id;

	private String tipoPessoa;

	private Timestamp dataVinculo;

	private String registroAtivo;

	private ProcessoDTO processo;

	private PessoaFisicaDTO pessoaFisica;

	private PessoaJuridicaDTO pessoaJuridica;

	private TipoParteEntity tipoParte;

}
