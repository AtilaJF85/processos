package br.com.atilajf.processos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaJuridicaDTO {
	
	private Long id;

	private String nomeRazaoSocial;

	private String nomeFantasia;

	private String cnpj;

	private String email;

	private String numeroTelefone;

	private String logradouro;

	private String complemento;

	private String nomeBairro;

	private String nomeMunicipio;

	private String uf;

	private String cep;

	private String registroAtivo;

}
