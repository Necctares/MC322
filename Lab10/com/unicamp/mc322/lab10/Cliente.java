package com.unicamp.mc322.lab10;

public class Cliente {
	private String nome;
	private Posicao endereco;
	private String cpf;
	private boolean novoCliente;
	
	/**
	 * Cria um novo Cliente
	 * @param nome do cliente
	 * @param cpf do cliente
	 * @param endereco (Posicao) do cliente
	 */
	Cliente(String nome, String cpf, Posicao endereco) {
		novoCliente = true;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
	}

	/**
	 * Imprime o nome e o cpf do Usuario.
	 */
	public void imprimirInfo() {
		System.out.printf("Usuario: %s (%s)\n", nome, cpf);
		return;
	}

	/**
	 * Checa se o Cliente já realizou alguma compra
	 * @return
	 */
	public boolean checarNovoCliente() {
		return novoCliente;
	}

	/**
	 * Retorna a Posicao do Cliente (endereco)
	 * @return Posicao
	 */
	public Posicao retornarPosicao() {
		return endereco;
	}

	/**
	 * Modifica o Status de Primeira Compra do Cliente
	 */
	public void alterarPrimeiraCompra() {
		novoCliente = false;
		return;
	}
}
