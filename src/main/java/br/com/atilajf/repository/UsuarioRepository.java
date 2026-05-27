package br.com.atilajf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.atilajf.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

}
