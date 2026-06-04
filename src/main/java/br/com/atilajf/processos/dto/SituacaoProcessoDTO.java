package br.com.atilajf.processos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SituacaoProcessoDTO {

	private String id; 
	
	private String CodigoSituacaoProcesso;

	private String descricaoSituacao;

	private String registroAtivo;

}
