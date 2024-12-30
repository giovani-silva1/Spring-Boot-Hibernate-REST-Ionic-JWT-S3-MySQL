package com.giovanidev.loja_ionic_be;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.giovanidev.loja_ionic_be.domain.Categoria;
import com.giovanidev.loja_ionic_be.domain.Produto;
import com.giovanidev.loja_ionic_be.repository.CategoriaRepository;
import com.giovanidev.loja_ionic_be.repository.ProdutoRepository;

@SpringBootApplication
public class LojaIonicBeApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
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
	 
		
	}

}
