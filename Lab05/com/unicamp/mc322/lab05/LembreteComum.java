package com.unicamp.mc322.lab05;

public class LembreteComum extends Lembrete {
	LembreteComum(String lembrete) {
		super(lembrete);
	}
	
	@Override
	protected void imprimirLembrete() {
		System.out.printf("****Lembrete****\nDescricao: %s\n\n", descricao);
	}
}
