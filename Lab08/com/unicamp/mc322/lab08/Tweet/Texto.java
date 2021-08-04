package com.unicamp.mc322.lab08.Tweet;

public class Texto extends Tweet {
	private String texto;
	private String idioma;

	private Texto(String titulo, String texto, String idioma, String autor) {
		super(titulo, autor);
		this.texto = texto;
		this.idioma = idioma;
	}

	public static Texto criarTweet(String titulo, String texto, String idioma, String autor) {
		if (titulo.length() > 60) {
			titulo = titulo.substring(0, 60);
		}
		if (texto.length() <= 1200) {
			Texto tweet = new Texto(titulo, texto, idioma, autor);
			return tweet;
		} else {
			return null;
		}
	}
}
