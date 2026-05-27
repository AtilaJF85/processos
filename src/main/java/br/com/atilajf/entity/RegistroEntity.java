package br.com.atilajf.entity;

import java.security.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_registro")
public class RegistroEntity {

	@Id
	@Column(name = "co_seq_registro")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ds_registro", nullable = false)
	private String descricaoRegistro;
	
	@Column(name = "dt_registro", nullable = false)
	private Timestamp dataRegistro;
	
	@Column(name = "st_registro_ativo", length = 1, nullable = false)
	private String registroAtivo;
	
	@OneToMany
	@JoinColumn(name = "processo_id", referencedColumnName = "co_seq_processo")
	private ProcessoEntity processo;
	
	@OneToMany
	@JoinColumn(name = "usuario_id", referencedColumnName = "co_seq_usuario")
	private UsuarioEntity usuario;
}
