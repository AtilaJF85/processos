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
public class UsuarioDTO {

	private Long id;
	
	private String descricaoSubOidc;

	private Timestamp dataPrimeiroAcesso;

	private Timestamp dataUltimoAcesso;

	private String registroAtivo;

	private PessoaFisicaDTO pessoaFisica;
}
