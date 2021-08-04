package com.unicamp.mc322.lab06;

import java.util.List;
import java.util.ArrayList;

public class Album extends Midia {
	List<Musica> musicasDoAlbum;

	Album(String nome) {
		super(nome, "N/A", 0, 0);
		musicasDoAlbum = new ArrayList<Musica>();
	}

	private void checarArtistaDoAlbum() {
		if (!musicasDoAlbum.isEmpty()) {
			String nomeArtista = musicasDoAlbum.get(0).retornarAutor();
			for (Musica musicaAtual : musicasDoAlbum) {
				if (!nomeArtista.equalsIgnoreCase(musicaAtual.retornarAutor())) {
					this.modificarAutor("Varios Artistas");
					return;
				}
			}
			this.modificarAutor(nomeArtista);
		}
		return;
	}

	public void adicionarMusica(Musica musica) {
		musicasDoAlbum.add(musica);
		this.acrescentarTempoDeDuracao(musica.retornarDuracao());
		this.acrescentarArmazenamento(musica.retornarTamanho());
		checarArtistaDoAlbum();
		return;
	}
}
