package com.unicamp.mc322.lab03;

public class Room {
	private Boolean ehVip;
	private Boolean ocupado;
	private Boolean fumante;
	private Boolean arCondicionado;
	
	Room(Boolean ehVip, Boolean fumante, Boolean arCondicionado){
		this.ehVip = ehVip;
		this.ocupado = false;
		this.fumante = fumante;
		this.arCondicionado = arCondicionado;
	}
	
	/** Retorna true caso ocupado, caso vazio, retorna false.*/
	Boolean checarVaga() {
		return ocupado;
	}

	Boolean checarVIP() {
		return ehVip;
	}
	
	Boolean checarFumante() {
		return fumante;
	}
	
	Boolean checarAr() {
		return arCondicionado;
	}
	
	void mudarStatus() {
		ocupado = !ocupado;
	}
}
