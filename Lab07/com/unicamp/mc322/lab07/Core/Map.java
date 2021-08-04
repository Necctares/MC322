package com.unicamp.mc322.lab07.Core;

import com.unicamp.mc322.lab07.Objetos.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe que controla o mapa do jogo.
 *
 */
public class Map {
	private int largura;
	private int altura;
	private int maxFoods;
	private int maxPredadores;
	private Objetos mapa[][];
	private List<Predador> predadores;
	private List<Frog> ras;

	/**
	 * Inicializa um novo mapa.
	 * 
	 * @param largura             do mapa
	 * @param altura              do mapa
	 * @param numeroMaxFoods      Numero maximo permitido para adicionar comidas.
	 * @param numeroMaxPredadores Numero maximo permitido para adicionar predadores.
	 */
	Map(int largura, int altura, int numeroMaxFoods, int numeroMaxPredadores) {
		this.largura = largura;
		this.altura = altura;
		this.maxFoods = numeroMaxFoods;
		this.maxPredadores = numeroMaxPredadores;
		this.mapa = new Objetos[altura][largura];
		predadores = new ArrayList<Predador>();
		ras = new ArrayList<Frog>();
	}

	/**
	 * 
	 * @return (int) altura do mapa
	 */
	int retornarAltura() {
		return altura;
	}

	/**
	 * 
	 * @return (int) largura do mapa
	 */
	int retornarLargura() {
		return largura;
	}

	/**
	 * Adiciona objetos ao mapa.
	 * 
	 * @param obj a ser adicionado.
	 * @return boolean: true se conseguiu adicionar objeto, false caso contrario.
	 */
	boolean adicionarObjetos(Objetos obj) {
		boolean adicionou = false;
		if (mapa[obj.retornarPosicao().retornarY()][obj.retornarPosicao().retornarX()] == null) {
			adicionou = true;
			if (obj.retornarTipo().equals(TipoDeObjetos.Food) && maxFoods > 0) {
				Posicao posicao = obj.retornarPosicao();
				mapa[posicao.retornarY()][posicao.retornarX()] = obj;
				maxFoods -= 1;
			} else if (obj.retornarTipo().equals(TipoDeObjetos.Obstacle)) {
				if ((obj instanceof Predador) && maxPredadores > 0) {
					maxPredadores -= 1;
					Predador predador = (Predador) obj;
					Posicao posicao = predador.retornarPosicao();
					mapa[posicao.retornarY()][posicao.retornarX()] = predador;
					predadores.add(predador);
				} else {
					Posicao posicao = obj.retornarPosicao();
					mapa[posicao.retornarY()][posicao.retornarX()] = obj;
				}
			} else if (obj.retornarTipo().equals(TipoDeObjetos.Frog)) {
				Frog ra = (Frog) obj;
				Posicao posicao = ra.retornarPosicao();
				mapa[posicao.retornarY()][posicao.retornarX()] = ra;
				ras.add(ra);
			}
		}
		return adicionou;
	}

	/**
	 * Atualiza movimento dos objetos moveis do mapa.
	 * 
	 * @return um valor double x: Caso x >= 0 => Pontuacao da rodada; Caso x < 0 =>
	 *         Jogo encerrou.
	 */
	double atualizarMovimentos() {
		String comando = null;
		double pontuacaoAcumulada = 0;
		//Atualiza o movimento de todas as rãs.
		for (Frog raAtual : ras) {
			System.out.printf("Ra %s, ", raAtual.retornarNome());
			comando = KeyboardInput.lerTeclado();
			if (comando.compareToIgnoreCase("sair") == 0) {
				return -1;
			}
			raAtual.calcularPosicao(comando);
			if (raAtual.checarPosicaoValida(largura, altura)) {
				Objetos objeto = mapa[raAtual.retornarPosicao().retornarY()][raAtual.retornarPosicao().retornarX()];
				if (objeto != null) {
					double resultado = objeto.interagirComObjeto(raAtual);
					if (resultado < 0) {
						return -1;
					} else {
						if(resultado > 0) {
							this.maxFoods += 1;
						}
						pontuacaoAcumulada += resultado;
					}
				}
				this.mapa[raAtual.retornarPosicao().retornarY()][raAtual.retornarPosicao().retornarX()] = raAtual;
				this.mapa[raAtual.retornarPosicaoAnterior().retornarY()][raAtual.retornarPosicaoAnterior()
						.retornarX()] = null;
			} else {
				return -1;
			}
		}
		//Atualiza o movimento de todos os predadores.
		List<Predador> predadoresRemovidos = new ArrayList<Predador>();
		for (Predador predAtual : predadores) {
			predAtual.calcularPosicao();
			if (predAtual.checarPosicaoValida(largura, altura)) {
				Objetos objeto = mapa[predAtual.retornarPosicao().retornarY()][predAtual.retornarPosicao().retornarX()];
				double resultado = predAtual.interagirComObjeto(objeto);
				mapa[predAtual.retornarPosicao().retornarY()][predAtual.retornarPosicao().retornarX()] = predAtual;
				if (predAtual.retornarPosicaoCauda() != null) {
					mapa[predAtual.retornarPosicaoCauda().retornarY()][predAtual.retornarPosicaoCauda()
							.retornarX()] = mapa[predAtual.retornarPosicaoAnterior().retornarY()][predAtual
									.retornarPosicaoAnterior().retornarX()];
				}
				if (resultado != 2) {
					mapa[predAtual.retornarPosicaoAnterior().retornarY()][predAtual.retornarPosicaoAnterior()
							.retornarX()] = null;
				}
				if (resultado < 0) {
					return -1;
				} else if (resultado == 1) {
					this.maxFoods += 1;
				}
			} else {
				if (predAtual.retornarPosicaoCauda() != null) {
					mapa[predAtual.retornarPosicaoCauda().retornarY()][predAtual.retornarPosicaoCauda()
							.retornarX()] = null;
				}
				mapa[predAtual.retornarPosicaoAnterior().retornarY()][predAtual.retornarPosicaoAnterior()
						.retornarX()] = null;
				predadoresRemovidos.add(predAtual);
				maxPredadores += 1;
			}
		}
		//Remove todos os predadores que devem ser removidos do jogo.
		for (Predador removidoAtual : predadoresRemovidos) {
			predadores.remove(removidoAtual);
		}
		return pontuacaoAcumulada;
	}

	/**
	 * Função para imprimir a situação atual do mapa.
	 */
	void imprimirMapa() {
		for (int i = 0; i < this.altura; i++) {
			for (int j = 0; j < this.largura; j++) {
				if (mapa[i][j] != null) {
					System.out.printf("%s\t", mapa[i][j].retornarIcone());
				} else {
					System.out.printf("--\t");
				}
			}
			System.out.println();
		}
	}
}
