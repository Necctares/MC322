package com.unicamp.mc322.lab05;

import java.time.Month;

public class LembreteMensal extends Lembrete {
	LembreteMensal(String lembrete, Month mes){
		super(lembrete,mes);
	}
	
	@Override
	protected void imprimirLembrete() {
		System.out.printf("****Lembrete****\nMes de ocorrencia: %s\nDescricao: %s\n\n", mes.toString(), descricao);
	}
}
