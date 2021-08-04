package com.unicamp.mc322.lab09.Lista;

import com.unicamp.mc322.lab09.Pessoa;

public class Lista {
	private No inicio;

	public Lista() {
		inicio = null;
	}

	public Lista(Pessoa pessoa) {
		inicio = new No(pessoa);
	}

	public boolean adicionarInicio(Pessoa pessoa) {
		No aux = inicio;
		inicio = new No(pessoa);
		inicio.proximoNo = aux;
		return true;
	}

	public boolean adicionarFim(Pessoa pessoa) {
		No atual = inicio;
		while (atual.proximoNo != null) {
			atual = atual.proximoNo;
		}
		atual.proximoNo = new No(pessoa);
		return true;
	}

	public boolean adicionar(Pessoa pessoa, int posicao) {
		No atual = inicio;
		while (posicao > 0) {
			posicao -= 1;
			if (atual.proximoNo != null) {
				atual = atual.proximoNo;
			} else {
				return false;
			}
		}
		if (atual.proximoNo != null) {
			No aux = atual.proximoNo;
			atual.proximoNo = new No(pessoa);
			atual.proximoNo.proximoNo = aux;
		} else {
			atual.proximoNo = new No(pessoa);
		}
		return true;
	}

	public Pessoa removerInicio() {
		if (inicio != null) {
			No aux = inicio;
			if (aux.proximoNo != null) {
				inicio = aux.proximoNo;
				inicio.proximoNo = aux.proximoNo.proximoNo;
				return aux.pessoa;
			} else {
				inicio = null;
				return aux.pessoa;
			}
		} else {
			return null;
		}
	}

	public Pessoa removerFim() {
		if (inicio != null) {
			No atual = inicio;
			No anterior = null;
			while (atual.proximoNo != null) {
				anterior = atual;
				atual = atual.proximoNo;
			}
			if (anterior != null) {
				anterior.proximoNo = null;
				return atual.pessoa;
			} else {
				inicio = null;
				return atual.pessoa;
			}
		} else {
			return null;
		}
	}

	public Pessoa remover(int posicao) {
		if (inicio == null) {
			return null;
		} else {
			No atual = inicio;
			No anterior = null;
			while (posicao > 0) {
				posicao -= 1;
				anterior = atual;
				if (atual.proximoNo != null) {
					atual = atual.proximoNo;
				} else {
					return null;
				}
			}
			if (anterior != null) {
				anterior.proximoNo = null;
				return atual.pessoa;
			} else {
				inicio = null;
				return atual.pessoa;
			}
		}
	}

	public int contarTamanho() {
		int contador = 0;
		No atual = inicio;
		while (atual != null) {
			contador += 1;
			atual = atual.proximoNo;
		}
		return contador;
	}

	public void limparLista() {
		inicio = null;
		return;
	}

	public void imprimirLista() {
		for (No atual = inicio; atual != null; atual = atual.proximoNo) {
			System.out.printf("Pessoa: %s\n", atual.pessoa.getNome());
		}
		return;
	}

	public void imprimirLista(int posicaoInicial, int posicaoFinal) {
		No atual = inicio;
		int i = 0;
		while (i != posicaoInicial) {
			i += 1;
			atual = atual.proximoNo;
		}
		for (; atual != null && posicaoInicial <= posicaoFinal; atual = atual.proximoNo) {
			System.out.printf("Pessoa: %s\n", atual.pessoa.getNome());
			posicaoInicial += 1;
		}
		return;
	}
}

class No {
	Pessoa pessoa;
	No proximoNo;

	No(Pessoa pessoa) {
		this.pessoa = pessoa;
		proximoNo = null;
	}
}