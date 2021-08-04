package com.unicamp.mc322.lab06;

public class Podcast extends Midia {
	private int numeroEpisodios;
	
	Podcast(String nome, String autor, double duracaoEpisodio, int numeroDeEpisodios){
		super(nome, autor, duracaoEpisodio, numeroDeEpisodios*duracaoEpisodio);
		numeroEpisodios = numeroDeEpisodios;
	}
}
