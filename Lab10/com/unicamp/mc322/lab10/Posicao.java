package com.unicamp.mc322.lab10;

public class Posicao {
	private int x;
	private int y;

	/**
	 * Cria um novo vetor Posicao
	 * 
	 * @param x coordenada
	 * @param y coordenada
	 */
	Posicao(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Calcula a distancia entre o ponto atual e um outro
	 * @param p2 segundo ponto
	 * @return Double valor da distancia euclidiana entre os dois pontos
	 */
	public double calcularDistancia(Posicao p2) {
		return Math.sqrt(Math.pow(this.x - p2.x, 2) + Math.pow(this.y - p2.y, 2));
	}
	
	/**
	 * Calcula a distancia euclidiana entre os pontos p1 e p2
	 * @param p1 Posicao 1
	 * @param p2 Posicao 2
	 * @return Double Valor da distancia euclidiana
	 */
	public static double calcularDistancia(Posicao p1, Posicao p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}
}
