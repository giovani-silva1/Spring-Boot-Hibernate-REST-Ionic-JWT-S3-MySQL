package com.giovanidev.loja_ionic_be;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giovanidev.loja_ionic_be.domain.Categoria;
import com.giovanidev.loja_ionic_be.domain.Cidade;
import com.giovanidev.loja_ionic_be.domain.Cliente;
import com.giovanidev.loja_ionic_be.domain.Endereco;
import com.giovanidev.loja_ionic_be.domain.Estado;
import com.giovanidev.loja_ionic_be.domain.Produto;
import com.giovanidev.loja_ionic_be.domain.enums.TipoCliente;
import com.giovanidev.loja_ionic_be.repository.CategoriaRepository;
import com.giovanidev.loja_ionic_be.repository.CidadeRepository;
import com.giovanidev.loja_ionic_be.repository.ClienteRepository;
import com.giovanidev.loja_ionic_be.repository.EnderecoRepository;
import com.giovanidev.loja_ionic_be.repository.EstadoRepository;
import com.giovanidev.loja_ionic_be.repository.ProdutoRepository;

@SpringBootApplication
public class LojaIonicBeApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(LojaIonicBeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "INFORMATICA");
		Categoria cat2 = new Categoria(null, "ESCRITÓRIO");
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		Produto p1 = new Produto(null, "Computador",2000.00);
		Produto p2 = new Produto(null, "Impressora",800.00);
		Produto p3 = new Produto(null, "Mouse",2000.00);
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		est1.setCidades(Arrays.asList(c1));
		est2.setCidades(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","Maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		clienteRepository.save(cli1);
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apto203","Jardim","38220834",cli1, c1);
		Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",cli1, c2);
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		cli1.setEnderecos(Arrays.asList(e1,e2));
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		clienteRepository.save(cli1);
		
	}

}
