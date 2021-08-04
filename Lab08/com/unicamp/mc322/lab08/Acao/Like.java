package com.unicamp.mc322.lab08.Acao;

import com.unicamp.mc322.lab08.Tweet.Tweet;

public class Like extends Acao {
	private Tweet liked;

	private Like(Tweet likedTweet) {
		super();
		this.liked = likedTweet;
	}

	public static Like gostarTweet(Tweet liked) {
		if (liked != null) {
			Like novoLike = new Like(liked);
			liked.adicionarLike();
			return novoLike;
		} else {
			return null;
		}
	}

	@Override
	public String informarAcao() {
		String acao = "deu like no tweet " + liked.getTitulo() + " de " + liked.getAutor() + " no dia " + this.getData();
		return acao;
	}

	
}
