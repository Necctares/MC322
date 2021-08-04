package com.unicamp.mc322.lab10.Restaurante;

import com.unicamp.mc322.lab10.Pedido.Avaliacao;

public class Lanche {
	private String nome;
	private double preco;
	private String id;
	private static final int tamanhoDoId = 5;
	private Avaliacao avaliacao;

	
	private Lanche(String id, String nome, double preco) {
		this.nome = nome;
		this.id = id;
		this.preco = preco;
		this.avaliacao = new Avaliacao();
	}

	/**
	 * Cria um novo lanche.
	 * 
	 * @param id    do lanche. Precisa ter o tamanho correto especificado em
	 *              {@link tamanhoDoId} (Valor atual: {@value #tamanhoDoId}).
	 * @param nome  do lanche
	 * @param preco do lanche
	 */
	public static Lanche criarLanche(String id, String nome, double preco) {
		if (id.length() == tamanhoDoId) {
			Lanche novoLanche = new Lanche(id, nome, preco);
			return novoLanche;
		} else {
			return null;
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
	public String getId() {
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

	/**
	 * Recebe uma avaliacao
	 * 
	 * @param avaliacao Valor entre 0 e 5 avaliando o item em questão.
	 */
	public void receberAvaliacao(int avaliacao) {
		this.avaliacao.receberAvaliacao(avaliacao);
		return;
	}

	/**
	 * Imprime a avaliação media atual do lanche
	 */
	void imprimirAvaliacao() {
		if (avaliacao.retornarAvaliacao() >= 0) {
			System.out.printf("Lanche: %s\tID: %s\tAvaliacao Media: %.2f\n", nome, id, avaliacao.retornarAvaliacao());
		}else {
			System.out.printf("Lanche: %s\tID: %s\tAvaliacao Media: Sem Avaliacao\n", nome, id, avaliacao.retornarAvaliacao());
		}
		return;
	}
}
