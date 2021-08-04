package com.unicamp.mc322.lab04;

import java.util.*;

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
	 */
	void adicionarAoCardapio(Lanche lanche) {
		if (checarSeExisteLanche(lanche)) {
			System.out.printf("Erro: Lanche ja existente.\n");
		} else {
			cardapio.add(lanche);
		}
		return;
	}

	/**
	 * Remove um lanche do cardapio.
	 * 
	 * @param lanche que será removido.
	 */
	void removerDoCardapio(Lanche lanche) {
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
		return;
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
	 */
	void aplicarDesconto(String id, double descontoAplicado, TipoDesconto tipo) {
		if (TipoDesconto.MONETARIO.equals(tipo) && descontoAplicado > 0) {
			desconto.put(id, -descontoAplicado);
		} else if (TipoDesconto.PORCENTAGEM.equals(tipo) && descontoAplicado > 0) {
			desconto.put(id, descontoAplicado);
		} else {
			System.out.printf("Erro: Desconto nao aplicavel.\n");
		}
		return;
	}

	/**
	 * Remove um desconto existente de um item do cardapio.
	 * 
	 * @param id do item que terá o desconto removido.
	 */
	void removerDesconto(String id) {
		if (desconto.containsKey(id)) {
			desconto.remove(id);
		} else {
			System.out.printf("Erro: nao foi possivel localizar nenhum desconto.\n");
		}
		return;
	}

	/**
	 * @param id do item que terá o desconto checado.
	 * @return Retorna o valor do desconto do item presente no cardapio. Retorna um
	 *         valor positivo caso o desconto seja em porcentagem. Ex: Desconto de
	 *         10% retornará 10. Retorna um valor negativo caso o desconto seja um
	 *         valor fixo (Monetario). Ex: Desconto de R$ 10 retornará -10. Caso não
	 *         tenha nenhum desconto o valor retornado será 0.
	 */
	double retornarDesconto(String id) {
		double descontoItem = 0;
		if (desconto.containsKey(id)) {
			descontoItem = desconto.get(id);
		}
		return descontoItem;
	}
}
