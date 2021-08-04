package com.unicamp.mc322.lab13.Estrategia;

import com.unicamp.mc322.lab13.Pedidos.IOrder;

/**
 * 
 * Utiliza a metrica padrão do calculo dado (Priority Score), porém, adiciona um
 * bonus de 20% do valor total se o pedido foi feito em Loja Fisica
 *
 */
public class StorePriorityScore implements IOrderingStrategy {

	@Override
	public int compare(IOrder o1, IOrder o2) {
		if (calcularPrioridade(o1) > calcularPrioridade(o2)) {
			return -1;
		} else if (calcularPrioridade(o1) < calcularPrioridade(o2)) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public double calcularPrioridade(IOrder pedido) {
		double prioridade = (pedido.obterPessoa().retornarIdade() / 100.0) + (0.07 * pedido.obterNumeroTurno());
		if (pedido.checarLojaFisica()) {
			prioridade += prioridade * 0.2;
		}
		return prioridade;
	}

}
