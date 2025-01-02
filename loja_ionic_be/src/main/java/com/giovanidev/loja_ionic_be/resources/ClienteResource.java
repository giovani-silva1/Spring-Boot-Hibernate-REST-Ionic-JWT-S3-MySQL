package com.giovanidev.loja_ionic_be.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giovanidev.loja_ionic_be.domain.Cliente;
import com.giovanidev.loja_ionic_be.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	

		
		@Autowired
		private ClienteService clienteService;

		@GetMapping(value = "/{id}")
		public ResponseEntity<Cliente> buscarPorId (@PathVariable Integer id)  {
			return ResponseEntity.ok(clienteService.buscar(id));
			
		}

}
