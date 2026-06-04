package br.com.atilajf.processos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_pessoa_juridica")
public class PessoaJuridicaEntity {

	@Id
	@Column(name = "co_seq_pessoa_juridica")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "no_razao_social", length = 200, nullable = false)
	private String nomeRazaoSocial;

	@Column(name = "no_nome_fantasia", length = 200, nullable = false)
	private String nomeFantasia;

	@Column(name = "nu_cnpj", length = 14, nullable = false)
	private String cnpj;

	@Column(name = "ds_email", length = 200, nullable = false)
	private String email;

	@Column(name = "nu_telefone", length = 20, nullable = false)
	private String numeroTelefone;

	@Column(name = "ds_logradouro", length = 200, nullable = false)
	private String logradouro;

	@Column(name = "ds_complemento", length = 100, nullable = false)
	private String complemento;

	@Column(name = "no_bairro", length = 100, nullable = false)
	private String nomeBairro;

	@Column(name = "no_municipio", length = 100, nullable = false)
	private String nomeMunicipio;

	@Column(name = "sg_uf", length = 2, nullable = false)
	private String uf;

	@Column(name = "nu_cep", length = 8, nullable = false)
	private String cep;

	@Column(name = "st_registro_ativo", length = 1, nullable = false)
	private String registroAtivo;

	@OneToMany(mappedBy = "pessoaJuridica", fetch = FetchType.LAZY)
	private List<ProcessoParteEntity> processoParte;
}
