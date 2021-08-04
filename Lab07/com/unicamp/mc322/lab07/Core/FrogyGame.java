package com.unicamp.mc322.lab07.Core;

import com.unicamp.mc322.lab07.Objetos.*;

/**
 * 
 * Classe para controlar o fluxo de execução do jogo.
 *
 */
public class FrogyGame {

	private double pontuacao;
	private Map mapa;
	private boolean estaSendoExecutado;
	private int novoEvento;

	public FrogyGame() {
		pontuacao = 0;
		estaSendoExecutado = true;
		novoEvento = 0;
	}

	/**
	 * Sua chamada encerra a execução do jogo.
	 */
	private void encerrarJogo() {
		estaSendoExecutado = false;
		return;
	}

	/**
	 * Altera a pontuacao final do jogador
	 * 
	 * @param acrescimo para a pontuacao (Se negativo, diminui a pontuacao do
	 *                  jogador)
	 */
	private void alterarPontuacao(double acrescimo) {
		pontuacao += acrescimo;
		return;
	}

	/**
	 * Função para inicializar o jogo. Seta todos os parametros iniciais necessarios
	 * e executa o jogo até o encerramento do jogo.
	 */
	public void inicializar() {
		double resultado = 0;
		mapa = new Map(10, 10, 2, 2);

		this.adicionarPedra(new Posicao(7, 2));
		this.adicionarPedra(new Posicao(2, 3));
		this.adicionarPedra(new Posicao(1, 7));
		this.adicionarPedra(new Posicao(4, 8));
		this.adicionarPedra(new Posicao(8, 8));
		this.adicionarPredador(new Posicao(4, 4), new Posicao(5, 5));
		this.adicionarArmadilha(new Posicao(0, 0), new Posicao(1, 0), null);
		this.adicionarComida(new Posicao(3, 1), TipoDeComida.vagalume);
		this.adicionarComida(new Posicao(7, 4), TipoDeComida.grilo);
		this.adicionarRa("Jogador 1", "J1", new Posicao(7, 8), TipoDeRa.verde);

		while (estaSendoExecutado) {
			novoEvento = (novoEvento + 1) % 7;
			mapa.imprimirMapa();
			resultado = mapa.atualizarMovimentos();
			if (resultado < 0) {
				this.encerrarJogo();
			} else {
				this.alterarPontuacao(resultado);
			}
			if (novoEvento == 0) {
				this.executarEvento();
			}
		}
		mapa.imprimirMapa();
		this.finalizarJogo();
		return;
	}

	/**
	 * Finaliza o jogo, retornando a pontuacao final do jogador.
	 */
	private void finalizarJogo() {
		System.out.printf("Ooops... Fim de jogo, voce morreu.\n Pontuacao total: %.2f\n", pontuacao);
		return;
	}

	/**
	 * Função para executar eventos no jogo. Atualmente adiciona um predador e uma
	 * comida em posições aleatorias no mapa cada vez que é chamada, respeitando o
	 * limite maximo de cada objeto no mapa.
	 */
	private void executarEvento() {
		int posX = (int) (Math.random() * (this.mapa.retornarLargura()));
		int posY = (int) (Math.random() * (this.mapa.retornarAltura()));
		switch ((int) (Math.random() * 2)) {
		case 0:
			this.adicionarComida(new Posicao(posX, posY), TipoDeComida.grilo);
			break;
		case 1:
			this.adicionarComida(new Posicao(posX, posY), TipoDeComida.vagalume);
			break;
		default:
			System.out.printf("Erro: caso nao especificado.\n");
			break;
		}
		posX = (int) (Math.random() * (this.mapa.retornarLargura()));
		posY = (int) (Math.random() * (this.mapa.retornarAltura()));
		Predador novoPred = new Predador(new Posicao(posX, posY));
		this.mapa.adicionarObjetos(novoPred);
		return;
	}

