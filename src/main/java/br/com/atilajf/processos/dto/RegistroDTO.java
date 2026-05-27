package br.com.atilajf.processos.dto;

import java.security.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDTO {

	private Long id;
	
	private String descricaoRegistro;

	private Timestamp dataRegistro;

	private String registroAtivo;

	private ProcessoDTO processo;

	private UsuarioDTO usuario;

}
