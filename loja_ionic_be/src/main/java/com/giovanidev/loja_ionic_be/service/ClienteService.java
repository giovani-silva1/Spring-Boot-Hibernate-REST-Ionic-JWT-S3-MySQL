package com.giovanidev.loja_ionic_be.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovanidev.loja_ionic_be.domain.Cliente;
import com.giovanidev.loja_ionic_be.repository.ClienteRepository;
import com.giovanidev.loja_ionic_be.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	
	@Autowired
	private ClienteRepository clienteRepository;

	
	public Cliente buscar(Integer id) {
		Optional<Cliente>clienteEncontrado = clienteRepository.findById(id);
		return clienteEncontrado.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); 
	}
	
	
}
