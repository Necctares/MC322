package com.unicamp.mc322.lab08.Acao;

public class Seguir extends Acao {
	private String seguidor;

	private Seguir(String seguidor) {
		super();
		this.seguidor = seguidor;
	}

	public static Seguir seguirUsuario(String seguidor) {
		if (seguidor != null) {
			Seguir novoSeguidor = new Seguir(seguidor);
			return novoSeguidor;
		} else {
			return null;
		}
	}

	@Override
	public String informarAcao() {
		String acao = "comecou a seguir " + seguidor + " no dia " + this.getData();
		return acao;
	}
}
