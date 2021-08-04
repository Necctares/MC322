package com.unicamp.mc322.lab07.Objetos;

import com.unicamp.mc322.lab07.Core.Posicao;

public class Frog extends Objetos {
	private TipoDeRa tipoRa;
	private String nome;
	private Posicao posAnterior;

	/**
	 * Instancia um objeto Frog
	 * @param icone da r�
	 * @param nome da r�
	 * @param pos Posi��o da r�
	 * @param tipo da r�
	 */
	public Frog(String icone, String nome, Posicao pos, TipoDeRa tipo) {
		super(icone, TipoDeObjetos.Frog, pos);
		this.tipoRa = tipo;
		this.nome = nome;
		this.posAnterior = new Posicao(pos.retornarX(),pos.retornarY());
	}

	/**
	 * 
	 * @return String contendo o nome da r�
	 */
	public String retornarNome() {
		return nome;
	}

	/**
	 * 
	 * @return Posicao anterior da r�
	 */
	public Posicao retornarPosicaoAnterior() {
		return posAnterior;
	}
	
	/**
	 * Define a intera��o da r� com outros objetos.
	 * Retorna um valor x double: x == 0 se n�o h� intera��o, x == -1 se interage com um obstaculo (Fim de jogo).
	 */
	@Override
	public double interagirComObjeto(Objetos obj) {
		if (obj == null) {
			return 0;
		} else if (obj.retornarTipo().equals(TipoDeObjetos.Obstacle)) {
			return -1;
		} else if (obj.retornarTipo().equals(TipoDeObjetos.Frog)) {
			this.pos.alterarPosicao(posAnterior);
			return 0;
		} else {
			return 0;
		}
	}

	/**
	 * 
	 * @return TipoDeRa da r�
	 */
	TipoDeRa retornarTipoDeRa() {
		return tipoRa;
	}

	/**
	 * Checa se a posi��o da r� � valida no mapa, em um limite (0,0) at� (limiteX, limiteY).
	 * @param limiteX limite de x
	 * @param limiteY limite de y
	 * @return boolean: true se a posi��o � valida, false caso contrario.
	 */
	public boolean checarPosicaoValida(int limiteX, int limiteY) {
		return this.pos.checarPosicaoValida(limiteX, limiteY);
	}

	/**
	 * Calcula a nova posi��o da r�
	 * @param direcao Comando da dire��o em que a r� ir� se mover.
	 */
	public void calcularPosicao(String direcao) {
		this.posAnterior.alterarPosicao(pos);
		if (tipoRa.equals(TipoDeRa.venenosa)) {
			int randDirecao = (int) Math.random() * 4;
			int numeroDeCasas = ((int) Math.random() * 4) + 1;
			switch (randDirecao) {
			case 0:
				pos.alterarPosicao(0, numeroDeCasas);
				break;
			case 1:
				pos.alterarPosicao(0, -numeroDeCasas);
				break;
			case 2:
				pos.alterarPosicao(numeroDeCasas, 0);
				break;
			case 3:
				pos.alterarPosicao(-numeroDeCasas, 0);
				break;
			default:
				System.out.printf("Erro: Direcao nao especificada.\n");
				break;
			}
		} else {
			switch (direcao) {
			case "w":
				if (tipoRa.equals(TipoDeRa.verde)) {
					pos.alterarPosicao(0, -1);
				} else {
					int acrescimo = ((int) Math.random() * 3) + 1;
					pos.alterarPosicao(0, -acrescimo);
				}
				break;
			case "s":
				if (tipoRa.equals(TipoDeRa.verde)) {
					pos.alterarPosicao(0, 1);
				} else {
					int acrescimo = ((int) Math.random() * 3) + 1;
					pos.alterarPosicao(0, acrescimo);
				}
				break;
			case "a":
				if (tipoRa.equals(TipoDeRa.verde)) {
					pos.alterarPosicao(-1, 0);
				} else {
					int acrescimo = ((int) Math.random() * 3) + 1;
					pos.alterarPosicao(-acrescimo, 0);
				}
				break;
			case "d":
				if (tipoRa.equals(TipoDeRa.verde)) {
					pos.alterarPosicao(1, 0);
				} else {
					int acrescimo = ((int) Math.random() * 3) + 1;
					pos.alterarPosicao(acrescimo, 0);
				}
				break;
			default:
				System.out.printf("Erro: Direcao nao especificada.\n");
				break;
			}
		}
		return;
	}
}
