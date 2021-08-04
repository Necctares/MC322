package com.unicamp.mc322.lab05;

import java.time.*;

public class Lembrete {
	protected DayOfWeek diaSemana;
	protected int dia;
	protected Month mes;
	protected int ano;
	protected String descricao;

	Lembrete(String descricao, LocalDate data) {
		diaSemana = data.getDayOfWeek();
		dia = data.getDayOfMonth();
		mes = data.getMonth();
		ano = data.getYear();
		this.descricao = descricao;
	}

	Lembrete(String descricao) {
		this.descricao = descricao;
	}

	Lembrete(String descricao, Month mes) {
		this.descricao = descricao;
		this.mes = mes;
	}

	Lembrete(String descricao, DayOfWeek dia) {
		this.descricao = descricao;
		diaSemana = dia;
	}

	Lembrete(String descricao, int dia, Month mes, int ano) {
		this.descricao = descricao;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	protected void imprimirLembrete() {
		System.out.printf("****Lembrete****\nData: %d/%d/%d (%s)\nDescricao: %s\n\n", dia, mes.getValue(), ano,
				diaSemana.toString(), descricao);
		return;
	}

	protected boolean checarDescricao(String descricaoParaComparacao) {
		return descricao.equalsIgnoreCase(descricaoParaComparacao);
	}
}
