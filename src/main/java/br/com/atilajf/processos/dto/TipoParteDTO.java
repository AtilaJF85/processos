package br.com.atilajf.processos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoParteDTO {

	private String id;
	
	private String codigoTipoParte;

	private String descricaoTipoParte;

	private String registroAtivo;

}
