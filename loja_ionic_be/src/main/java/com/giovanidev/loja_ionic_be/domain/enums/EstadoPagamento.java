package com.giovanidev.loja_ionic_be.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "PENDENTE"), QUITADO(2, "QUITADO"), CANCELADO(3,"CANCELADO");

	private int id;
	private String tipoPagamento;

	private EstadoPagamento(int id, String tipoPagamento) {
		this.id = id;
		this.tipoPagamento = tipoPagamento;
	}

	public int getId() {
		return id;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public static EstadoPagamento toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (EstadoPagamento estadoPagamentoEncontrado : EstadoPagamento.values()) {
			if (codigo.equals(estadoPagamentoEncontrado.getId())) {
				return estadoPagamentoEncontrado;
			}
		}
		throw new IllegalArgumentException();
	}
}
