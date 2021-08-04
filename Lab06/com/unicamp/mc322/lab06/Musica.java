package com.unicamp.mc322.lab06;

public class Musica extends Midia {
	Musica(String nome, String autor, double duracao) {
		super(nome, autor, duracao, 5 * (duracao / 60));
	}
}
