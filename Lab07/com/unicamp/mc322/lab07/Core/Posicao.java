package com.unicamp.mc322.lab07.Core;

/**
 * 
 * Classe para armazenar uma posição (X,Y).
 *
 */
public class Posicao {
	private int posX;
	private int posY;

	/**
	 * Inicializa um novo objeto posição
	 * 
	 * @param x coordenada x
	 * @param y coordenada y
	 */
	public Posicao(int x, int y) {
		posX = x;
		posY = y;
	}

	/**
	 * Altera a posição atual.
	 * 
	 * @param pos Nova posição
	 */
	public void alterarPosicao(Posicao pos) {
		posX = pos.posX;
		posY = pos.posY;
		return;
	}

	/**
	 * Incrementa a posição atual com os valores passados
	 * 
	 * @param incrementoX Incremento da coordenada x
	 * @param incrementoY Incremento da coordenada y
	 */
	public void alterarPosicao(int incrementoX, int incrementoY) {
		posX += incrementoX;
		posY += incrementoY;
		return;
	}

	/**
	 * Calcula a distancia entre duas posições
	 * 
	 * @param pos Segundo ponto para calcular a distancia. (Distancia de Manhattan)
	 * @return Um inteiro com valor da distancia.
	 */
	public int calcularDistancia(Posicao pos) {
		return Math.abs(this.posX - pos.posX) + Math.abs(this.posY - pos.posY);
	}

	/**
	 * Checa se a posicao atual é valida. (Se está dentro do "quadrado" (0,0) até
	 * (limiteX, limiteY))
	 * 
	 * @param limiteX limite de X
	 * @param limiteY limite de Y
	 * @return boolean: true se a posição é valida, false caso contrario.
	 */
	public boolean checarPosicaoValida(int limiteX, int limiteY) {
		boolean tudoCerto = true;
		if (posX < 0 || posX >= limiteX || posY < 0 || posY >= limiteY) {
			tudoCerto = false;
		}
		return tudoCerto;
	}

	/**
	 * 
	 * @return o valor da posicao x
	 */
	public int retornarX() {
		return posX;
	}

	/**
	 * 
	 * @return o valor da posicao y
	 */
	public int retornarY() {
		return posY;
	}
}
