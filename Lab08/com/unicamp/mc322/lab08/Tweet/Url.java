package com.unicamp.mc322.lab08.Tweet;

import java.time.LocalDate;

public class Url extends Tweet {
	private LocalDate dataVisita;
	private String url;

	private Url(String titulo, LocalDate dataDeAcesso, String url, String autor) {
		super(titulo, autor);
		this.dataVisita = dataDeAcesso;
		this.url = url;
	}

	public static Url criarTweet(String titulo, LocalDate dataDeAcesso, String url, String autor) {
		if (titulo.length() > 60) {
			titulo = titulo.substring(0, 60);
		}
		if (dataDeAcesso.isBefore(LocalDate.now())) {
			Url tweet = new Url(titulo, dataDeAcesso, url, autor);
			return tweet;
		} else {
			return null;
		}
	}
}
