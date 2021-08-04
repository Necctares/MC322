package com.unicamp.mc322.lab10.Pedido;

import java.util.*;

import com.unicamp.mc322.lab10.*;
import com.unicamp.mc322.lab10.Restaurante.*;

/**
 * Classe que gerencia e faz o intermedio entre as ações realizadas dos
 * Restaurantes, Entregadores e Clientes.
 *
 */
public class Pedido {
	private Cliente usuario;
	private Restaurante restaurante;
	private Entregador entregador;
	private List<Lanche> pedido;
	private StatusDoPedido status;
	private double valorTotal;
	private String comentario;
	private double avaliacaoMedia;
	private boolean ehParaEntrega;

	/**
	 * Cria um novo pedido
	 * @param usuario Cliente que fez o pedido
	 * @param restaurante Que irá receber o pedido
	 * @param entrega Boolean, True se o pedido é para entrega, False caso seja para retirada.
	 */
	public Pedido(Cliente usuario, Restaurante restaurante, boolean entrega) {
		this.restaurante = restaurante;
		this.usuario = usuario;
		pedido = new ArrayList<Lanche>();
		status = StatusDoPedido.NOVO;
		valorTotal = 0;
		ehParaEntrega = entrega;
		comentario = null;
		avaliacaoMedia = 0;
		entregador = null;
	}

	/**
	 * Adiciona um item ao pedido.
	 * 
	 * @param lanche que será adicionado.
	 */
	public void addItem(Lanche lanche) {
		pedido.add(lanche);
		return;
	}

	/**
	 * Remove um item do pedido.
	 * 
	 * @param lanche que será removido.
	 */
	public void removerItem(Lanche lanche) {
		if (!pedido.remove(lanche)) {
			System.out.printf("Erro: Lanche nao esta na lista.\n");
		}
		return;
	}

	/**
	 * Finaliza o pedido do cliente. O status do pedido mudará de NOVO para
	 * PREPARANDO.
	 * 
	 */
	public void fecharPedido() {
		valorTotal = restaurante.calcularValorPedido(pedido);
		if (ehParaEntrega) {
			Double distancia = Posicao.calcularDistancia(usuario.retornarPosicao(), restaurante.retornarPosicao());
			valorTotal += distancia * 0.5;
		}
		if (usuario.checarNovoCliente()) {
			valorTotal -= valorTotal * 0.2;
		}
		usuario.alterarPrimeiraCompra();
		status = StatusDoPedido.alterarStatus(status, ehParaEntrega);
		restaurante.receberPedido(this);
		return;
	}

	/**
	 * Imprime um resumo do pedido.
	 */
	public void imprimirPedido() {
		usuario.imprimirInfo();
		for (int i = 0; i < pedido.size(); i++) {
			System.out.printf("- %s\n", pedido.get(i).getId());
		}
		StatusDoPedido.imprimirStatus(status);
		System.out.printf("Valor Total: R$ %.2f\n", valorTotal);
		if (status.equals(StatusDoPedido.ENTREGUE)) {
			System.out.printf("Avaliacao do pedido (Media total): %.2f\t", avaliacaoMedia);
			if (comentario != null) {
				System.out.printf("Comentario do cliente: %s\n", comentario);
			}
			System.out.println();
		}
		return;
	}

	/**
	 * Muda o Status atual do lanche: PREPARANDO => PRONTOPARAENTREGA/PRONTOPARARETIRAR
	 * 
	 * @return True caso modifique o status, caso contrario retorna False.
	 */
	public boolean aprontarPedido() {
		if (status.equals(StatusDoPedido.PREPARANDO)) {
			status = StatusDoPedido.alterarStatus(status, ehParaEntrega);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Atribui um entregador ao pedido atual
	 * @param entregador que irá entregar o pedido
	 * @return True caso o pedido tenha sido atribuido ao entregador corretamente, False caso contrario.
	 */
	public boolean atribuirEntregador(Entregador entregador) {
		if (status.equals(StatusDoPedido.PRONTOPARAENTREGA)) {
			this.entregador = entregador;
			status = StatusDoPedido.alterarStatus(status, ehParaEntrega);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Finaliza a entrega do pedido atual
	 * @return True caso tenha sido finalizado corretamente, False caso contrario.
	 */
	public boolean finalizarEntrega() {
		if (status.equals(StatusDoPedido.SAIUPARAENTREGA) || status.equals(StatusDoPedido.PRONTOPARARETIRAR)) {
			status = StatusDoPedido.alterarStatus(status, ehParaEntrega);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Cancela o pedido atual
	 * @return True caso tenha sido cancelado, False caso contrario
	 */
	public boolean cancelarPedido() {
		if (status.equals(StatusDoPedido.NOVO) || status.equals(StatusDoPedido.PREPARANDO)) {
			status = StatusDoPedido.CANCELADO;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Avalia o pedido atual (Pedido tem que ter o status de ENTREGUE) com notas entre 0-5
	 * @param avaliacaoLanche List(Integer) contendo o mesmo numero de elementos que o pedido (lanches) possui.
	 * @param avaliacaoRestaurante Int contendo o valor da avaliação do restaurante
	 * @param avaliacaoEntregador Integer contendo o valor da avaliação do entregador, caso seja retirada passar valor null
	 * @param comentario do Cliente sobre o pedido
	 * @return True caso a avaliacao tenha sido bem sucedida, False caso contrario.
	 */
	public boolean avaliarPedido(List<Integer> avaliacaoLanche, int avaliacaoRestaurante, Integer avaliacaoEntregador,
			String comentario) {
		int avaliacao = 0;
		int contador = 0;
		if (status.equals(StatusDoPedido.ENTREGUE)) {
			if (avaliacaoLanche.size() == pedido.size()) {
				for (int i = 0; i < pedido.size(); i++) {
					pedido.get(i).receberAvaliacao(avaliacaoLanche.get(i));
					avaliacao += avaliacaoLanche.get(i);
					contador += 1;
				}
			}
			restaurante.receberAvaliacao(avaliacaoRestaurante);
			avaliacao += avaliacaoRestaurante;
			contador += 1;
			if (avaliacaoEntregador != null && entregador != null) {
				entregador.receberAvaliacao(avaliacaoEntregador.intValue());
				avaliacao += avaliacaoEntregador.intValue();
				contador += 1;
			}
			avaliacaoMedia = (double) avaliacao / contador;
			this.comentario = comentario;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checa se o pedido é para entrega
	 * @return True caso seja para entrega, False caso contrario
	 */
	public boolean checarEntrega() {
		return ehParaEntrega;
	}
}
