package com.unicamp.mc322.lab13.Estrategia;

import java.util.Comparator;

import com.unicamp.mc322.lab13.Pedidos.IOrder;

public interface IOrderingStrategy extends Comparator<IOrder> {
	/**
	 * Calcula a prioridade do pedido
	 * @param pedido para calcular a prioridade
	 * @return double contendo o valor da prioridade do pedido
	 */
	public double calcularPrioridade(IOrder pedido);
}
