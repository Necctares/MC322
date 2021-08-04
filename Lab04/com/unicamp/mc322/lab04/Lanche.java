package com.unicamp.mc322.lab04;

public class Lanche {
	private String nome;
	private double preco;
	private String id;
	private static final int tamanhoDoId = 5;

	/**
	 * Cria um novo lanche.
	 * 
	 * @param id    do lanche. Precisa ter o tamanho correto especificado em
	 *              {@link tamanhoDoId} (Valor atual: {@value #tamanhoDoId}).
	 * @param nome  do lanche
	 * @param preco do lanche
	 */
	Lanche(String id, String nome, double preco) {
		if (id.length() == tamanhoDoId) {
			this.nome = nome;
			this.id = id;
			this.preco = preco;
		} else {
			System.out.printf("Erro: O id do lanche tem que ser 5 caracteres.\n");
		}
	}

	/**
	 * 
	 * @return Retorna o preco do lanche. (double)
	 */
	double getPreco() {
		return preco;
	}

	/**
	 * 
	 * @return Retorna o id do lanche. (String)
	 */
	String getId() {
		return id;
	}

	/**
	 * Imprime o id e o nome do lanche.
	 */
	void imprimirInfo() {
		System.out.printf("[%s] %s", id, nome);
	}

	/**
	 * 
	 * @param lanche que será comparado.
	 * @return true se os lanches forem iguais (mesmo id), false caso contrario.
	 */
	boolean compararId(Lanche lanche) {
		boolean ehIgual = false;
		if (this.id == lanche.id) {
			ehIgual = true;
		}
		return ehIgual;
	}
}
