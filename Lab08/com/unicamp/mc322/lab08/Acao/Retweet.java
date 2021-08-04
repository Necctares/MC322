package com.unicamp.mc322.lab08.Acao;

import com.unicamp.mc322.lab08.Tweet.Tweet;

public class Retweet extends Acao {
	private Tweet retweet;

	private Retweet(Tweet retweet) {
		super();
		this.retweet = retweet;
	}

	public static Retweet compartilharTweet(Tweet retweet) {
		if (retweet != null) {
			Retweet novoRT = new Retweet(retweet);
			retweet.adicionarRetweet();
			return novoRT;
		} else {
			return null;
		}
	}

	@Override
	public String informarAcao() {
		String acao = "retweetou " + retweet.getTitulo() + " de " + retweet.getAutor() + " no dia " + this.getData();
		return acao;
	}
}
