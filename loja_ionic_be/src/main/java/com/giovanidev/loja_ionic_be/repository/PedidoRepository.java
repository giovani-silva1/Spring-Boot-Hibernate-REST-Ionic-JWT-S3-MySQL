package com.giovanidev.loja_ionic_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giovanidev.loja_ionic_be.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer>{
	
	
}

	