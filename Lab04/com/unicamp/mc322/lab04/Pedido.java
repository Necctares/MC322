package com.unicamp.mc322.lab04;

import java.util.*;

public class Pedido {
	private User usuario;
	private List<Lanche> pedido;
	private StatusDoPedido status;
	private double valorTotal;

	/**
	 * Cria um novo pedido.
	 * 
	 * @param usuario (User) responsavel pelo pedido.
	 */
	Pedido(User usuario) {
		this.usuario = usuario;
		pedido = new ArrayList<Lanche>();
		status = StatusDoPedido.NOVO;
		valorTotal = 0;
	}

	/**
	 * Adiciona um item ao pedido.
	 * 
	 * @param lanche que será adicionado.
	 */
	public void addItem(Lanche lanche) {
		pedido.add(lanche);
		return;
	}

	/**
	 * Remove um item do pedido.
	 * 
	 * @param lanche que será removido.
	 */
	public void removerItem(Lanche lanche) {
		if (!pedido.remove(lanche)) {
			System.out.printf("Erro: Lanche nao esta na lista.\n");
		}
		return;
	}

	/**
	 * Finaliza o pedido do cliente. O status do pedido mudará de NOVO para
	 * PREPARANDO.
	 * 
	 * @param valor total do pedido.
	 */
	void fecharPedido(double valor) {
		valorTotal = valor;
		status = StatusDoPedido.alterarStatus(status);
		return;
	}

	/**
	 * Imprime um resumo do pedido.
	 */
	void imprimirPedido() {
		usuario.imprimirInfo();
		for (int i = 0; i < pedido.size(); i++) {
			System.out.printf("- %s\n", pedido.get(i).getId());
		}
		StatusDoPedido.imprimirStatus(status);
		System.out.printf("Valor Total: R$ %.2f\n", valorTotal);
		return;
	}

	/**
	 * Verifica o Status do pedido.
	 * 
	 * @param statusComparativo Status que será comparado com o status atual do
	 *                          pedido.
	 * @return true se os status forem iguais, false caso contrario.
	 */
	boolean verificarSeOStatusEhIgual(StatusDoPedido statusComparativo) {
		boolean ehIgual = false;
		if (status.equals(statusComparativo)) {
			ehIgual = true;
		}
		return ehIgual;
	}

	/**
	 * 
	 * @return Retorna os itens do pedido. (List<\Lanche>)
	 */
	List<Lanche> retornarPedidos() {
		return pedido;
	}

	/**
	 * 
	 * @return Retorna o id(Cpf) do cliente responsavel pelo pedido.
	 */
	String retornarIdCliente() {
		return usuario.getCpf();
	}

	/**
	 * Muda o Status atual do lanche respeitando o ciclo: NOVO => PREPARANDO =>
	 * SAIUPARAENTREGA => ENTREGUE
	 */
	void aprontarPedido() {
		status = StatusDoPedido.alterarStatus(status);
		return;
	}
}
