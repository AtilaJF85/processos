package br.com.atilajf.processos.entity;

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
@Table(name = "tb_setor")
public class SetorEntity {

	@Id
	@Column(name = "co_seq_setor")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "no_setor", length = 200, nullable = false)
	private String nomeSetor;

	@Column(name = "sg_setor", length = 20, nullable = false)
	private String siglaSetor;

	@Column(name = "ds_setor", length = 500, nullable = false)
	private String descricaoSetor;

	@Column(name = "st_registro_ativo", length = 1, nullable = false)
	private String registroAtivo;

	@OneToMany(mappedBy = "setor", fetch = FetchType.LAZY)
	private List<AnalistaEntity> analista;

	@OneToMany(mappedBy = "setorOrigem", fetch = FetchType.LAZY)
	private List<TramitacaoEntity> tramitacaoSetorOrigem;
	
	@OneToMany(mappedBy = "setorDestino", fetch = FetchType.LAZY)
	private List<TramitacaoEntity> tramitacaoSetorDestino;

}
