package com.unicamp.mc322.lab09.Fila;

import com.unicamp.mc322.lab09.Pessoa;
import com.unicamp.mc322.lab09.Lista.Lista;

public class Fila {
	Lista fila;
	
	Fila(){
		fila = new Lista();
	}
	
	Fila(Pessoa pessoa){
		fila = new Lista(pessoa);
	}
	
	public boolean adicionar(Pessoa pessoa) {
		return fila.adicionarFim(pessoa);
	}
	
	public Pessoa remover() {
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
		fila.imprimirLista();
		return;
	}
}
