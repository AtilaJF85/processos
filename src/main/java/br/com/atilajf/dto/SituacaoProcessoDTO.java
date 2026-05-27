package br.com.atilajf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SituacaoProcessoDTO {

	private Long id; 
	
	private String CodigoSituacaoProcesso;

	private String descricaoSituacao;

	private String registroAtivo;

}
