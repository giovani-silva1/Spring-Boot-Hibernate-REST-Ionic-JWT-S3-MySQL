package com.giovanidev.loja_ionic_be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.giovanidev.loja_ionic_be.domain.Categoria;
import com.giovanidev.loja_ionic_be.domain.dto.CategoriaDTO;
import com.giovanidev.loja_ionic_be.repository.CategoriaRepository;
import com.giovanidev.loja_ionic_be.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria find(Integer id) {
		Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(id);
		return categoriaEncontrada.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(CategoriaDTO categoriaDto) {
		Categoria categoria = fromDto(categoriaDto);
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(categoria.getId());
		categoriaEncontrada.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + categoria.getId() + ", Tipo: " + Categoria.class.getName()));

		return categoriaRepository.save(categoria);
	}

	public void delete(Integer id) {
		find(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possivel deletar uma categoria com produtos associados");
		}

	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	
	public Categoria fromDto(CategoriaDTO categoriaDto) {
		return new Categoria(categoriaDto.getId(), categoriaDto.getNome());
	}

}
