package br.com.atilajf.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalistaDTO {


	private Long id; 
	
	private Date dataVinculo;

	private String registroAtivo;

	private SetorDTO setor;

	private PessoaFisicaDTO pessoaFisica;

}
