package com.unicamp.mc322.lab08.Acao;

import com.unicamp.mc322.lab08.Tweet.Tweet;

public class Comentario extends Acao {
	private String comentario;
	private Tweet tweetComentado;

	private Comentario(String comentario, Tweet comentado) {
		super();
		this.comentario = comentario;
		this.tweetComentado = comentado;
	}

	public static Comentario comentarTweet(Tweet comentado, String comentario) {
		if (comentario.length() <= 100) {
			Comentario novoComentario = new Comentario(comentario, comentado);
			comentado.adicionarComentarios();
			return novoComentario;
		} else {
			return null;
		}
	}

	@Override
	public String informarAcao() {
		String acao = "fez um comentario no tweet " + tweetComentado.getTitulo() + " de " + tweetComentado.getAutor() + ": \""+ comentario + "\" no dia " + this.getData();
		return acao;
	}
}
