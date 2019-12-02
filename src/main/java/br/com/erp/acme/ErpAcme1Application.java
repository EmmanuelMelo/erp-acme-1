package br.com.erp.acme;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.erp.acme.domain.Departamento;
import br.com.erp.acme.domain.Dependente;
import br.com.erp.acme.domain.Empresa;
import br.com.erp.acme.domain.Endereco;
import br.com.erp.acme.domain.Funcionario;
import br.com.erp.acme.repositories.DepartamentoRepository;
import br.com.erp.acme.repositories.DependenteRepository;
import br.com.erp.acme.repositories.EmpresaRepository;
import br.com.erp.acme.repositories.EnderecoRepository;
import br.com.erp.acme.repositories.FuncionarioRepository;


@SpringBootApplication
public class ErpAcme1Application implements CommandLineRunner {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private DependenteRepository dependenteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ErpAcme1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Endereco end1 = new Endereco(null, "rua Antônio Peregrino", 162, 58080000, "João Pessoa", "Centro");
		
		enderecoRepository.saveAll(Arrays.asList(end1));
		
		
		Empresa emp = new Empresa(null, "Tecmais", "06.234.334/0001-75", 32245030, end1);
		
		empresaRepository.saveAll(Arrays.asList(emp));
		
		Departamento dep1 = new Departamento(null, "Recursos humanos");
		Departamento dep2 = new Departamento(null, "Almoxarifado");
		
		Funcionario func1 = new Funcionario(null, "Eweraldo", 01035245723, 998313145, emp, null);
		Funcionario func2 = new Funcionario(null, "Alan", 02035445643, 988709193, emp, null);
		Funcionario func3 = new Funcionario(null, "Elba", 06150646474, 981826767, emp, null);
		
		dep1.getFuncionarios().addAll(Arrays.asList(func3));
		dep2.getFuncionarios().addAll(Arrays.asList(func1, func2));
		
		func1.getDepartamentos().addAll(Arrays.asList(dep2));
		func2.getDepartamentos().addAll(Arrays.asList(dep2));
		func3.getDepartamentos().addAll(Arrays.asList(dep1));
		
		
		departamentoRepository.saveAll(Arrays.asList(dep1, dep2));
		funcionarioRepository.saveAll(Arrays.asList(func1, func2, func3));
		
		Dependente depen1 = new Dependente(null, "Pedro", 06264656375, func1);
		Dependente depen2 = new Dependente(null, "Ana", 06264656377, func1);
		func1.getDependentes().addAll(Arrays.asList(depen1, depen2));
		
		dependenteRepository.saveAll(Arrays.asList(depen1, depen2));
	}

}
