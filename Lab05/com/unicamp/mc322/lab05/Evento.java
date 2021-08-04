package com.unicamp.mc322.lab05;

import java.time.Month;

public class Evento extends Lembrete {
	Evento(String lembrete, int dia, Month mes, int ano) {
		super(lembrete, dia, mes, ano);
	}

	@Override
	protected void imprimirLembrete() {
		System.out.printf("****Lembrete****\nData do Evento: %d/%d/%d\nDescricao: %s\n\n", dia, mes.getValue(), ano,
				descricao);
	}

	protected String retornarHash() {
		String id = "" + dia + mes.toString() + ano;
		return id;
	}
}
