package com.unicamp.mc322.lab05;

public class Pessoa {
	private String nome;
	private String email;
	private String telefone;

	Pessoa(String nome, String email, String telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}

	public boolean checarNome(String nome) {
		return this.nome.equalsIgnoreCase(nome);
	}

	public boolean checarEmail(String email) {
		return this.email.equalsIgnoreCase(email);
	}

	public boolean checarTelefone(String telefone) {
		return this.telefone.equalsIgnoreCase(telefone);
	}

	void imprimirDados() {
		System.out.printf("Nome: %s || Email: %s || Telefone: %s\n", nome, email, telefone);
	}
}
