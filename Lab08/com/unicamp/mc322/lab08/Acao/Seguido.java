package com.unicamp.mc322.lab08.Acao;

public class Seguido extends Acao {
	private String seguidor;
	
	private Seguido(String seguidor) {
		super();
		this.seguidor = seguidor;
	}
	
	public static Seguido seguirUsuario(String seguidor) {
		if (seguidor != null) {
			Seguido novoSeguidor = new Seguido(seguidor);
			return novoSeguidor;
		} else {
			return null;
		}
	}

	@Override
	public String informarAcao() {
		String acao = "foi seguido por " + seguidor + " no dia " + this.getData();
		return acao;
	}
}
