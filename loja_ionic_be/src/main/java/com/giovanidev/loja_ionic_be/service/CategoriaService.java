package com.giovanidev.loja_ionic_be.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovanidev.loja_ionic_be.domain.Categoria;
import com.giovanidev.loja_ionic_be.repository.CategoriaRepository;
import com.giovanidev.loja_ionic_be.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	
	public Categoria find(Integer id) {
		Optional<Categoria>categoriaEncontrada = categoriaRepository.findById(id);
		return categoriaEncontrada.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); 
	}


	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}


	public Categoria update(Categoria categoria) {
		Optional<Categoria>categoriaEncontrada = categoriaRepository.findById(categoria.getId());
		categoriaEncontrada.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + categoria.getId() + ", Tipo: " + Categoria.class.getName())); 
		
		return categoriaRepository.save(categoria);
	}
	
	
	
	
}
