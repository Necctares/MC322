package com.unicamp.mc322.lab13.Container;

import com.unicamp.mc322.lab13.Estrategia.IOrderingStrategy;
import com.unicamp.mc322.lab13.Exceptions.CrazyDSOutOfBoundException;
import com.unicamp.mc322.lab13.Pedidos.IOrder;
import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

public class ICrazyDSContainer implements ICrazyDS {
	private List<IOrder> pedidos;
	private IOrderingStrategy estrategia;
	
	/**
	 * Cria um novo container para armazenar os pedidos
	 * @param estrategia Implementada para ordenar os pedidos feitos
	 */
	public ICrazyDSContainer(IOrderingStrategy estrategia) {
		pedidos = new LinkedList<IOrder>();
		this.estrategia = estrategia;
	}
	
	@Override
	public boolean adicionarElemento(IOrder pedido) {
		boolean adicionou = false;
		if (pedidos.contains(pedido)) {
			return adicionou;
		}
		adicionou = pedidos.add(pedido);
		if (adicionou) {
			Collections.sort(pedidos, estrategia);
		}
		return adicionou;
	}

	@Override
	public boolean removerElemento(IOrder pedido) {
		return pedidos.remove(pedido);
	}

	@Override
	public IOrder obterMaiorPrioridade() {
		if (pedidos.size() == 0) {
			throw new CrazyDSOutOfBoundException("Lista vazia, acesso invalido");
		}
		return pedidos.get(0);
	}

	@Override
	public void imprimirListaPrioritaria() {
		if (pedidos.isEmpty()) {
			System.out.println("Lista vazia");
		}
		for (IOrder p : pedidos) {
			p.imprimirReduzido();
		}
		return;
	}

	@Override
	public IOrder obterElementoNaPosicao(int posicao) {
		if (pedidos.size() <= posicao || posicao < 0) {
			throw new CrazyDSOutOfBoundException("Posicao invalida de acesso");
		}
		return pedidos.get(posicao);
	}
}
