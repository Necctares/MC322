package com.unicamp.mc322.lab06;

import java.util.List;
import java.util.ArrayList;

public class Playlist {
	private String nome;
	private double tamanhoTotal;
	private List<Midia> playlist;
	private boolean abaixoDos60s;
	private int posicaoDaPlaylist;

	Playlist(String nome) {
		this.nome = nome;
		tamanhoTotal = 0;
		abaixoDos60s = true;
		posicaoDaPlaylist = 0;
		playlist = new ArrayList<Midia>();
	}

	private void checarAbaixoDos60s() {
		for (Midia midiaAtual : playlist) {
			if (midiaAtual.retornarDuracao() >= 60) {
				abaixoDos60s = false;
				return;
			}
		}
		abaixoDos60s = true;
		return;
	}

	boolean estaAbaixoDos60s() {
		return abaixoDos60s;
	}

	boolean nomeIgual(String nome) {
		return this.nome.equalsIgnoreCase(nome);
	}

	double retornarTamanho() {
		return tamanhoTotal;
	}

	String retornarNome() {
		return nome;
	}

	void adicionarMidia(Midia midia, User usuario) {
		if (midia.retornarDuracao() < 60 && abaixoDos60s) {
			playlist.add(midia);
			tamanhoTotal += midia.retornarTamanho();
		} else {
			if (usuario.checarAssinatura()
					&& (usuario.verificarTamanhoArmazenamento() + midia.retornarTamanho() <= 900)) {
				playlist.add(midia);
				tamanhoTotal += midia.retornarTamanho();
				abaixoDos60s = false;
			} else if (!usuario.checarAssinatura()
					&& (usuario.verificarTamanhoArmazenamento() + midia.retornarTamanho() <= 200)) {
				playlist.add(midia);
				tamanhoTotal += midia.retornarTamanho();
				abaixoDos60s = false;
			} else {
				System.out.printf(
						"Erro: Nao foi possivel adicionar midia pois o arquivo excedeu o limite maximo de armazenamento.\n");
			}
		}
		return;
	}

	void removerMidia(Midia midia) {
		if (playlist.remove(midia)) {
			checarAbaixoDos60s();
			tamanhoTotal -= midia.retornarTamanho();
		} else {
			System.out.printf("Erro: Midia nao encontrada.\n");
		}
		return;
	}

	Midia acharMenorDuracao() {
		if (!playlist.isEmpty()) {
			Midia menorDuracao = playlist.get(0);
			for (Midia midiaAtual : playlist) {
				if (menorDuracao.retornarDuracao() > midiaAtual.retornarDuracao()) {
					menorDuracao = midiaAtual;
				}
			}
			return menorDuracao;
		} else {
			return null;
		}
	}

	Midia acharMaiorDuracao() {
		if (!playlist.isEmpty()) {
			Midia maiorDuracao = playlist.get(0);
			for (Midia midiaAtual : playlist) {
				if (maiorDuracao.retornarDuracao() < midiaAtual.retornarDuracao()) {
					maiorDuracao = midiaAtual;
				}
			}
			return maiorDuracao;
		} else {
			return null;
		}
	}

	double calcularDuracaoTotal() {
		double duracaoTotal = 0;
		if (playlist.isEmpty()) {
			return duracaoTotal;
		} else {
			for (Midia midiaAtual : playlist) {
				duracaoTotal += midiaAtual.retornarDuracao();
			}
			return duracaoTotal;
		}
	}

	double calcularDuracaoMedia() {
		if (playlist.isEmpty()) {
			return 0;
		} else {
			return calcularDuracaoTotal() / playlist.size();
		}
	}

	public Midia play(boolean shuffle) {
		if (playlist.size() > 1 && shuffle) {
			int elemento = (int) (Math.random() * playlist.size());
			while (elemento == posicaoDaPlaylist) {
				elemento = (int) (Math.random() * playlist.size());
			}
			return playlist.get(elemento);
		} else if (!playlist.isEmpty()) {
			posicaoDaPlaylist += 1;
			if (posicaoDaPlaylist < playlist.size()) {
				return playlist.get(posicaoDaPlaylist);
			} else {
				posicaoDaPlaylist = 0;
				return playlist.get(posicaoDaPlaylist);
			}
		} else {
			return null;
		}
	}

	public void imprimirInfo() {
		System.out.printf(
				"Nome da playlist: %s\nTamanho Total: %.2f MB\nContem midia acima de 60s: %b\nDuracao total: %.2f minutos || Duracao Media: %.2f minutos\n",
				nome, tamanhoTotal, !abaixoDos60s, this.calcularDuracaoTotal() / 60, this.calcularDuracaoMedia() / 60);
		Midia duracao = this.acharMenorDuracao();
		System.out.printf("Midia com menor duracao: %s || Tempo: %.2f minutos.\n", duracao.retornarNome(),
				duracao.retornarDuracao()/60);
		duracao = this.acharMaiorDuracao();
		System.out.printf("Midia com maior duracao: %s || Tempo: %.2f minutos.\n", duracao.retornarNome(),
				duracao.retornarDuracao()/60);
		return;
	}
}
