package br.com.erp.acme.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.erp.acme.DTO.DepartamentoDTO;
import br.com.erp.acme.domain.Departamento;
import br.com.erp.acme.services.DepartamentoService;

@RestController
@RequestMapping(value="/departamentos")
public class DepartamentoResource {
	
	@Autowired
	private DepartamentoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Departamento obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody DepartamentoDTO objDto){
		Departamento obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody DepartamentoDTO objDto, @PathVariable Integer id) {
		Departamento obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Departamento> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<DepartamentoDTO>> findAll() {
		List<Departamento> list = service.findAll();
		List<DepartamentoDTO> listDto = list.stream().map(obj -> new DepartamentoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);	
	}
	
	@RequestMapping(value = "/page", method=RequestMethod.GET)
	public ResponseEntity<Page<DepartamentoDTO>> findPages(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Departamento> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<DepartamentoDTO> listDto = list.map(obj -> new DepartamentoDTO(obj));
		return ResponseEntity.ok().body(listDto);	
	}

}
