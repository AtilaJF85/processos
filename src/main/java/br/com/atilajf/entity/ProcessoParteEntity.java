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
@Table(name = "rl_processo_parte")
public class ProcessoParteEntity {

	@Id
	@Column(name = "co_seq_parte")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tp_pessoa", length = 1, nullable = false)
	private String tipoPessoa;
	
	@Column(name = "dt_vinculo", nullable = false)
	private Timestamp dataVinculo;
	
	@Column(name = "st_registro_ativo", length = 1, nullable = false)
	private String registroAtivo;
	
	@ManyToOne
	@JoinColumn(name = "processo_id", referencedColumnName = "co_seq_processo")
	private ProcessoEntity processo;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_fisica_id", referencedColumnName = "co_seq_pessoa_fisica")
	private PessoaFisicaEntity pessoaFisica;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_juridica_id", referencedColumnName = "co_seq_pessoa_juridica")
	private PessoaJuridicaEntity pessoaJuridica;
	
	@ManyToOne
	@JoinColumn(name = "tipo_parte_id", referencedColumnName = "co_seq_tipo_parte")
	private TipoParteEntity tipoParte;
	
	
}
