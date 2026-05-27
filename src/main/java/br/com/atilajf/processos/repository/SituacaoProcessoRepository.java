package br.com.atilajf.processos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.atilajf.processos.entity.SituacaoProcessoEntity;

public interface SituacaoProcessoRepository extends JpaRepository<SituacaoProcessoEntity, Long>{

}
