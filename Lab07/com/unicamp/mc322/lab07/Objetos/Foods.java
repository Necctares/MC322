package com.unicamp.mc322.lab07.Objetos;

import com.unicamp.mc322.lab07.Core.Posicao;

public abstract class Foods extends Objetos {

	/**
	 * Instancia um objeto Foods
	 * @param icone do objeto
	 * @param pos Posicao do objeto
	 */
	Foods(String icone, Posicao pos) {
		super(icone, TipoDeObjetos.Food, pos);
	}
}
