package com.unicamp.mc322.lab06;

import java.util.List;
import java.util.ArrayList;

public class User {
	private String nome;
	private String cpf;
	private String nascimento;
	private String genero;
	private boolean assinante;
	private List<Playlist> playlist;

	User(String nome, String cpf, String genero, String dataNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.genero = genero;
		nascimento = dataNascimento;
		assinante = false;
		playlist = new ArrayList<Playlist>();
	}

	double verificarTamanhoArmazenamento() {
		double contadorTamanho = 0;
		for (Playlist playlistAtual : playlist) {
			if (!playlistAtual.estaAbaixoDos60s()) {
				contadorTamanho += playlistAtual.retornarTamanho();
			}
		}
		return contadorTamanho;
	}

	private void verificarArmazenamento() {
		double contadorTamanho = verificarTamanhoArmazenamento();
		while ((contadorTamanho > 900 && assinante) || (contadorTamanho > 200 && !assinante)) {
			Playlist removido = playlist.remove(playlist.size() - 1);
			contadorTamanho -= removido.retornarTamanho();
			System.out.printf("Playlist (%s) removida pois o limite de armazenamento do usuario foi excedido.\n",
					removido.retornarNome());
		}
		return;
	}

	boolean checarAssinatura() {
		return assinante;
	}

	void modificarPremimum(boolean novoStatus) {
		assinante = novoStatus;
		this.verificarArmazenamento();
		return;
	}

	boolean adicionarPlaylist(Playlist playlist) {
		if (assinante && verificarTamanhoArmazenamento() + playlist.retornarTamanho() <= 900) {
			this.playlist.add(playlist);
			return true;
		} else if (!assinante && verificarTamanhoArmazenamento() + playlist.retornarTamanho() <= 200) {
			this.playlist.add(playlist);
			return true;
		} else {
			return false;
		}
	}

	public Playlist criarPlaylist(String nome) {
		Playlist novaPlaylist = new Playlist(nome);
		playlist.add(novaPlaylist);
		return novaPlaylist;
	}

	public Playlist removerPlayList(Playlist playlist) {
		if (this.playlist.remove(playlist)) {
			return playlist;
		} else {
			System.out.printf("Erro: Playlist nao encontrada.\n");
			return null;
		}
	}

	public boolean transferirPlaylist(User usuarioTransferencia, Playlist playlist) {
		return usuarioTransferencia.adicionarPlaylist(playlist);
	}

	public void imprimirInfo() {
		System.out.printf("Nome: %s || Cpf: %s || Data de nascimento: %s || Genero: %s\n", nome, cpf, nascimento,
				genero);
		System.out.printf("Possui assinatura: %b || Numero de playlists: %d || Armazenamento ocupado: %.2f\n", assinante,
				playlist.size(), this.verificarTamanhoArmazenamento());
		return;
	}
}
