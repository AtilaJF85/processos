package br.com.atilajf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoParteDTO {

	private Long id;
	
	private String codigoTipoParte;

	private String descricaoTipoParte;

	private String registroAtivo;

}
