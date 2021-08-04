package com.unicamp.mc322.lab08.Tweet;

public class Imagem extends Tweet {
	private Resolucao resolucao;
	private double tamanho;
	private String paraCegoVer;
	private Extensao extensao;

	private Imagem(String titulo, String textoParaCego, double tamanho, Resolucao resolucao, Extensao extensao, String autor) {
		super(titulo, autor);
		this.resolucao = resolucao;
		this.tamanho = tamanho;
		this.paraCegoVer = textoParaCego;
		this.extensao = extensao;
	}

	public static Imagem criarTweet(String titulo, double tamanho, Extensao extensao, Resolucao resolucao,
			String textoParaCego, String autor) {
		if (titulo.length() > 60) {
			titulo = titulo.substring(0, 60);
		}
		if (Extensao.checarExtensaoPermitidaDeImagem(extensao)) {
			Imagem imagem = new Imagem(titulo, textoParaCego, tamanho, resolucao, extensao, autor);
			return imagem;
		} else {
			return null;
		}
	}
}
