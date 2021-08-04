package com.unicamp.mc322.lab03;

import java.util.*;

public class Hotel {
	private String nome;
	private String endereco;
	private String telefone;
	private List<Room> quartos;
	private float diariaNormal;
	private float diariaVIP;

	String consultarNome() {
		return nome;
	}

	Hotel(String nome, String endereco, String telefone, float diariaNormal, float diariaVip, int totalQuartos,
			int numQuartosVip) {
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.diariaNormal = diariaNormal;
		this.diariaVIP = diariaVip;
		quartos = new ArrayList<Room>();
		int i;
		if (numQuartosVip > totalQuartos) {
			System.out.printf(
					"Erro, numeros de Quartos vips excedeu a quantidade maxima de quartos permitidos neste hotel.\nO numero de quartos vips será o numero total de quartos.\n");
			numQuartosVip = totalQuartos;
		}
		for (i = 0; i < numQuartosVip; i++) {
			Room novoQuarto = new Room(true, true, true);
			quartos.add(novoQuarto);
		}
		for (; i < totalQuartos; i++) {
			Room novoQuarto = new Room(false, false, false);
			quartos.add(novoQuarto);
		}
	}

	// Consulta a disponibilidade de quartos.
	void consultarQuartos() {
		int contador = 0;
		System.out.printf("Os quartos disponiveis para reserva são:\n");
		for (int i = 0; i < quartos.size(); i++) {
			if (!quartos.get(i).checarVaga()) {
				contador += 1;
				System.out.printf("|%d|", i + 1);
			}
		}
		if (contador > 0) {
			System.out.printf("\nEstes são os quartos disponiveis. Total de vagas: %d\n", contador);
		} else {
			System.out.printf("Não há nenhum quarto disponivel.\n");
		}
		return;
	}

	// Consulta quais quartos são quartos para fumante.
	void consultarFumante() {
		Boolean fumante = false;
		System.out.printf("Os quartos para fumante são:\n");
		for (int i = 0; i < quartos.size(); i++) {
			if (quartos.get(i).checarFumante()) {
				fumante = true;
				System.out.printf("|%d|", i + 1);
			}
		}
		if (fumante) {
			System.out.printf("\nEstes são os quartos para fumantes.\n");
		} else {
			System.out.printf("Não há nenhum quarto para fumante.\n");
		}
		return;
	}

	// Consulta quais quartos possuem ar condicionado.
	void consultarAr() {
		Boolean ar = false;
		System.out.printf("Os quartos com ar condicionado são:\n");
		for (int i = 0; i < quartos.size(); i++) {
			if (quartos.get(i).checarAr()) {
				ar = true;
				System.out.printf("|%d|", i + 1);
			}
		}
		if (ar) {
			System.out.printf("\nEstes são os quartos com ar condicionado.\n");
		} else {
			System.out.printf("Não há nenhum quarto com ar condicionado.\n");
		}
		return;
	}

	void consultarVIP() {
		Boolean vip = false;
		System.out.printf("Os quartos VIPS são:\n");
		for (int i = 0; i < quartos.size(); i++) {
			if (quartos.get(i).checarVIP()) {
				vip = true;
				System.out.printf("|%d|", i + 1);
			}
		}
		if (vip) {
			System.out.printf("\nEstes são os quartos VIPS.\n");
		} else {
			System.out.printf("Não há nenhum quarto disponivel para fumante.\n");
		}
		return;
	}

	float consultarDiaria(int numeroDoQuarto) {
		if (numeroDoQuarto < 1 || numeroDoQuarto > quartos.size()) {
			System.out.printf("Quarto não existente.\n");
			return -1;
		} else {
			if (quartos.get(numeroDoQuarto - 1).checarVIP()) {
				return diariaVIP;
			} else {
				return diariaNormal;
			}
		}
	}

	Boolean consultarDisponibilidade(int numQuarto, Boolean fumante) {
		if (!quartos.get(numQuarto - 1).checarVaga()) {
			if (fumante) {
				if (quartos.get(numQuarto - 1).checarFumante()) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	void alterarReserva(int numQuarto) {
		quartos.get(numQuarto - 1).mudarStatus();
	}

	void mostrarStatus() {
		System.out.printf("::Hotel:: %s ::Hotel::\n%s\nTel: %s\nDiarias:\nQuarto Comum: %.2f\nQuarto VIP: %.2f\n", nome,
				endereco, telefone, diariaNormal, diariaVIP);
		this.consultarQuartos();
		this.consultarVIP();
		this.consultarFumante();
		this.consultarAr();
	}
}
