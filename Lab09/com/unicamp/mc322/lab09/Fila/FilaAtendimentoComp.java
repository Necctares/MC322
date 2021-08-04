package com.unicamp.mc322.lab09.Fila;

import com.unicamp.mc322.lab09.Pessoa;

public class FilaAtendimentoComp {
	private Fila normal;
	private Fila prioritaria;

	FilaAtendimentoComp() {
		normal = new Fila();
		prioritaria = new Fila();
	}

	public boolean adicionar(Pessoa pessoa) {
		return normal.adicionar(pessoa);
	}

	public boolean adicionarPrioritario(Pessoa pessoa) {
		return prioritaria.adicionar(pessoa);
	}

	public Pessoa remover() {
		if (prioritaria.contarTamanho() > 0) {
			return prioritaria.remover();
		} else {
			return normal.remover();
		}
	}

	public int contarTamanho() {
		return prioritaria.contarTamanho() + normal.contarTamanho();
	}

	public void imprimirFila() {
		System.out.printf("Pessoas prioritarias na fila (%d):\n", prioritaria.contarTamanho());
		prioritaria.imprimirFila();
		System.out.printf("Pessoas sem prioridade na fila (%d):\n", normal.contarTamanho());
		normal.imprimirFila();
		return;
	}
}
