package br.com.atilajf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.atilajf.entity.RegistroEntity;

public interface RegistroRepository extends JpaRepository<RegistroEntity, Long> {

}
