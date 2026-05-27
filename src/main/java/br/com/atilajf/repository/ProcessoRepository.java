package br.com.atilajf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.atilajf.entity.ProcessoEntity;

public interface ProcessoRepository extends JpaRepository<ProcessoEntity, Long> {

}
