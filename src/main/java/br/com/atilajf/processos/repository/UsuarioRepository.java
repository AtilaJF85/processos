package br.com.atilajf.processos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.atilajf.processos.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

}
