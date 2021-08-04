package com.unicamp.mc322.lab08.Acao;

import java.time.LocalDate;

public abstract class Acao {
	private LocalDate dataAcao;

	protected Acao() {
		dataAcao = LocalDate.now();
	}
	
	public String getData() {
		String dataTweet = String.valueOf(dataAcao.getDayOfMonth()) + "/" + String.valueOf(dataAcao.getMonthValue()) + "/" + String.valueOf(dataAcao.getYear());
		return dataTweet;
	}
	
	public abstract String informarAcao();
}
