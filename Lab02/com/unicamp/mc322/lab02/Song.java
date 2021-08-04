package com.unicamp.mc322.lab02;

public class Song {
	private String nome;
	private String genero;
	private String artista;
	private float duracao;

	public Song(String nomeDaMusica, String generoDaMusica, String artistaDaMusica) {
		nome = nomeDaMusica;
		genero = generoDaMusica;
		artista = artistaDaMusica;
		duracao = 0;
	}

	public Song(String nomeDaMusica, String generoDaMusica, String artistaDaMusica, float duracaoDaMusica) {
		this(nomeDaMusica, generoDaMusica, artistaDaMusica);
		duracao = duracaoDaMusica;
	}

	public void alterarNome(String novoNome) {
		nome = novoNome;
		return;
	}

	public void alterarGenero(String novoGenero) {
		genero = novoGenero;
		return;
	}

	public void alterarArtista(String novoArtista) {
		artista = novoArtista;
		return;
	}

	public String retornarNome() {
		return nome;
	}

	public String retornarArtista() {
		return artista;
	}

	public float retornarDuracao() {
		return duracao;
	}
}
