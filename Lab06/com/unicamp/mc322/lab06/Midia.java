package com.unicamp.mc322.lab06;

public abstract class Midia {
	private String nome;
	private String autor;
	private double tempoDeDuracao;
	private double tamArmazenamento;

	Midia(String nome, String autor, double tempoDeDuracao, double tamArmazenamento) {
		this.nome = nome;
		this.autor = autor;
		this.tempoDeDuracao = tempoDeDuracao;
		this.tamArmazenamento = tamArmazenamento;
	}

	protected String retornarNome() {
		return nome;
	}

	protected String retornarAutor() {
		return autor;
	}

	protected void modificarAutor(String nomeDoAutor) {
		autor = nomeDoAutor;
		return;
	}

	protected void acrescentarTempoDeDuracao(double acrescimo) {
		tempoDeDuracao += acrescimo;
		return;
	}

	protected void acrescentarArmazenamento(double acrescimo) {
		tamArmazenamento += acrescimo;
		return;
	}

	protected double retornarTamanho() {
		return tamArmazenamento;
	}

	protected double retornarDuracao() {
		return tempoDeDuracao;
	}

	public void imprimirInfo() {
		System.out.printf("Nome: %s\nAutor: %s\nDuracao: %.2f minutos\nPeso total: %.2f MB\n", nome, autor,
				tempoDeDuracao / 60, tamArmazenamento);
		return;
	}
}
