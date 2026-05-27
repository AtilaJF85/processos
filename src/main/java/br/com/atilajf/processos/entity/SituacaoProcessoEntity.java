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
@Table(name = "tb_situacao_processo")
public class SituacaoProcessoEntity {

	@Id
	@Column(name = "co_situacao_processo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ds_situacao", length = 100, nullable = false)
	private String descricaoSituacao;
	
	@Column(name = "st_registro_ativo", length = 1, nullable = false)
	private String registroAtivo;

	@OneToMany(mappedBy = "situacaoProcesso", fetch = FetchType.LAZY)
	private List<ProcessoEntity> processo;
}
