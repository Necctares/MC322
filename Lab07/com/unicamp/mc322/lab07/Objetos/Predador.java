package com.unicamp.mc322.lab07.Objetos;

import com.unicamp.mc322.lab07.Core.Posicao;

public class Predador extends Obstacles {
	private Cauda cauda;
	private Posicao posicaoAnterior;

	/**
	 * Instancia um novo Predador
	 * @param pos Posicao do predador.
	 */
	public Predador(Posicao pos) {
		super("$$", pos);
		cauda = null;
		posicaoAnterior = new Posicao(pos.retornarX(), pos.retornarY());
	}

	/**
	 * Adiciona uma cauda ao predador.
	 * @param pos Posi��o da cauda (Distancia de manhattan maxima da cauda para o predador � 1.)
	 * @return Cauda se criada com sucesso, null caso contrario.
	 */
	public Cauda adicionarCauda(Posicao pos) {
		if (this.pos.calcularDistancia(pos) < 3) {
			cauda = new Cauda(pos);
			return cauda;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @return Posicao anterior do predador.
	 */
	public Posicao retornarPosicaoAnterior() {
		return posicaoAnterior;
	}

	/**
	 * 
	 * @return Posicao atual da cauda
	 */
	public Posicao retornarPosicaoCauda() {
		if (cauda == null) {
			return null;
		} else {
			return cauda.pos;
		}
	}

	/**
	 * Remove a cauda do predador.
	 */
	public void removerCauda() {
		cauda = null;
		return;
	}

	/**
	 * Atualiza a posi��o da cauda.
	 */
	private void atualizarCauda() {
		if (cauda == null) {
			posicaoAnterior.alterarPosicao(pos);
			;
		} else {
			posicaoAnterior.alterarPosicao(cauda.pos);
			cauda.pos.alterarPosicao(this.pos);
		}
		return;
	}

	/**
	 * Define a intera��o do Predador com outros objetos.
	 */
	@Override
	public double interagirComObjeto(Objetos obj) {
		if (obj == null) {
			return 0;
		} else if (obj.retornarTipo().equals(TipoDeObjetos.Frog)) {
			return -1;
		} else if (obj.retornarTipo().equals(TipoDeObjetos.Obstacle)) {
			if (cauda != null) {
				this.pos.alterarPosicao(cauda.pos);
				this.cauda.pos.alterarPosicao(posicaoAnterior);
			}else {
				this.pos.alterarPosicao(posicaoAnterior);
			}
			return 2;
		} else if (obj.retornarTipo().equals(TipoDeObjetos.Food)) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Checa se a posi��o do predador no mapa � valida, se est� dentro de um limite entre as posi��es (0,0) at� (limite x, limite y)
	 * @param limiteX limite x
	 * @param limiteY limite y
	 * @return boolean: true se � valida, false caso contrario.
	 */
	public boolean checarPosicaoValida(int limiteX, int limiteY) {
		return this.pos.checarPosicaoValida(limiteX, limiteY);
	}

	/**
	 * Calcula a nova posi��o do predador.
	 */
	public void calcularPosicao() {
		this.atualizarCauda();
		int movimentoX = (int) (Math.random() * 3) - 1;
		int movimentoY = (int) (Math.random() * 3) - 1;
		pos.alterarPosicao(movimentoX, movimentoY);
	}
}

class Cauda extends Obstacles {

	Cauda(Posicao pos) {
		super("$$", pos);
	}

}