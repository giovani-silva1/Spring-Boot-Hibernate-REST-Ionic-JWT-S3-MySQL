package com.giovanidev.loja_ionic_be.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giovanidev.loja_ionic_be.domain.Pedido;
import com.giovanidev.loja_ionic_be.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	

		
		@Autowired
		private PedidoService pedidoService;

		@GetMapping(value = "/{id}")
		public ResponseEntity<Pedido> buscarPorId (@PathVariable Integer id)  {
			return ResponseEntity.ok(pedidoService.buscar(id));
			
		}
		

}
