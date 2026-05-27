package br.com.atilajf.processos.repository;

import br.com.atilajf.processos.entity.ProcessoParteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessoParteRepository extends JpaRepository<ProcessoParteEntity, Long>{

	List<ProcessoParteEntity> findByProcessoId(Long id);
}
