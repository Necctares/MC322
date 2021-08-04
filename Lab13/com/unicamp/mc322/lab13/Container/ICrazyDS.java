package com.unicamp.mc322.lab13.Container;

import com.unicamp.mc322.lab13.Pedidos.IOrder;

public interface ICrazyDS {
	/**
	 * Adiciona um novo elemento ao container
	 * @param pedido que será adicionado
	 * @return boolean: True caso tenha adicionado com sucesso, False caso contrario
	 */
	public boolean adicionarElemento(IOrder pedido);
	/**
	 * Remove um elemento do container
	 * @param pedido que será removido
	 * @return boolean: true caso o elemento tenha sido removido com sucesso, false caso contrario
	 */
	public boolean removerElemento(IOrder pedido);
	/**
	 * Obtem o elemento com maior prioridade no container
	 * @return IOrder: elemento de maior prioridade
	 */
	public IOrder obterMaiorPrioridade();
	/**
	 * Obtem o elemento na posição especificada
	 * @param posicao do elemento
	 * @return IOrder
	 * @throws CrazyDSOutOfBoundException caso a posição seja invalida
	 */
	public IOrder obterElementoNaPosicao(int posicao);
	/**
	 * Imprime todos os elementos do container
	 */
	public void imprimirListaPrioritaria();
}
