package com.giovanidev.loja_ionic_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giovanidev.loja_ionic_be.domain.ItemPedido;
import com.giovanidev.loja_ionic_be.domain.ItemPedidoPK;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido,ItemPedidoPK>{
	
	
}

	