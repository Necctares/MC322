package com.unicamp.mc322.lab04;

public enum StatusDoPedido {
	NOVO, PREPARANDO, SAIUPARAENTREGA, ENTREGUE;

	/**
	 * Altera o Status atual do pedido respeitando o ciclo: NOVO => PREPARANDO =>
	 * SAIUPARAENTREGA => ENTREGUE
	 * 
	 * @param pedido que terá o status atualizado.
	 * @return novo status do pedido.
	 */
	static StatusDoPedido alterarStatus(StatusDoPedido pedido) {
		switch (pedido) {
		case NOVO:
			pedido = StatusDoPedido.PREPARANDO;
			break;
		case PREPARANDO:
			pedido = StatusDoPedido.SAIUPARAENTREGA;
			break;
		case SAIUPARAENTREGA:
			pedido = StatusDoPedido.ENTREGUE;
			break;
		default:
			System.out.printf("Erro: Pedido ja entregue.\n");
			break;
		}
		return pedido;
	}

	/**
	 * Imprime o status atual do pedido.
	 * 
	 * @param pedido que terá o status impresso.
	 */
	static void imprimirStatus(StatusDoPedido pedido) {
		switch (pedido) {
		case NOVO:
			System.out.printf("Status: Novo Pedido, usuario ainda não confirmou o pedido.\n");
			break;
		case PREPARANDO:
			System.out.printf("Status: Pedido em preparação, usuario já fez a confirmação.\n");
			break;
		case SAIUPARAENTREGA:
			System.out.printf("Status: Saiu Para Entrega, em breve o pedido será entregue.\n");
			break;
		default:
			System.out.printf("Status: Pedido Entregue.\n");
			break;
		}
		return;
	}
}
