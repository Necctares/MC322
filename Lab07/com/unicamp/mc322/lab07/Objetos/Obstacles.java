package com.unicamp.mc322.lab07.Objetos;

import com.unicamp.mc322.lab07.Core.Posicao;

public class Obstacles extends Objetos {

	/**
	 * Instancia um novo Obstacles
	 * 
	 * @param icone do obstaculo
	 * @param pos   Posição do obstaculo
	 */
	Obstacles(String icone, Posicao pos) {
		super(icone, TipoDeObjetos.Obstacle, pos);
	}

	/**
	 * Define a interação de um obstaculo com outros objetos.
	 */
	@Override
	public double interagirComObjeto(Objetos obj) {
		if (obj == null) {
			return 0;
		} else if (obj.retornarTipo().equals(TipoDeObjetos.Frog)) {
			return -1;
		} else {
			return 0;
		}
	}

}
