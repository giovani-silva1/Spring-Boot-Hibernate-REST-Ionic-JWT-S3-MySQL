package com.giovanidev.loja_ionic_be.domain.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.giovanidev.loja_ionic_be.domain.Categoria;

public class CategoriaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@org.hibernate.validator.constraints.NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5,max = 80, message = "O Tamanho deve ser no minimo 5 caracteres")
	private String nome;

	public CategoriaDTO() {

	}
	
	
	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
