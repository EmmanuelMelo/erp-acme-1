package br.com.erp.acme.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.erp.acme.DTO.DepartamentoDTO;
import br.com.erp.acme.domain.Departamento;
import br.com.erp.acme.repositories.DepartamentoRepository;
import br.com.erp.acme.services.exception.DataIntegrityException;
import br.com.erp.acme.services.exception.ObjectNotFoundException;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository repo;
	
	public Departamento buscar(Integer id) {
		Optional<Departamento> obj = repo.findById(id);
		return obj.orElse(null);
	}

	public Departamento find(Integer id) {
		Optional<Departamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Departamento.class.getName()));
	}
	
	public Departamento insert(Departamento obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Departamento update(Departamento obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Indisponível exclusão de categoria que contenha produtos!");
		}
	}
		
	public List<Departamento> findAll(){
		return repo.findAll();
	}
	
	public Page<Departamento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Departamento fromDTO(DepartamentoDTO objDto) {
		return new Departamento(objDto.getId(), objDto.getNome());
	}
	
}
