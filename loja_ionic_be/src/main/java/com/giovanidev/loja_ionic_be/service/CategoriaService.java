package com.giovanidev.loja_ionic_be.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovanidev.loja_ionic_be.domain.Categoria;
import com.giovanidev.loja_ionic_be.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	
	public Categoria buscar(Integer id) {
		Optional<Categoria>categoriaEncontrada = categoriaRepository.findById(id);
		return categoriaEncontrada.orElseThrow(null);
	}
	
	
}