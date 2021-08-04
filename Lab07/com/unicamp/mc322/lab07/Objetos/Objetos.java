package com.unicamp.mc322.lab07.Objetos;

import com.unicamp.mc322.lab07.Core.Posicao;

public abstract class Objetos {
	private String icone;
	private TipoDeObjetos tipo;
	protected Posicao pos;

	/**
	 * Instancia um novo Objetos
	 * 
	 * @param icone do objeto
	 * @param tipo  do objeto
	 * @param pos   Posicao inicial do objeto
	 */
	Objetos(String icone, TipoDeObjetos tipo, Posicao pos) {
		this.icone = icone;
		this.tipo = tipo;
		this.pos = pos;
	}

	/**
	 * Define a intera��o com outro objeto.
	 * 
	 * @param obj Objeto com que este objeto ir� interagir
	 * @return um valor x double, que significa algum resultado de intera��o.
	 */
	public abstract double interagirComObjeto(Objetos obj);

	/**
	 * 
	 * @return o TipoDeObjetos do objeto
	 */
	public TipoDeObjetos retornarTipo() {
		return tipo;
	}

	/**
	 * 
	 * @return a Posi��o atual do objeto.
	 */
	public Posicao retornarPosicao() {
		return pos;
	}

	/**
	 * 
	 * @return uma String contendo o icone do objeto.
	 */
	public String retornarIcone() {
		return icone;
	}
}
