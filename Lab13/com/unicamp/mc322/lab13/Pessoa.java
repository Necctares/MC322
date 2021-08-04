package com.unicamp.mc322.lab13;

import java.time.LocalDate;
import java.time.Period;
import com.unicamp.mc322.lab13.Exceptions.IllegalPersonArgumentException;

public class Pessoa {
	private String nome;
	private String cpf;
	private LocalDate idade;
	
	/**
	 * Cria uma nova pessoa
	 * @param nome da pessoa
	 * @param cpf da pessoa
	 * @param nascimento Data de Nascimento da pessoa
	 * @throws IllegalPersonArgumentException caso algum parametro seja invalido
	 */
	public Pessoa(String nome, String cpf, LocalDate nascimento) {
		this.nome = nome;
		if(cpf.length() != 12) {
			throw new IllegalPersonArgumentException("CPF invalido, utilize o formato xxxxxxxxx-xx");
		}
		this.cpf = cpf;
		idade = nascimento;
		if(retornarIdade() < 0) {
			throw new IllegalPersonArgumentException("Idade invalida, valor negativo!");
		}
	}
	
	/**
	 * 
	 * @return String com nome da pessoa
	 */
	public String retornarNome() {
		return nome;
	}
	
	/**
	 * 
	 * @return String com CPF da pessoa
	 */
	public String retornarCPF() {
		return cpf;
	}
	
	/**
	 * 
	 * @return Int contendo a idade da pessoa
	 */
	public int retornarIdade() {
		return Period.between(idade, LocalDate.now()).getYears();
	}
}
