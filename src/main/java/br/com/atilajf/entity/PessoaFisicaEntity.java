package br.com.atilajf.entity;

import java.util.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_pessoa_fisica")
public class PessoaFisicaEntity {

	@Id
	@Column(name = "co_seq_pessoa_fisica")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "no_pessoa_fisica", length = 200, nullable = false)
	private String nomePessoaFisica;

	@Column(name = "nu_cpf", length = 11, nullable = false)
	private String cpf;

	@Column(name = "dt_nascimento", nullable = false)
	private Date dataDeNascimento;

	@Column(name = "ds_email", length = 200, nullable = false)
	private Date email;

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
	private String servicoAtivo;

	@OneToMany(mappedBy = "pessoaFisica", fetch = FetchType.LAZY)
	private List<ProcessoParteEntity> processoParte;
	
	@OneToMany(mappedBy = "analista", fetch = FetchType.LAZY)
	private List<AnalistaEntity> analista;

	@OneToMany(mappedBy = "pessoaFisica", fetch = FetchType.LAZY)
	private List<UsuarioEntity> usuario;
}
