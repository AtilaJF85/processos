package br.com.atilajf.processos.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFisicaDTO {
	
	private Long id;

	private String nomePessoaFisica;

	private String cpf;

	private Date dataDeNascimento;

	private Date email;

	private String numeroTelefone;

	private String logradouro;

	private String complemento;

	private String nomeBairro;

	private String nomeMunicipio;

	private String uf;

	private String cep;

	private String servicoAtivo;
	
	private String cnh;

}
