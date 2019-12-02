package br.com.erp.acme.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erp.acme.domain.Funcionario;
import br.com.erp.acme.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository repo;
	
	public Funcionario buscar(Integer id) {
		Optional<Funcionario> obj = repo.findById(id);
		return obj.orElse(null);
	}

}
