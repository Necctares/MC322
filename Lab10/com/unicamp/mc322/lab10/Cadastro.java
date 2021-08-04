package com.unicamp.mc322.lab10;

import java.util.Map;
import java.util.HashMap;
import com.unicamp.mc322.lab10.Restaurante.*;

/**
 * Classe para armazenar os cadastros dos Clientes, Entregadores e Restaurantes.
 *
 *
 */
public class Cadastro {
	private Map<String, Cliente> clientes;
	private Map<String, Entregador> entregadores;
	private Map<String, Restaurante> restaurantes;

	/**
	 * Cria um novo banco de Cadastro
	 */
	public Cadastro() {
		clientes = new HashMap<String, Cliente>();
		entregadores = new HashMap<String, Entregador>();
		restaurantes = new HashMap<String, Restaurante>();
	}

	/**
	 * Cadastra um novo restaurante
	 * @param nome do restaurante
	 * @param cnpj do restaurante
	 * @param endereco (Posicao) do restaurante
	 * @return Restaurante caso o cadastro tenha sido bem sucedido, null caso contrario
	 */
	public Restaurante cadastrarRestaurante(String nome, String cnpj, Posicao endereco) {
		if (!restaurantes.containsKey(cnpj)) {
			Restaurante novo = new Restaurante(nome, cnpj, endereco);
			restaurantes.put(cnpj, novo);
			return novo;
		} else {
			return null;
		}
	}

	/**
	 * Remove um restaurante do cadastro
	 * @param cnpj do restaurante
	 * @return Restaurante removido, null caso não tenha sido possivel remover
	 */
	public Restaurante removerRestaurante(String cnpj) {
		return restaurantes.remove(cnpj);
	}

	/**
	 * Cadastra um novo cliente
	 * @param nome do cliente
	 * @param cpf do cliente
	 * @param endereco (Posicao) do cliente
	 * @return Cliente caso cadastro tenha sido bem sucedido, null caso contrario
	 */
	public Cliente cadastrarCliente(String nome, String cpf, Posicao endereco) {
		if (!clientes.containsKey(cpf)) {
			Cliente novo = new Cliente(nome, cpf, endereco);
			clientes.put(cpf, novo);
			return novo;
		} else {
			return null;
		}
	}

	/**
	 * Remove um Cliente do cadastro
	 * @param cpf do cliente
	 * @return Cliente que foi removido, null caso não tenha sido removido
	 */
	public Cliente removerCliente(String cpf) {
		return clientes.remove(cpf);
	}

	/**
	 * Cadastra um novo entregador
	 * @param nome do entregador
	 * @param cpf do entregador
	 * @return Entregador que foi cadastrado, null caso não tenha sido possivel cadastrar
	 */
	public Entregador cadastrarEntregador(String nome, String cpf) {
		if (!entregadores.containsKey(cpf)) {
			Entregador novo = new Entregador(nome, cpf);
			entregadores.put(cpf, novo);
			return novo;
		} else {
			return null;
		}
	}
	
	/**
	 * Remove um entregador
	 * @param cpf do entregador
	 * @return Entregador removido, null caso não tenha sido possivel remover
	 */
	public Entregador removerEntregador(String cpf) {
		return entregadores.remove(cpf);
	}
	
	/**
	 * Imprime um resumo dos pedidos de todos os restaurantes cadastrados
	 */
	public void imprimirResumoPedidos() {
		for(Restaurante atual : restaurantes.values()) {
			atual.imprimirPedidos();
			System.out.println();
		}
		return;
	}
	
	/**
	 * Imprime o cardapio de todos os restaurantes cadastrados
	 */
	public void imprimirTodosCardapios() {
		for(Restaurante atual : restaurantes.values()) {
			atual.imprimirCardapio();
			System.out.println();
		}
		return;
	}
	
	/**
	 * Imprime todas as avaliacoes feitas aos Restaurantes, Lanches e Entregadores.
	 */
	public void imprimirAvaliacoes() {
		for(Restaurante atual : restaurantes.values()) {
			atual.imprimirAvaliacao();
			System.out.println();
		}
		System.out.printf("\nAvaliacao dos entregadores:\n");
		for(Entregador atual : entregadores.values()) {
			atual.imprimirInfo();
		}
		return;
	}
}