	/**
	 * Função para adicionar armadilhas ao jogo.
	 * 
	 * @param pos1 Posicao 1 da armadilha a ser adicionada.
	 * @param pos2 Posicao 2 da armadilha a ser adicionada. (Deixe null caso queira
	 *             apenas uma posição).
	 * @param pos3 Posicao 3 da armadilha a ser adicionada. (Deixe null caso queira
	 *             apenas uma ou duas posições).
	 */
	private void adicionarArmadilha(Posicao pos1, Posicao pos2, Posicao pos3) {
		if (pos2 == null && pos3 == null) {
			Objetos novoObj = new Armadilha(pos1);
			mapa.adicionarObjetos(novoObj);
		} else if (pos2 != null && pos3 == null) {
			if (pos1.calcularDistancia(pos2) < 3) {
				Objetos novoObj = new Armadilha(pos1);
				Objetos novoObj2 = new Armadilha(pos2);
				mapa.adicionarObjetos(novoObj);
				mapa.adicionarObjetos(novoObj2);
			}
		} else {
			if (pos1.calcularDistancia(pos2) < 3 && pos2.calcularDistancia(pos3) < 3
					&& pos3.calcularDistancia(pos1) < 3) {
				Objetos novoObj = new Armadilha(pos1);
				Objetos novoObj2 = new Armadilha(pos2);
				Objetos novoObj3 = new Armadilha(pos3);
				mapa.adicionarObjetos(novoObj);
				mapa.adicionarObjetos(novoObj2);
				mapa.adicionarObjetos(novoObj3);
			}
		}
		return;
	}

	/**
	 * Função para adicionar um Predador ao jogo.
	 * 
	 * @param pos1 Posição para adicionar o predador no mapa.
	 * @param pos2 Posição para adicionar uma "cauda" do predador no mapa, esta
	 *             cauda, irá seguir a movimentação da Posição 1.
	 */
	private void adicionarPredador(Posicao pos1, Posicao pos2) {
		if (pos2 != null) {
			Predador novoPredador = new Predador(pos1);
			Objetos cauda = novoPredador.adicionarCauda(pos2);
			if (mapa.adicionarObjetos(novoPredador) && novoPredador.retornarPosicaoCauda() != null) {
				if (!mapa.adicionarObjetos(cauda)) {
					novoPredador.removerCauda();
				}
			} else {
				novoPredador.removerCauda();
			}
		} else {
			Predador novoPredador = new Predador(pos1);
			mapa.adicionarObjetos(novoPredador);
		}
		return;
	}

	/**
	 * Adiciona uma pedra ao jogo.
	 * 
	 * @param pos1 Posição para adicionar a pedra ao jogo.
	 */
	private void adicionarPedra(Posicao pos1) {
		Objetos novaPedra = new Pedra(pos1);
		mapa.adicionarObjetos(novaPedra);
		return;
	}

	/**
	 * Adiciona uma Rã ao jogo.
	 * 
	 * @param nome  da Rã
	 * @param icone da Rã
	 * @param pos1  Posição em que a rã será adicionada.
	 * @param tipo  da rã a ser adicionada.
	 */
	private void adicionarRa(String nome, String icone, Posicao pos1, TipoDeRa tipo) {
		Frog novaRa = new Frog(icone, nome, pos1, tipo);
		mapa.adicionarObjetos(novaRa);
		return;
	}

	/**
	 * Adiciona uma comida ao jogo.
	 * 
	 * @param pos  Posição em que a comida será adicionada.
	 * @param tipo de comida a ser adicionada.
	 */
	private void adicionarComida(Posicao pos, TipoDeComida tipo) {
		switch (tipo) {
		case grilo: {
			Objetos novaComida = new Grilos(pos);
			mapa.adicionarObjetos(novaComida);
			break;
		}
		default: {
			Objetos novaComida = new Vagalumes(pos);
			mapa.adicionarObjetos(novaComida);
			break;
		}
		}
		return;
	}
}
