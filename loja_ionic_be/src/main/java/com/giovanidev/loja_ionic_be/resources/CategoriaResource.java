package com.giovanidev.loja_ionic_be.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giovanidev.loja_ionic_be.domain.Categoria;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaResource {

	@GetMapping
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1, "Informatica");
		Categoria cat2 = new Categoria(2,"Escritório");
		
		List<Categoria> lista = new ArrayList<>();
		lista.addAll(Arrays.asList(cat1,cat2));
		return lista;
		
	}
	
}
