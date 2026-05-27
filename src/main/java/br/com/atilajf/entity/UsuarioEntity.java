package br.com.atilajf.entity;

import java.security.Timestamp;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_usuario")
public class UsuarioEntity {

	@Id
	@Column(name = "co_seq_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ds_sub_oidc", length = 255, nullable = false)
	private String descricaoSubOidc;

	@Column(name = "dt_primeiro_acesso", nullable = false)
	private Timestamp dataPrimeiroAcesso;

	@Column(name = "dt_ultimo_acesso", nullable = false)
	private Timestamp dataUltimoAcesso;

	@Column(name = "st_registro_ativo", length = 1, nullable = false)
	private String registroAtivo;

	@ManyToOne
	@JoinColumn(name = "pessoa_fisica_id", referencedColumnName = "co_seq_pessoa_fisica")
	private PessoaFisicaEntity pessoaFisica;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<TramitacaoEntity> tramitacao;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<RegistroEntity> registro;
	
	@OneToMany(mappedBy = "usuarioAbertura", fetch = FetchType.LAZY)
	private List<UsuarioEntity> usuario;

}
