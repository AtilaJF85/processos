package br.com.atilajf.entity;

import java.security.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "th_tramitacao")
public class TramitacaoEntity {

	@Id
	@Column(name = "co_seq_tramitacao")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dt_tramitacao", nullable = false)
	private Timestamp dataTramitacao;

	@Column(name = "ds_observacao", length = 1000, nullable = false)
	private String descricaoObservacao;

	@ManyToOne
	@JoinColumn(name = "processo_id", referencedColumnName = "co_seq_processo")
	private ProcessoEntity processo;

	@ManyToOne
	@JoinColumn(name = "analista_id", referencedColumnName = "co_seq_analista")
	private AnalistaEntity analista;

	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "co_seq_usuario")
	private UsuarioEntity usuario;

	@ManyToOne
	@JoinColumn(name = "setor_origem_id", referencedColumnName = "co_seq_setor_origem")
	private SetorEntity setorOrigem;
	
	@ManyToOne
	@JoinColumn(name = "setor_destino_id", referencedColumnName = "co_seq_setor_destino")
	private SetorEntity setorDestino;

}
