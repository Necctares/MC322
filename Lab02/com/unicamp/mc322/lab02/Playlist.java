package com.unicamp.mc322.lab02;

import java.util.*;

public class Playlist {
	private String nome;
	private String genero;
	private List<Song> musicas;
	private int playlistPos;

	public Playlist(String nomeDaPlaylist, String generoDaPlaylist) {
		nome = nomeDaPlaylist;
		genero = generoDaPlaylist;
		playlistPos = 0;
		musicas = new ArrayList<Song>();
	}

	public void addSong(Song song) {
		musicas.add(song);
		musicas.sort(Comparator.comparing(Song::retornarNome));
		return;
	}

	public void addSong(Song song, boolean assinante) {
		musicas.add(song);
		musicas.sort(Comparator.comparing(Song::retornarNome));
		return;
	}

	public void removerMusica(String song) {
		Song aux;
		for (int i = 0; i < musicas.size(); i++) {
			aux = musicas.get(i);
			if (song.equalsIgnoreCase(aux.retornarNome())) {
				musicas.remove(i);
				i -= 1;
			}
		}
		return;
	}

	public Song retornarMenorDuracao() {
		Song aux = musicas.get(0);
		Song cmp;
		for (int i = 1; i < musicas.size(); i++) {
			cmp = musicas.get(i);
			if (aux.retornarDuracao() > cmp.retornarDuracao()) {
				aux = cmp;
			}
		}
		return aux;
	}

	public Song retornarMaiorDuracao() {
		Song aux = musicas.get(0);
		Song cmp;
		for (int i = 1; i < musicas.size(); i++) {
			cmp = musicas.get(i);
			if (aux.retornarDuracao() < cmp.retornarDuracao()) {
				aux = cmp;
			}
		}
		return aux;
	}

	public float retornarDuracaoMedia() {
		Song aux;
		float soma = 0;
		for (int i = 0; i < musicas.size(); i++) {
			aux = musicas.get(i);
			soma += aux.retornarDuracao();
		}
		if (musicas.size() != 0) {
			soma /= musicas.size();
		}
		return soma;
	}

	public float retornarDuracaoTotal() {
		Song aux;
		float soma = 0;
		for (int i = 0; i < musicas.size(); i++) {
			aux = musicas.get(i);
			soma += aux.retornarDuracao();
		}
		return soma;
	}

	public Song play() {
		if (playlistPos < musicas.size()) {
			playlistPos += 1;
			return musicas.get(playlistPos - 1);
		} else {
			playlistPos = 0;
			return musicas.get(playlistPos);
		}
	}

	public Song play(boolean shuffle) {
		if (musicas.size() > 1 && shuffle) {
			int elemento = (int) (Math.random() * musicas.size());
			while (elemento == playlistPos-1) {
				elemento = (int) (Math.random() * musicas.size());
			}
			return musicas.get(elemento);
		} else {
			return musicas.get(0);
		}
	}

	public int retornarNumMusicas() {
		return musicas.size();
	}

	public String retornarNome() {
		return nome;
	}

	public void mostrarMusicas() {
		System.out.printf("%s\n\tNumero de Musicas: %d\n\tMusicas:\n", nome, musicas.size());
		Song aux;
		for (int i = 0; i < musicas.size(); i++) {
			aux = musicas.get(i);
			System.out.printf("\t- %s - %s;\n", aux.retornarNome(), aux.retornarArtista());
		}
	}
}
