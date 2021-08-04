package com.unicamp.mc322.lab09;

public class Pessoa {
	private String nome;
	private String idade;
	private String cpf;

	Pessoa(String nome, String idade, String cpf) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
}
