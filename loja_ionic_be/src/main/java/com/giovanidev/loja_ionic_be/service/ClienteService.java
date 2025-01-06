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
import com.giovanidev.loja_ionic_be.domain.dto.ClienteDTO;
import com.giovanidev.loja_ionic_be.repository.ClienteRepository;
import com.giovanidev.loja_ionic_be.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);
		return clienteEncontrado.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente update(Cliente cliente) {
		Cliente clienteEncontrado = find(cliente.getId());
		updateData(clienteEncontrado,cliente);
		
		return clienteRepository.save(clienteEncontrado);
	}
	
	public Cliente insert(ClienteDTO clienteDto) {
		Cliente cliente = fromDto(clienteDto);
		return clienteRepository.save(cliente);
	}

	private void updateData(Cliente clienteEncontrado, Cliente cliente) {
		clienteEncontrado.setNome(cliente.getNome());
		clienteEncontrado.setEmail(cliente.getEmail());
		
	}

	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possivel deletar um cliente com pedidos");
		}

	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(),clienteDTO.getEmail(),null,null);
	}
	
	

}
