package com.unicamp.mc322.lab10.Restaurante;

import java.util.*;

import com.unicamp.mc322.lab10.*;
import com.unicamp.mc322.lab10.Pedido.*;

/**
 * Classe que administra as funções do restaurante
 *
 *
 */
public class Restaurante {
	private String nome;
	private String cnpj;
	private Posicao posicao;
	private List<Entregador> entregadores;
	private Cardapio cardapio;
	private List<Pedido> pedidos;
	private Avaliacao avaliacao;

	/**
	 * Cria um novo restaurante
	 * @param nome do restaurante
	 * @param cnpj do restaurante
	 * @param posicao do restaurante (endereco cartesiano)
	 */
	public Restaurante(String nome, String cnpj, Posicao posicao) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.posicao = posicao;
		entregadores = new ArrayList<Entregador>();
		cardapio = new Cardapio();
		pedidos = new ArrayList<Pedido>();
		avaliacao = new Avaliacao();
	}

	/**
	 * Calcula valor do pedido
	 * @param lanches Lista com os lanches do pedido
	 * @return Double valor total do pedido
	 */
	public double calcularValorPedido(List<Lanche> lanches) {
		double valorTotal = 0;
		for (Lanche atual : lanches) {
			valorTotal += cardapio.retornarValorItem(atual);
		}
		return valorTotal;
	}

	/**
	 * Imprime informação do restaurante
	 */
	public void imprimirInfo() {
		System.out.printf("**********\nNome: %s\tCNPJ: %s\n**********\n", nome, cnpj);
	}

	/**
	 * Imprime uma lista com as avaliações dos Lanches e do restaurante
	 */
	public void imprimirAvaliacao() {
		if (avaliacao.retornarAvaliacao() >= 0) {
			System.out.printf("Restaurante: %s\tAvaliacao Media: %.2f\tAvaliacao do cardapio:\n", nome,
					avaliacao.retornarAvaliacao());
		}else {
			System.out.printf("Restaurante: %s\tAvaliacao Media: Sem Avaliacoes\tAvaliacao do cardapio:\n", nome);
		}
		cardapio.imprimirAvaliacoes();
		return;
	}

	/**
	 * Imprime o cardapio
	 */
	public void imprimirCardapio() {
		this.imprimirInfo();
		cardapio.imprimirCardapio();
		return;
	}

	/**
	 * Imprime todos os pedidos do restaurante
	 */
	public void imprimirPedidos() {
		this.imprimirInfo();
		for (Pedido atual : pedidos) {
			atual.imprimirPedido();
			System.out.println();
		}
	}

	/**
	 * 
	 * @return Posicao do restaurante (endereço)
	 */
	public Posicao retornarPosicao() {
		return posicao;
	}

	/**
	 * Adiciona um pedido a lista de pedidos
	 * @param pedido recebido
	 */
	public void receberPedido(Pedido pedido) {
		pedidos.add(pedido);
		return;
	}

	/**
	 * Adiciona um lanche ao cardapio
	 * @param lanche a ser adicionado
	 * @return True se o lanche foi adicionado, False caso contrario
	 */
	public boolean adicionarAoCardapio(Lanche lanche) {
		return cardapio.adicionarAoCardapio(lanche);
	}

	/**
	 * Remove um lanche do cardapio
	 * @param lanche a ser removido
	 * @return True se o lanche foi removido, False caso contrario
	 */
	public boolean removerDoCardapio(Lanche lanche) {
		return cardapio.removerDoCardapio(lanche);
	}

	/**
	 * Aplica um desconto a um item do cardapio
	 * @param id do item
	 * @param descontoAplicado valor absoluto(abs()) do desconto.
	 * @param tipo do desconto a ser aplicado (Porcentagem ou Monetario)
	 * @return True caso o desconto foi aplicado, False caso contrario.
	 */
	public boolean aplicarDesconto(String id, double descontoAplicado, TipoDesconto tipo) {
		return cardapio.aplicarDesconto(id, descontoAplicado, tipo);
	}

	/**
	 * Remove o desconto de um item do cardapio
	 * @param id do item
	 * @return True caso o desconto tenha sido removido, False caso contrario.
	 */
	public boolean removerDesconto(String id) {
		return cardapio.removerDesconto(id);
	}

	/**
	 * Prepara o proximo pedido com Status PREPARANDO
	 * @return Pedido que foi preparado, null caso nenhum pedido esteja em PREPARANDO
	 */
	public Pedido prepararProximoPedido() {
		for (Pedido atual : pedidos) {
			if (atual.aprontarPedido()) {
				return atual;
			}
		}
		return null;
	}

	/**
	 * Entrega o proximo pedido com Status PRONTOPARAENTREGA
	 * @return True caso algum pedido tenha saido para entrega, False caso contrario
	 */
	public boolean entregarProximoPedido() {
		Iterator<Entregador> entregadorIt = entregadores.iterator();
		Entregador entregador;
		if (entregadorIt.hasNext()) {
			entregador = entregadorIt.next();
			while (entregadorIt.hasNext() && entregador.checarSeEstaOcupado()) {
				entregador = entregadorIt.next();
			}
			if (!entregador.checarSeEstaOcupado()) {
				for (Pedido atual : pedidos) {
					if (atual.atribuirEntregador(entregador)) {
						entregador.atribuirEntrega(atual);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Função utilizada para quando um pedido é retirado no balcão pelo cliente.
	 * @param pedidoRetirado Pedido a ser retirado
	 * @return True caso tenha sido retirado com sucesso, False caso contrario.
	 */
	public boolean retirarPedido(Pedido pedidoRetirado) {
		if (pedidos.contains(pedidoRetirado) && !pedidoRetirado.checarEntrega()) {
			return pedidoRetirado.finalizarEntrega();
		} else {
			return false;
		}
	}

	/**
	 * Recebe avaliação de algum pedido.
	 * @param avaliacao Valor int entre 0-5 para avaliacao
	 */
	public void receberAvaliacao(int avaliacao) {
		this.avaliacao.receberAvaliacao(avaliacao);
		return;
	}

	/**
	 * Adiciona um entregador a lista de entregadores do restaurante
	 * @param entregador a ser adicionado
	 * @return True se o entregador foi adicionado, False caso contrario.
	 */
	public boolean adicionarEntregador(Entregador entregador) {
		if (!entregadores.contains(entregador)) {
			return entregadores.add(entregador);
		} else {
			return false;
		}
	}
	
	/**
	 * Remove um entregador da lista de entregadores do restaurante
	 * @param entregador a ser removido
	 * @return True caso o entregador tenha sido removido, False caso contrario
	 */
	public boolean removerEntregador(Entregador entregador) {
		return entregadores.remove(entregador);
	}
}
