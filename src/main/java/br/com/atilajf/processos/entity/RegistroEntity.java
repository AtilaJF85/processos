package br.com.atilajf.processos.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
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
	private LocalDateTime dataRegistro;
	
	@Column(name = "st_registro_ativo", length = 1, nullable = false)
	private String registroAtivo;
	
	@ManyToOne
	@JoinColumn(name = "co_seq_processo", referencedColumnName = "co_seq_processo")
	private ProcessoEntity processo;
	
	@ManyToOne
	@JoinColumn(name = "co_seq_usuario", referencedColumnName = "co_seq_usuario")
	private UsuarioEntity usuario;
}
