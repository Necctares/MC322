package com.unicamp.mc322.lab10;

import com.unicamp.mc322.lab10.Pedido.*;

public class Entregador {
	private String nome;
	private String cpf;
	private boolean ocupado;
	private Pedido pedidoAtual;
	private Avaliacao avaliacao;

	/**
	 * Cria um novo entregador
	 * 
	 * @param nome do entregador
	 * @param cpf  do entregador
	 */
	Entregador(String nome, String cpf) {
		ocupado = false;
		pedidoAtual = null;
		avaliacao = new Avaliacao();
		this.nome = nome;
		this.cpf = cpf;
	}

	/**
	 * Imprime as informações do entregador
	 */
	public void imprimirInfo() {
		if (avaliacao.retornarAvaliacao() >= 0) {
			System.out.printf("Nome: %s\tCPF: %s\tAvaliacao: %.2f\n", nome, cpf, avaliacao.retornarAvaliacao());
		} else {
			System.out.printf("Nome: %s\tCPF: %s\tAvaliacao: Sem Avaliacao\n", nome, cpf);
		}
		return;
	}

	/**
	 * Checa se o entregador está ocupado.
	 * 
	 * @return True caso o entregador esteja ocupado, False caso contrario.
	 */
	public boolean checarSeEstaOcupado() {
		return ocupado;
	}

	/**
	 * Atribui uma entrega ao entregador
	 * 
	 * @param pedidoAtual Pedido a ser entregue
	 */
	public void atribuirEntrega(Pedido pedidoAtual) {
		if (!ocupado) {
			ocupado = true;
			this.pedidoAtual = pedidoAtual;
		}
		return;
	}

	/**
	 * Encerra a entrega atual
	 */
	public void encerrarEntrega() {
		if (pedidoAtual != null) {
			ocupado = false;
			pedidoAtual.finalizarEntrega();
		}
		return;
	}

	/**
	 * Recebe uma avaliacao
	 * 
	 * @param avaliacao Valor entre 0 e 5 avaliando o entregador
	 */
	public void receberAvaliacao(int avaliacao) {
		this.avaliacao.receberAvaliacao(avaliacao);
		return;
	}
}
