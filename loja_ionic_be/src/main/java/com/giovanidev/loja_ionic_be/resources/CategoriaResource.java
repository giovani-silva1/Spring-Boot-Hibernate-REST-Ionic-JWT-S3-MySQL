package com.giovanidev.loja_ionic_be.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.giovanidev.loja_ionic_be.domain.Categoria;
import com.giovanidev.loja_ionic_be.domain.dto.CategoriaDTO;
import com.giovanidev.loja_ionic_be.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(categoriaService.find(id));

	}
	
	@GetMapping()
	public ResponseEntity<List<CategoriaDTO>> listAll() {
		List<Categoria> categoriasEncontradas = categoriaService.findAll();
		List<CategoriaDTO> categoriaDto = categoriasEncontradas.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(categoriaDto);

	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> listAllPage(
			@RequestParam(value = "page" ,defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage" ,defaultValue = "24")  Integer linesPerPage,
			@RequestParam(value = "orderBy" ,defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction" ,defaultValue = "ASC") String direction) {
		Page<Categoria> categoriasEncontradasPaginadas = categoriaService.findPage(page,linesPerPage,orderBy,direction);
		Page<CategoriaDTO> categoriaDto = categoriasEncontradasPaginadas.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(categoriaDto);

	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDto) {
		Categoria categoria = categoriaService.insert(categoriaDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Integer id) {
		categoria.setId(id);
		categoria = categoriaService.update(categoria);
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
