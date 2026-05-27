package br.com.atilajf.entity;

import java.util.Date;
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

@Getter
@Setter
@Entity
@Table(name = "tb_analista")
public class AnalistaEntity {

	@Id
	@Column(name = "co_seq_analista")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dt_vinculo", nullable = false)
	private Date dataVinculo;

	@Column(name = "st_registro_ativo", length = 1, nullable = false)
	private String registroAtivo;

	@ManyToOne
	@JoinColumn(name = "setor_id", referencedColumnName = "co_seq_setor")
	private SetorEntity setor;

	@ManyToOne
	@JoinColumn(name = "analista_id", referencedColumnName = "co_seq_pessoa_fisica")
	private PessoaFisicaEntity pessoaFisica;

	@OneToMany(mappedBy = "analista", fetch = FetchType.LAZY)
	private List<TramitacaoEntity> tramitacao;
}
