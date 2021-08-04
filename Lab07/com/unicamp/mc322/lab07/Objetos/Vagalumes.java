package com.unicamp.mc322.lab07.Objetos;

import com.unicamp.mc322.lab07.Core.Posicao;

public class Vagalumes extends Foods {
	private static final int pontos = 15;

	/**
	 * Instancia um novo Vagalumes
	 * 
	 * @param pos Posicao do vagalume
	 */
	public Vagalumes(Posicao pos) {
		super("va", pos);
	}

	/**
	 * Define a interação do vagalume com outros objetos.
	 */
	@Override
	public double interagirComObjeto(Objetos obj) {
		if (obj.retornarTipo().equals(TipoDeObjetos.Frog)) {
			Frog ra = (Frog) obj;
			switch (ra.retornarTipoDeRa()) {
			case verde:
				return pontos;
			case tomato:
				return pontos + (pontos * 0.1);
			case venenosa:
				return pontos + (pontos * 0.2);
			default:
				System.out.printf("Erro: Tipo nao existente.\n");
				return -1;
			}
		} else {
			return 0;
		}
	}

}
