package com.unicamp.mc322.lab13.Estrategia;

import com.unicamp.mc322.lab13.Pedidos.IOrder;

/**
 * 
 * Metrica padrão do calculo dado (Priority Score)
 *
 */
public class PriorityScore implements IOrderingStrategy {

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
		return prioridade;
	}

}
