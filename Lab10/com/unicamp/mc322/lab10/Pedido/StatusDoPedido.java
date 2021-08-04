package com.unicamp.mc322.lab10.Pedido;

public enum StatusDoPedido {
	NOVO, PREPARANDO, PRONTOPARAENTREGA, PRONTOPARARETIRAR, SAIUPARAENTREGA, ENTREGUE, CANCELADO;

	/**
	 * Altera o Status atual do pedido respeitando o ciclo: NOVO => PREPARANDO =>
	 * PRONTOPARAENTREGA/PRONTOPARARETIRAR => SAIUPARAENTREGA(Caso entrega seja
	 * true) => ENTREGUE
	 * 
	 * @param pedido  que terá o status atualizado.
	 * @param entrega Boolean True caso seja para entrega, false caso seja para
	 *                retirada
	 * @return novo status do pedido.
	 */
	static StatusDoPedido alterarStatus(StatusDoPedido pedido, boolean entrega) {
		switch (pedido) {
		case NOVO:
			pedido = StatusDoPedido.PREPARANDO;
			break;
		case PREPARANDO:
			if (entrega) {
				pedido = StatusDoPedido.PRONTOPARAENTREGA;
			} else {
				pedido = StatusDoPedido.PRONTOPARARETIRAR;
			}
			break;
		case PRONTOPARAENTREGA:
			pedido = StatusDoPedido.SAIUPARAENTREGA;
			break;
		case SAIUPARAENTREGA:
			pedido = StatusDoPedido.ENTREGUE;
			break;
		case PRONTOPARARETIRAR:
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
		case PRONTOPARAENTREGA:
			System.out.printf("Status: Pedido pronto para ser entregue, aguardando entregador.\n");
			break;
		case SAIUPARAENTREGA:
			System.out.printf("Status: Saiu Para Entrega, em breve o pedido será entregue.\n");
			break;
		case PRONTOPARARETIRAR:
			System.out.printf("Status: Pronto para retirada, esperando o cliente.\n");
			break;
		case ENTREGUE:
			System.out.printf("Status: Pedido entregue.\n");
			break;
		default:
			System.out.printf("Status: Pedido Cancelado.\n");
			break;
		}
		return;
	}
}
