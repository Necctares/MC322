package com.unicamp.mc322.lab05;

import java.time.DayOfWeek;

public class LembreteSemanal extends Lembrete {
	LembreteSemanal(String lembrete, DayOfWeek dia) {
		super(lembrete, dia);
	}
	
	@Override
	protected void imprimirLembrete() {
		System.out.printf("****Lembrete****\nDia da semana: %s\nDescricao: %s\n\n", diaSemana.toString(), descricao);
	}
}
