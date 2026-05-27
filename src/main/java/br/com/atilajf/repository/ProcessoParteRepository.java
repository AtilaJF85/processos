package br.com.atilajf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.atilajf.entity.ProcessoEntity;
import br.com.atilajf.entity.ProcessoParteEntity;

public interface ProcessoParteRepository extends JpaRepository<ProcessoEntity, Long>{

	List<ProcessoParteEntity> findByProcessoId(Long id);
}
