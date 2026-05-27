package br.com.atilajf.processos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import br.com.atilajf.processos.entity.PessoaFisicaEntity;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaEntity, Long> {

}
