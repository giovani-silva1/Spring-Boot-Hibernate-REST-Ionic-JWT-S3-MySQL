package com.giovanidev.loja_ionic_be.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.giovanidev.loja_ionic_be.domain.Cliente;

import jakarta.validation.constraints.Email;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty
	@Length(min = 5, max = 50 ,message = "Tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email obrigatório")
	private String email;
	
	
	
	public ClienteDTO() {
		
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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	

	
	
	public ClienteDTO (Cliente cliente) {
		id = cliente.getId();
		email = cliente.getEmail();
		nome = cliente.getNome();
	}
	
	
	

}
