package com.unicamp.mc322.lab13.Pedidos;

import com.unicamp.mc322.lab13.Pessoa;
import java.util.concurrent.ThreadLocalRandom;

public class PedidoInternet implements IOrder {
	private Pessoa cliente;
	private String codigo;
	private int numeroTurnos;

	/**
	 * Gera um novo pedido feito pela Internet
	 * @param cliente responsavel pelo pedido
	 */
	public PedidoInternet(Pessoa cliente) {
		this.cliente = cliente;
		this.codigo = gerarCodigo();
		numeroTurnos = 0;
	}
	
	/**
	 * Gera aleatoriamente um codigo para o pedido
	 * @return String contendo um codigo
	 */
	private String gerarCodigo() {
		StringBuilder codigo = new StringBuilder();
		codigo.append("INT");
		for(int i=0; i<5; i++) {
			codigo.append((char)ThreadLocalRandom.current().nextInt('A','Z'+1));
		}
		codigo.append("#");
		codigo.append(cliente.retornarCPF());
		return codigo.toString();
	}

	@Override
	public void incrementarTurno() {
		numeroTurnos += 1;
		return;
	}

	@Override
	public int obterNumeroTurno() {
		return numeroTurnos;
	}

	@Override
	public String obterCodigo() {
		return codigo;
	}

	@Override
	public void imprimirReduzido() {
		System.out.printf("O cliente %s fez o pedido %s\n", cliente.retornarNome(), codigo);
		return;
	}

	@Override
	public void imprimirCompleto() {
		System.out.printf("O cliente %s, CPF: %s, com idade de %d anos fez o pedido %s\n", cliente.retornarNome(),
				cliente.retornarCPF(), cliente.retornarIdade(), codigo);

	}

	@Override
	public Pessoa obterPessoa() {
		return cliente;
	}

	@Override
	public boolean checarLojaFisica() {
		return false;
	}

}
