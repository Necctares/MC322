package com.unicamp.mc322.lab04;

import java.util.*;

public class Pidao {
	private String nome;
	private String cnpj;
	private Posicao localizacao;
	private Cardapio cardapio;
	private List<Pedido> pedidos;
	private Map<String, User> clientes;

	/**
	 * Inicializa um novo app Pidao.
	 * @param nome do restaurante.
	 * @param cnpj do restaurante.
	 * @param x coordenada da localização.
	 * @param y coordenada da localização.
	 */
	Pidao(String nome, String cnpj, int x, int y) {
		this.nome = nome;
		this.cnpj = cnpj;
		localizacao = new Posicao(x, y);
		cardapio = new Cardapio();
		pedidos = new ArrayList<Pedido>();
		clientes = new HashMap<String, User>();
	}

	/**
	 * Função para cadastrar um novo usuario. Caso o cpf já esteja cadastrado,
	 * retorna NULL. Caso contrario, retorna User.
	 * 
	 * @param x    Valor da coordenada x (Posição)
	 * @param y    Valor da coordenada y (Posição)
	 * @param cpf  do novo Usuario
	 * @param nome completo do novo Usuario
	 */
	public User cadastrarUsuario(String nome, String cpf, int x, int y) {
		if (clientes.containsKey(cpf)) {
			System.out.printf("Erro: Cliente ja cadastrado.\n");
			return null;
		} else {
			Posicao posicaoCliente = new Posicao(x, y);
			User novoCliente = new User(nome, cpf, posicaoCliente);
			clientes.put(cpf, novoCliente);
			return novoCliente;
		}
	}

	/**
	 * Aplica um desconto ao item do cardapio com o referido id, com base no tipo de
	 * desconto (Monetario ou Porcentagem). Caso já exista um desconto aplicado, o
	 * valor será substituido pelo novo.
	 * 
	 * @param id               do item no cardapio.
	 * @param descontoAplicado valor do desconto. (Valor > 0)
	 * @param desconto         Tipo do desconto que será aplicado. Valores
	 *                         absolutos, Ex: 10% => 10 e R$ 10.00 => 10.
	 */
	public void aplicarDesconto(String id, double descontoAplicado, TipoDesconto desconto) {
		cardapio.aplicarDesconto(id, descontoAplicado, desconto);
		return;
	}

	/**
	 * Remove um desconto existente a um item do cardapio.
	 * 
	 * @param id do Item contido no cardapio.
	 */
	public void removerDesconto(String id) {
		cardapio.removerDesconto(id);
		return;
	}

	/**
	 * Adiciona um lanche ao cardapio.
	 * 
	 * @param lanche que será adicionado.
	 */
	public void adicionarAoCardapio(Lanche lanche) {
		cardapio.adicionarAoCardapio(lanche);
		return;
	}

	/**
	 * Remove um lanche do cardapio
	 * 
	 * @param lanche que será removido.
	 */
	public void removerDoCardapio(Lanche lanche) {
		cardapio.removerDoCardapio(lanche);
		return;
	}

	/**
	 * Cancela um pedido já existente. O pedido só poderá ser cancelado caso seu
	 * status seja NOVO ou PREPARANDO.
	 * 
	 * @param pedido que será cancelado.
	 */
	public void cancelarPedido(Pedido pedido) {
		if (pedidos.contains(pedido)) {
			if (pedido.verificarSeOStatusEhIgual(StatusDoPedido.NOVO)
					|| pedido.verificarSeOStatusEhIgual(StatusDoPedido.PREPARANDO)) {
				pedidos.remove(pedido);
			} else {
				System.out.printf("Erro: Pedido nao pode ser cancelado.\n");
			}
		} else {
			System.out.printf("Erro: Pedido nao encontrado.\n");
		}
		return;
	}

	/**
	 * Faz a confirmação de um pedido.
	 * 
	 * @param pedido a ser confirmado.
	 */
	public void fazerPedido(Pedido pedido) {
		if (clientes.containsKey(pedido.retornarIdCliente())) {
			List<Lanche> novoPedido = pedido.retornarPedidos();
			double valorTotal = 0;
			double desconto;
			for (int i = 0; i < novoPedido.size(); i++) {
				desconto = cardapio.retornarDesconto(novoPedido.get(i).getId());
				if (desconto > 0) {
					valorTotal += novoPedido.get(i).getPreco() - (novoPedido.get(i).getPreco() * (desconto / 100));
				} else if (desconto < 0) {
					valorTotal += novoPedido.get(i).getPreco() + desconto;
				} else {
					valorTotal += novoPedido.get(i).getPreco();
				}
			}
			if (clientes.get(pedido.retornarIdCliente()).checarNovoCliente()) {
				valorTotal = valorTotal - (valorTotal * 0.2);
				clientes.get(pedido.retornarIdCliente()).adicionarCompra();
			} else if (clientes.get(pedido.retornarIdCliente()).checarSeAtingiuMetaDeCompras()) {
				if (valorTotal > 60) {
					valorTotal -= 60;
				} else {
					valorTotal = 0;
				}
				clientes.get(pedido.retornarIdCliente()).resetarCompras();
			} else if (valorTotal > 100) {
				valorTotal = valorTotal - (valorTotal * 0.1);
				clientes.get(pedido.retornarIdCliente()).adicionarCompra();
			} else {
				clientes.get(pedido.retornarIdCliente()).adicionarCompra();
			}
			pedido.fecharPedido(valorTotal);
			pedidos.add(pedido);
		} else {
			System.out.printf("Erro: Cliente nao cadastrado.\n");
		}
		return;
	}

	/**
	 * Modifica o Status de um pedido respeitando o ciclo de fases: NOVO =>
	 * PREPARANDO => SAIUPARAENTREGA => ENTREGUE.
	 * 
	 * @param pedido que será atualizado.
	 */
	public void aprontarPedido(Pedido pedido) {
		pedido.aprontarPedido();
		return;
	}

	/**
	 * Imprime o cardapio do restaurante.
	 */
	public void imprimirCardapio() {
		System.out.printf("Restaurante %s\n(CNPJ: %s)\n\n", nome, cnpj);
		cardapio.imprimirCardapio();
		return;
	}

	/**
	 * Imprime o Resumo dos pedidos atuais do restaurante.
	 */
	public void imprimirResumoPedidos() {
		System.out.printf("Existem %d pedidos:\n", pedidos.size());
		for (int i = 0; i < pedidos.size(); i++) {
			System.out.printf("==========================================================\n");
			pedidos.get(i).imprimirPedido();
			System.out.printf("==========================================================\n");
		}
		return;
	}
}
