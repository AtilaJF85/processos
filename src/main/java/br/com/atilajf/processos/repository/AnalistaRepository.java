package br.com.atilajf.processos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.atilajf.processos.entity.AnalistaEntity;

public interface AnalistaRepository extends JpaRepository<AnalistaEntity, Long> {
 
}
