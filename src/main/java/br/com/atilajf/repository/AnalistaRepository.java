package br.com.atilajf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.atilajf.entity.AnalistaEntity;

public interface AnalistaRepository extends JpaRepository<AnalistaEntity, Long> {
 
}
