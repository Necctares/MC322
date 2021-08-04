package com.unicamp.mc322.lab13.Pedidos;

import com.unicamp.mc322.lab13.Pessoa;

public interface IOrder {
	/**
	 * Incrementa em 1 o turno do pedido
	 */
	public void incrementarTurno();
	/**
	 * Obtem o numero de turnos em espera do pedido
	 * @return Int contendo o numero de turnos de espera do pedido
	 */
	public int obterNumeroTurno();
	/**
	 * 
	 * @return String com o codigo do pedido
	 */
	public String obterCodigo();
	/**
	 * Imprime as informações basicas do pedido (Nome do Cliente + Codigo)
	 */
	public void imprimirReduzido();
	/**
	 * Imprime as informações completas do pedido (Dados do Cliente + Codigo)
	 */
	public void imprimirCompleto();
	/**
	 * 
	 * @return Pessoa responsavel pelo pedido
	 */
	public Pessoa obterPessoa();
	/**
	 * 
	 * @return boolean: true caso o pedido foi feito em loja fisica, false caso contrario
	 */
	public boolean checarLojaFisica();
}
