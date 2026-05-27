package br.com.atilajf.processos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetorDTO {

	private Long id;
	
	private String nomeSetor;

	private String siglaSetor;

	private String descricaoSetor;

	private String registroAtivo;
}
