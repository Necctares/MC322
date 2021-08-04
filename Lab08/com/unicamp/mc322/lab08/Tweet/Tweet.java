package com.unicamp.mc322.lab08.Tweet;

import java.time.LocalDate;

public abstract class Tweet {
	private String titulo;
	private LocalDate data;
	private int likes;
	private int retweets;
	private int comentarios;
	private String autor;

	protected Tweet(String titulo, String autor) {
		this.titulo = titulo;
		data = LocalDate.now();
		this.likes = 0;
		this.retweets = 0;
		this.comentarios = 0;
		this.autor = autor;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void imprimirInfo() {
		System.out.printf(
				"Tweet: %s\nData de publicacao: %d/%d/%d\nNumero de likes: %d\tRetweets: %d\tComentarios: %d\n", titulo,
				data.getDayOfMonth(), data.getMonthValue(), data.getYear(), likes, retweets, comentarios);
		return;
	}

	public void adicionarLike() {
		likes += 1;
		return;
	}

	public void adicionarRetweet() {
		retweets += 1;
		return;
	}

	public void adicionarComentarios() {
		comentarios += 1;
		return;
	}
}
