package com.unicamp.mc322.lab04;

public class User {
	private String nome;
	private String cpf;
	private Posicao endereco;
	private boolean novoCliente;
	private int numeroCompras;
	private static final int numeroMaxCompras = 10;

	/**
	 * Cria um novo usuario.
	 * 
	 * @param nome     do usuario
	 * @param cpf      do usuario
	 * @param endereco do usuario (Posicao)
	 */
	User(String nome, String cpf, Posicao endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		novoCliente = true;
		numeroCompras = 0;
	}

	/**
	 * 
	 * @return Retorna a String contendo o cpf do usuario.
	 */
	String getCpf() {
		return cpf;
	}

	/**
	 * Imprime o nome e o cpf do Usuario.
	 */
	void imprimirInfo() {
		System.out.printf("Usuario: %s (%s)\n", nome, cpf);
		return;
	}

	/**
	 * Adiciona uma compra na "fidelidade" do usuario.
	 */
	void adicionarCompra() {
		numeroCompras += 1;
		novoCliente = false;
		return;
	}

	/**
	 * Reseta o numero de compras da "fidelidade" do usuario para 0.
	 */
	void resetarCompras() {
		numeroCompras = 0;
		return;
	}

	/**
	 * Checa se o usuario é um cliente novo (Que nunca fez compra).
	 * 
	 * @return true caso o cliente nunca tenha feito compras, false se o cliente já
	 *         fez alguma compra.
	 */
	boolean checarNovoCliente() {
		return novoCliente;
	}

	/**
	 * Checa se o usuario atingiu a meta de "fidelidade" de compras.
	 * 
	 * @return true se a fidelidade foi atingida, false caso contrario.<br>
	 * numeroDeCompras == numeroDeComprasNecessarias - 1
	 */
	boolean checarSeAtingiuMetaDeCompras() {
		boolean atingiu = false;
		if (numeroCompras == numeroMaxCompras - 1) {
			atingiu = true;
		}
		return atingiu;
	}
}
