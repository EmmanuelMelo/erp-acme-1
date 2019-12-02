package br.com.erp.acme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.erp.acme.domain.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer>{

}
