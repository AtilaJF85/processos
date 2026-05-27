package br.com.atilajf.processos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.atilajf.processos.entity.RegistroEntity;

public interface RegistroRepository extends JpaRepository<RegistroEntity, Long> {

}
