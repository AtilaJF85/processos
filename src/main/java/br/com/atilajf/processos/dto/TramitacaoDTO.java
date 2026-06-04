package br.com.atilajf.processos.dto;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TramitacaoDTO {

	private Long id;

	private LocalDateTime dataTramitacao;

	private String descricaoObservacao;

	private ProcessoDTO processo;

	private AnalistaDTO analista;

	private UsuarioDTO usuario;

	private SetorDTO setorOrigem;
	
	private SetorDTO setorDestino;

}
