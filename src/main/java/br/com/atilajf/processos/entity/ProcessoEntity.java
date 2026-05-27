package br.com.atilajf.processos.entity;

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
@Table(name = "tb_processo")
public class ProcessoEntity {

	@Id
	@Column(name = "co_seq_processo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nu_processo", length = 30, nullable = false)
	private String numeroProcesso;

	@Column(name = "ds_assunto", length = 500, nullable = false)
	private String descricaoAssunto;

	@Column(name = "dt_abertura", nullable = false)
	private Timestamp dataAbertura;

	@Column(name = "st_registro_ativo", length = 1, nullable = false)
	private String registroAtivo;

	@ManyToOne
	@JoinColumn(name = "usuario_abertura_id", referencedColumnName = "co_seq_usuario")
	private UsuarioEntity usuarioAbertura;

	@ManyToOne
	@JoinColumn(name = "situacao_processo_id", referencedColumnName = "co_situacao_processo")
	private SituacaoProcessoEntity situacaoProcesso;

	@OneToMany(mappedBy = "processo", fetch = FetchType.LAZY)
	private List<ProcessoParteEntity> processoParte;

	@OneToMany(mappedBy = "processo", fetch = FetchType.LAZY)
	private List<TramitacaoEntity> tramitacao;
	
	@OneToMany(mappedBy = "processo", fetch = FetchType.LAZY)
	private List<RegistroEntity> registro;
}
