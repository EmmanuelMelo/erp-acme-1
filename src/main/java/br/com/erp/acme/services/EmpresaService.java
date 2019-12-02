package br.com.erp.acme.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erp.acme.domain.Empresa;
import br.com.erp.acme.repositories.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository repo;
	
	public Empresa buscar(Integer id) {
		Optional<Empresa> obj = repo.findById(id);
		return obj.orElse(null);
	}

}
