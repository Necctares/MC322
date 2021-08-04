package com.unicamp.mc322.lab08.Tweet;

public class Video extends Tweet {
	private double duracao;
	private double tamanho;
	private Extensao extensao;

	private Video(String titulo, Extensao extensao, double duracao, double tamanho, String autor) {
		super(titulo, autor);
		this.extensao = extensao;
		this.duracao = duracao;
		this.tamanho = tamanho;
	}

	public static Video criarTweet(String titulo, double duracao, Extensao extensao, double tamanho, String autor) {
		if (titulo.length() > 60) {
			titulo = titulo.substring(0, 60);
		}
		if (Extensao.checarExtensaoPermitidaDeVideo(extensao)) {
			Video video = new Video(titulo, extensao, duracao, tamanho, autor);
			return video;
		} else {
			return null;
		}
	}

}
