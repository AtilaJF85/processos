package br.com.atilajf.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import br.com.atilajf.entity.PessoaFisicaEntity;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaEntity, Long> {

}
