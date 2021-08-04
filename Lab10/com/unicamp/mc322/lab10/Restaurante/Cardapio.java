package com.unicamp.mc322.lab10.Restaurante;

import java.util.*;

/**
 * Classe para gerenciar o cardapio
 *
 *
 */
public class Cardapio {
	private List<Lanche> cardapio;
	private Map<String, Double> desconto;

	/**
	 * Inicializa um novo cardapio.
	 */
	Cardapio() {
		cardapio = new ArrayList<Lanche>();
		desconto = new HashMap<String, Double>();
	}

	/**
	 * Imprime os itens no cardapio.
	 */
	void imprimirCardapio() {
		System.out.printf("Numero de Lanches disponiveis: %d || Cardapio de hoje:\n", cardapio.size());
		double preco;
		double descontoItem;
		for (int i = 0; i < cardapio.size(); i++) {
			cardapio.get(i).imprimirInfo();
			if (desconto.containsKey(cardapio.get(i).getId())) {
				descontoItem = desconto.get(cardapio.get(i).getId());
				if (descontoItem > 0) {
					preco = cardapio.get(i).getPreco() - (cardapio.get(i).getPreco() * (descontoItem / 100));
					System.out.printf(" R$ %.2f (Promocao! Preco normal: R$ %.2f)\n", preco,
							cardapio.get(i).getPreco());
				} else {
					preco = cardapio.get(i).getPreco() + descontoItem;
					System.out.printf(" R$ %.2f (Promocao! Preco normal: R$ %.2f)\n", preco,
							cardapio.get(i).getPreco());
				}
			} else {
				preco = cardapio.get(i).getPreco();
				System.out.printf(" R$ %.2f\n", preco);
			}
		}
		System.out.printf("\n");
		return;
	}
	
	/**
	 * Imprime as avaliações dos lanches contidas no cardapio atual
	 */
	void imprimirAvaliacoes() {
		for(Lanche atual : cardapio) {
			atual.imprimirAvaliacao();
		}
		return;
	}
	
	/**
	 * Retorna o valor de um item, caso tenha desconto, retorna o valor com desconto aplicado
	 * @param lanche para pesquisar o valor
	 * @return Double Valor do lanche
	 */
	double retornarValorItem(Lanche lanche) {
		double valor = 0;
		if (cardapio.contains(lanche)) {
			valor = lanche.getPreco();
			if (desconto.containsKey(lanche.getId())) {
				Double descontoItem = desconto.get(lanche.getId());
				if (descontoItem > 0) {
					valor = valor - (valor * (descontoItem / 100));
				} else {
					valor = valor + descontoItem;
				}
			}
		}
		return valor;
	}

	/**
	 * Checa se o lanche está contido no cardapio.
	 * 
	 * @param lanche que será checado.
	 * @return true se o lanche está no cardapio, false caso não esteja.
	 */
	private boolean checarSeExisteLanche(Lanche lanche) {
		boolean existe = false;
		for (int i = 0; i < cardapio.size(); i++) {
			if (cardapio.get(i).compararId(lanche)) {
				existe = true;
			}
		}
		return existe;
	}

	/**
	 * Adiciona um lanche no cardapio.
	 * 
	 * @param lanche que será adicionado.
	 * @return True se o lanche foi adicionado, False caso contrario.
	 */
	boolean adicionarAoCardapio(Lanche lanche) {
		if (checarSeExisteLanche(lanche)) {
			System.out.printf("Erro: Lanche ja existente.\n");
			return false;
		} else {
			cardapio.add(lanche);
			return true;
		}
	}

	/**
	 * Remove um lanche do cardapio.
	 * 
	 * @param lanche que será removido.
	 * @return True se o lanche foi removido, False caso contrario.
	 */
	boolean removerDoCardapio(Lanche lanche) {
		boolean achou = false;
		int i = 0;
		while (i < cardapio.size() && !achou) {
			if (cardapio.get(i).compararId(lanche)) {
				achou = true;
				cardapio.remove(i);
			}
			i += 1;
		}
		if (!achou) {
			System.out.printf("Erro: Item nao encontrado.\n");
		}
		return achou;
	}

	/**
	 * Aplica um desconto a um item do cardapio. Caso o tipo de desconto seja em
	 * Porcentagem, ele será armazenado como numero positivo, caso seja Monetario
	 * (valor fixo), será armazenado como um valor negativo. Ex: Desconto de 10% =>
	 * 10, Desconto de R$ 10.00 => -10.
	 * 
	 * @param id               do Item que será aplicado o desconto.
	 * @param descontoAplicado Valor do desconto aplicado.
	 * @param tipo             do desconto aplicado.
	 * @return True caso desconto foi aplicado com sucesso, False caso contrario.
	 */
	boolean aplicarDesconto(String id, double descontoAplicado, TipoDesconto tipo) {
		boolean aplicou = false;
		if (TipoDesconto.MONETARIO.equals(tipo) && descontoAplicado > 0) {
			desconto.put(id, -descontoAplicado);
			aplicou = true;
		} else if (TipoDesconto.PORCENTAGEM.equals(tipo) && descontoAplicado > 0) {
			desconto.put(id, descontoAplicado);
			aplicou = true;
		} else {
			System.out.printf("Erro: Desconto nao aplicavel.\n");
		}
		return aplicou;
	}

	/**
	 * Remove um desconto existente de um item do cardapio.
	 * 
	 * @param id do item que terá o desconto removido.
	 * @return True caso desconto tenha sido removido, False caso contrario.
	 */
	boolean removerDesconto(String id) {
		if (desconto.containsKey(id)) {
			desconto.remove(id);
			return true;
		} else {
			System.out.printf("Erro: nao foi possivel localizar nenhum desconto.\n");
			return false;
		}
	}
}
