package com.unicamp.mc322.lab09.Fila;

import com.unicamp.mc322.lab09.Pessoa;

public class FilaAtendimentoHeranca extends Fila {
	private int numeroDePrioridades;

	FilaAtendimentoHeranca() {
		super();
		numeroDePrioridades = 0;
	}

	public boolean adicionar(Pessoa pessoa, boolean prioridade) {
		if (prioridade) {
			numeroDePrioridades += 1;
			return fila.adicionarInicio(pessoa);
		} else {
			return fila.adicionarFim(pessoa);
		}
	}

	public Pessoa remover() {
		if (numeroDePrioridades > 0) {
			numeroDePrioridades -= 1;
		}
		return fila.removerInicio();
	}

	public int contarTamanho() {
		return fila.contarTamanho();
	}

	public void limparFila() {
		fila.limparLista();
		return;
	}

	public void imprimirFila() {
		System.out.printf("Pessoas prioritarias na fila (%d):\n", numeroDePrioridades);
		fila.imprimirLista(0, numeroDePrioridades - 1);
		System.out.printf("Pessoas sem prioridade na fila (%d):\n", fila.contarTamanho() - numeroDePrioridades);
		fila.imprimirLista(numeroDePrioridades - 1, fila.contarTamanho() - 1);
		return;
	}
}
