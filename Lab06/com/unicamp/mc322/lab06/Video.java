package com.unicamp.mc322.lab06;

public class Video extends Midia {
	Video(String nome, String autor, double duracao, Resolucao resolucao, int fps) {
		super(nome, autor, duracao, ((duracao/60 * 5) + ((resolucao.retornarNumPixels() * fps * (duracao/60)) / 1000000)));
	}
}
