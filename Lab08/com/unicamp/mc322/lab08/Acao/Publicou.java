package com.unicamp.mc322.lab08.Acao;

import com.unicamp.mc322.lab08.Tweet.Tweet;

public class Publicou extends Acao {
	private Tweet publicado;

	private Publicou(Tweet publicado) {
		super();
		this.publicado = publicado;
	}

	public static Publicou publicarTweet(Tweet publicado) {
		if (publicado != null) {
			Publicou novoTweet = new Publicou(publicado);
			return novoTweet;
		} else {
			return null;
		}
	}

	@Override
	public String informarAcao() {
		String acao = "publicou o tweet " + publicado.getTitulo() + " no dia " + this.getData();
		return acao;
	}
}
