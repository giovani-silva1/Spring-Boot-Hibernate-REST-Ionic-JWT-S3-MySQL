package com.giovanidev.loja_ionic_be.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giovanidev.loja_ionic_be.domain.Pedido;
import com.giovanidev.loja_ionic_be.repository.PedidoRepository;
import com.giovanidev.loja_ionic_be.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public Pedido buscar(Integer id) {
		Optional<Pedido>pedidoEncontrado = pedidoRepository.findById(id);
		return pedidoEncontrado.orElseThrow(() -> new ObjectNotFoundException( 
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())); 
	}
}
