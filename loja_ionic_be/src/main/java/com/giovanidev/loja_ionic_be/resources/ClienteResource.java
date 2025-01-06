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
import com.giovanidev.loja_ionic_be.domain.Cliente;
import com.giovanidev.loja_ionic_be.domain.dto.CategoriaDTO;
import com.giovanidev.loja_ionic_be.domain.dto.ClienteDTO;
import com.giovanidev.loja_ionic_be.domain.dto.ClienteNewDTO;
import com.giovanidev.loja_ionic_be.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(clienteService.find(id));

	}

	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> listAll() {
		List<Cliente> clientesEncontrados = clienteService.findAll();
		List<ClienteDTO> clienteDto = clientesEncontrados.stream().map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(clienteDto);

	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClienteDTO>> listAllPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> categoriasEncontradasPaginadas = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> categoriaDto = categoriasEncontradasPaginadas.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(categoriaDto);

	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteDto) {
		Cliente cliente = clienteService.fromDTO(clienteDto);
		cliente = clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	
	
	
	
	

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@javax.validation.Valid @RequestBody Cliente cliente, @PathVariable Integer id) {
		cliente.setId(id);
		cliente = clienteService.update(cliente);
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
