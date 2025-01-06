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
import com.giovanidev.loja_ionic_be.domain.Cliente;
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
		Categoria categoriaEncontrada = find(categoria.getId());
		updateData(categoriaEncontrada, categoria);

		return categoriaRepository.save(categoriaEncontrada);
	}

	private void updateData(Categoria categoriaEncontrada, Categoria categoria) {
		categoriaEncontrada.setId(categoria.getId());
		categoriaEncontrada.setNome(categoria.getNome());
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
