package br.com.atilajf.processos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.atilajf.processos.entity.ProcessoEntity;

public interface ProcessoRepository extends JpaRepository<ProcessoEntity, Long> {

}
