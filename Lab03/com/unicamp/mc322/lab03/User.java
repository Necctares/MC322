package com.unicamp.mc322.lab03;

public class User {
	private String nome;
	private String cpf;
	private String nascimento;
	private String genero;
	private float saldo;
	private Boolean fumante;
	
	User(String nome, String cpf, String nascimento, String genero, float saldo, Boolean fumante){
		this.nome = nome;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.genero = genero;
		this.saldo = saldo;
		this.fumante = fumante;
	}
	
	float consultarSaldo() {
		return saldo;
	}

	Boolean consultarFumante() {
		return fumante;
	}
	
	String consultarNome() {
		return nome;
	}
	
	void incrementarSaldo(float montante) {
		if (montante < 0) {
			System.out.printf("Operação invalida.\n");
		} else {
			saldo += montante;
		}
	}

	void decrementarSaldo(float montante) {
		if (montante > saldo || montante < 0) {
			System.out.printf("Operação invalida.\n");
		} else {
			saldo -= montante;
		}
	}

	void imprimirInfo() {
		System.out.printf("Cliente: %s\nCpf: %s\nData de Nascimento: %s\nGenero: %s\nFuma: %b\nSaldo: %.2f\n", nome, cpf, nascimento, genero, fumante, saldo);
	}
}
