package com.unicamp.mc322.lab07.Core;

/**
 * 
 * Classe para armazenar uma posi��o (X,Y).
 *
 */
public class Posicao {
	private int posX;
	private int posY;

	/**
	 * Inicializa um novo objeto posi��o
	 * 
	 * @param x coordenada x
	 * @param y coordenada y
	 */
	public Posicao(int x, int y) {
		posX = x;
		posY = y;
	}

	/**
	 * Altera a posi��o atual.
	 * 
	 * @param pos Nova posi��o
	 */
	public void alterarPosicao(Posicao pos) {
		posX = pos.posX;
		posY = pos.posY;
		return;
	}

	/**
	 * Incrementa a posi��o atual com os valores passados
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
	 * Calcula a distancia entre duas posi��es
	 * 
	 * @param pos Segundo ponto para calcular a distancia. (Distancia de Manhattan)
	 * @return Um inteiro com valor da distancia.
	 */
	public int calcularDistancia(Posicao pos) {
		return Math.abs(this.posX - pos.posX) + Math.abs(this.posY - pos.posY);
	}

	/**
	 * Checa se a posicao atual � valida. (Se est� dentro do "quadrado" (0,0) at�
	 * (limiteX, limiteY))
	 * 
	 * @param limiteX limite de X
	 * @param limiteY limite de Y
	 * @return boolean: true se a posi��o � valida, false caso contrario.
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
