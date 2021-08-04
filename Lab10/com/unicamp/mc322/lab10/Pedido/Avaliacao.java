package com.unicamp.mc322.lab10.Pedido;

/**
 * Classe para armazenar as avaliacoes
 *
 *
 */
public class Avaliacao {
	private int avaliacao;
	private int numeroAvaliacoes;

	/**
	 * Cria uma nova avaliacao
	 */
	public Avaliacao() {
		avaliacao = 0;
		numeroAvaliacoes = 0;
	}
	
	/**
	 * Recebe uma nova avaliacao
	 * @param avaliacao Valor int entre 0-5 para avaliar o item
	 */
	public void receberAvaliacao(int avaliacao) {
		if (avaliacao <= 5 && avaliacao >= 0) {
			this.avaliacao += avaliacao;
			numeroAvaliacoes += 1;
		}
		return;
	}

	/**
	 * Retorna a avaliação media do item
	 * @return Double Valor da avaliação media atual, caso numero de avaliações seja 0, retorna -1.
	 */
	public double retornarAvaliacao() {
		if (numeroAvaliacoes == 0) {
			return -1;
		} else {
			return (double) (avaliacao / numeroAvaliacoes);
		}
	}
}
