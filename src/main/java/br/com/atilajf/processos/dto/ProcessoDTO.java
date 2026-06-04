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
public class ProcessoDTO {

	private Long id;
	
	private String numeroProcesso;

	private String descricaoAssunto;

	private LocalDateTime dataAbertura;

	private String registroAtivo;

	private UsuarioDTO usuarioAbertura;

	private SituacaoProcessoDTO situacaoProcesso;
}
