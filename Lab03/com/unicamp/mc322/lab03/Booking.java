package com.unicamp.mc322.lab03;

import java.util.*;

public class Booking {
	List<Reserva> reservas;

	Booking() {
		reservas = new ArrayList<Reserva>();
	}

	public static void main(String[] args) {
		Booking catalogo = new Booking();
		Hotel tropical = new Hotel("Praia Tropical", "Rua Tajubá, 201 - Florianopolis, SC", "3225-8997", 100, 900, 100,
				10);
		Hotel florestal = new Hotel("Campo Florestal", "Rua Monteiro, 456 - Goiânia, GO", "3654-8974", 50, 2000, 100,
				10);
		User roberci = new User("Roberci Silva", "784245698-21", "12/04/1996", "Masculino", 1000, true);
		User marcela = new User("Marcela Domingues", "269784061-45", "22/07/1998", "Feminino", 2000, false);
		catalogo.criarReserva(roberci, tropical, 2, 1);
		catalogo.criarReserva(marcela, florestal, 13, 4);
		catalogo.criarReserva(roberci, tropical, 87, 1);
		catalogo.cancelarReserva(marcela, tropical, 22);
		catalogo.criarReserva(roberci, florestal, 99, 1);
		catalogo.cancelarReserva(roberci, florestal, 99);
		catalogo.criarReserva(marcela, florestal, 87, 1);
		System.out.printf("\n\n");
		tropical.mostrarStatus();
		System.out.printf("\n\n");
		florestal.mostrarStatus();
	}

	void criarReserva(User hospede, Hotel hotel, int numQuarto, int diasReserva) {
		if (hotel.consultarDisponibilidade(numQuarto, hospede.consultarFumante())) {
			float valorDaReserva = diasReserva * hotel.consultarDiaria(numQuarto);
			if (valorDaReserva > hospede.consultarSaldo()) {
				System.out.printf("Saldo insuficiente, reserva não realizada.\n");
			} else {
				hotel.alterarReserva(numQuarto);
				hospede.decrementarSaldo(valorDaReserva);
				Reserva novaReserva = new Reserva(hospede.consultarNome(), hotel.consultarNome(), diasReserva,
						numQuarto, valorDaReserva);
				reservas.add(novaReserva);
				System.out.printf("Reserva concluida com sucesso! Custo total da reserva: %.2f\n", valorDaReserva);
			}
		} else {
			System.out.printf("Não foi possivel realizar reserva. Quarto não disponivel.\n");
		}
	}

	void cancelarReserva(User hospede, Hotel hotel, int numQuarto) {
		for (int i = 0; i < reservas.size(); i++) {
			if (reservas.get(i).checarReserva(hospede.consultarNome(), hotel.consultarNome(), numQuarto)) {
				float reembolso = reservas.get(i).checarValor() * (float) 0.7;
				hotel.alterarReserva(numQuarto);
				hospede.incrementarSaldo(reembolso);
				reservas.remove(i);
				System.out.printf("Reserva cancelada com sucesso. Valor devolvido: %.2f\n", reembolso);
				return;
			}
		}
		System.out.printf("O cancelamento falhou, reserva não encontrada.\n");
		return;
	}

}

class Reserva {
	private int numDias;
	private String nomeHospede;
	private String nomeDoHotel;
	private int numQuarto;
	private float valorTotal;

	Reserva(String hospede, String hotel, int diasReserva, int numQuarto, float valor) {
		numDias = diasReserva;
		nomeHospede = hospede;
		nomeDoHotel = hotel;
		this.numQuarto = numQuarto;
		valorTotal = valor;
	}

	Boolean checarReserva(String hospede, String hotel, int quarto) {
		if (hospede == nomeHospede && hotel == nomeDoHotel && numQuarto == quarto) {
			return true;
		} else {
			return false;
		}
	}

	float checarValor() {
		return valorTotal;
	}
}