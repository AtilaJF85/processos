package br.com.atilajf.processos.entity;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_tipo_parte")
public class TipoParteEntity {

	@Id
	@Column(name = "co_tipo_parte", length = 30, nullable = false)
	private String id;

	@Column(name = "ds_tipo_parte", length = 200, nullable = false)
	private String descricaoTipoParte;

	@Column(name = "st_registro_ativo", length = 200, nullable = false)
	private String registroAtivo;

	@OneToMany(mappedBy = "tipoParte", fetch = FetchType.LAZY)
	private List<ProcessoParteEntity> processoParte;

}