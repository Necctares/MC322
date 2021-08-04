package com.unicamp.mc322.lab05;

import java.time.*;
import java.util.*;

public class Agenda {
	private List<LembreteComum> lembretesComuns;
	private Map<Month, List<LembreteMensal>> lembretesMensais;
	private Map<DayOfWeek, List<LembreteSemanal>> lembretesSemanais;
	private Map<String, List<Evento>> eventos;
	private Map<String, List<Reuniao>> reunioes;

	Agenda() {
		lembretesComuns = new ArrayList<LembreteComum>();
		lembretesMensais = new HashMap<Month, List<LembreteMensal>>();
		lembretesSemanais = new HashMap<DayOfWeek, List<LembreteSemanal>>();
		eventos = new HashMap<String, List<Evento>>();
		reunioes = new HashMap<String, List<Reuniao>>();
	}

	public void cancelarLembrete(String lembrete, TipoLembrete tipo) {
		switch (tipo) {
		case LembreteComum:
			for (Lembrete lembreteAtual : lembretesComuns) {
				if (lembreteAtual.checarDescricao(lembrete)) {
					lembretesComuns.remove(lembreteAtual);
					return;
				}
			}
			break;
		case LembreteMensal:
			for (List<LembreteMensal> listaAtual : lembretesMensais.values()) {
				for (Lembrete lembreteAtual : listaAtual) {
					if (lembreteAtual.checarDescricao(lembrete)) {
						listaAtual.remove(lembreteAtual);
						return;
					}
				}
			}
			break;
		case LembreteSemanal:
			for (List<LembreteSemanal> listaAtual : lembretesSemanais.values()) {
				for (Lembrete lembreteAtual : listaAtual) {
					if (lembreteAtual.checarDescricao(lembrete)) {
						listaAtual.remove(lembreteAtual);
						return;
					}
				}
			}
			break;
		case Evento:
			for (List<Evento> listaAtual : eventos.values()) {
				for (Lembrete lembreteAtual : listaAtual) {
					if (lembreteAtual.checarDescricao(lembrete)) {
						listaAtual.remove(lembreteAtual);
						return;
					}
				}
			}
			break;
		case Reuniao:
			for (List<Reuniao> listaAtual : reunioes.values()) {
				for (Lembrete lembreteAtual : listaAtual) {
					if (lembreteAtual.checarDescricao(lembrete)) {
						listaAtual.remove(lembreteAtual);
						return;
					}
				}
			}
			break;
		default:
			System.out.printf("Erro: Tipo desconhecido.\n");
		}
		System.out.printf("Nao foi encontrado nenhum lembrete do tipo passado.\n");
		return;
	}

	public void adicionarLembreteComum(String lembrete) {
		LembreteComum novoLembrete = new LembreteComum(lembrete);
		lembretesComuns.add(novoLembrete);
		return;
	}

	public void adicionarLembreteSemanal(String lembrete, List<DayOfWeek> dias) {
		for (DayOfWeek diaAtual : dias) {
			LembreteSemanal novoLembrete = new LembreteSemanal(lembrete, diaAtual);
			if (lembretesSemanais.containsKey(diaAtual)) {
				lembretesSemanais.get(diaAtual).add(novoLembrete);
			} else {
				List<LembreteSemanal> novaListaLembrete = new ArrayList<LembreteSemanal>();
				novaListaLembrete.add(novoLembrete);
				lembretesSemanais.put(diaAtual, novaListaLembrete);
			}
		}
		return;
	}

	public void adicionarLembreteMensal(String lembrete, Month mes) {
		LembreteMensal novoLembrete = new LembreteMensal(lembrete, mes);
		if (lembretesMensais.containsKey(mes)) {
			lembretesMensais.get(mes).add(novoLembrete);
		} else {
			List<LembreteMensal> novaListaLembrete = new ArrayList<LembreteMensal>();
			novaListaLembrete.add(novoLembrete);
			lembretesMensais.put(mes, novaListaLembrete);
		}
		return;
	}

	public void adicionarEvento(Evento evento) {
		if (eventos.containsKey(evento.retornarHash())) {
			eventos.get(evento.retornarHash()).add(evento);
		} else {
			List<Evento> novaListaEvento = new ArrayList<Evento>();
			novaListaEvento.add(evento);
			eventos.put(evento.retornarHash(), novaListaEvento);
		}
		return;
	}

	public void adicionarReuniao(Reuniao reuniao) {
		if (reunioes.containsKey(reuniao.retornarHash())) {
			reunioes.get(reuniao.retornarHash()).add(reuniao);
		} else {
			List<Reuniao> novaListaReuniao = new ArrayList<Reuniao>();
			novaListaReuniao.add(reuniao);
			reunioes.put(reuniao.retornarHash(), novaListaReuniao);
		}
		return;
	}

	public void imprimirLembretesDoDia() {
		LocalDate dataAtual = LocalDate.now();
		String hashEvento = "" + dataAtual.getDayOfMonth() + dataAtual.getMonth().toString() + dataAtual.getYear();
		System.out.printf("****||Lembretes do Dia||****\n");
		for (LembreteComum lembreteAtual : lembretesComuns) {
			lembreteAtual.imprimirLembrete();
		}
		if (lembretesSemanais.containsKey(dataAtual.getDayOfWeek())) {
			for (LembreteSemanal lembreteAtual : lembretesSemanais.get(dataAtual.getDayOfWeek())) {
				lembreteAtual.imprimirLembrete();
			}
		}
		if (lembretesMensais.containsKey(dataAtual.getMonth())) {
			for (LembreteMensal lembreteAtual : lembretesMensais.get(dataAtual.getMonth())) {
				lembreteAtual.imprimirLembrete();
			}
		}
		if (eventos.containsKey(hashEvento)) {
			for (Evento lembreteAtual : eventos.get(hashEvento)) {
				lembreteAtual.imprimirLembrete();
			}
		}
		if (reunioes.containsKey(hashEvento)) {
			for (Reuniao lembreteAtual : reunioes.get(hashEvento)) {
				lembreteAtual.imprimirLembrete();
			}
		}
		System.out.printf("****||%s||****\n\n", dataAtual.toString());
		return;
	}

	public void imprimirLembretesDoDia(LocalDate diaInformado) {
		LocalDate dataAtual = diaInformado;
		String hashEvento = "" + dataAtual.getDayOfMonth() + dataAtual.getMonth().toString() + dataAtual.getYear();
		System.out.printf("****||Lembretes do Dia||****\n");
		for (LembreteComum lembreteAtual : lembretesComuns) {
			lembreteAtual.imprimirLembrete();
		}
		if (lembretesSemanais.containsKey(dataAtual.getDayOfWeek())) {
			for (LembreteSemanal lembreteAtual : lembretesSemanais.get(dataAtual.getDayOfWeek())) {
				lembreteAtual.imprimirLembrete();
			}
		}
		if (lembretesMensais.containsKey(dataAtual.getMonth())) {
			for (LembreteMensal lembreteAtual : lembretesMensais.get(dataAtual.getMonth())) {
				lembreteAtual.imprimirLembrete();
			}
		}
		if (eventos.containsKey(hashEvento)) {
			for (Evento lembreteAtual : eventos.get(hashEvento)) {
				lembreteAtual.imprimirLembrete();
			}
		}
		if (reunioes.containsKey(hashEvento)) {
			for (Reuniao lembreteAtual : reunioes.get(hashEvento)) {
				lembreteAtual.imprimirLembrete();
			}
		}
		System.out.printf("****||%s||****\n\n", dataAtual.toString());
		return;
	}

	public void imprimirLembretesDoPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
		System.out.printf("Lembretes Comuns durante o periodo:\n");
		for (LembreteComum lembreteAtual : lembretesComuns) {
			lembreteAtual.imprimirLembrete();
		}
		LocalDate dataAtual = dataInicial;
		int contarDias = 0;
		System.out.printf("Lembretes Semanais durante o periodo:\n");
		while (dataAtual.isBefore(dataFinal) && contarDias < 7) {
			contarDias += 1;
			if (lembretesSemanais.containsKey(dataAtual.getDayOfWeek())) {
				for (LembreteSemanal lembreteAtual : lembretesSemanais.get(dataAtual.getDayOfWeek())) {
					lembreteAtual.imprimirLembrete();
				}
			}
			dataAtual = dataAtual.plusDays(1);
		}
		System.out.printf("Lembretes Mensais durante o periodo:\n");
		dataAtual = dataInicial;
		int contarMeses = 0;
		while (dataAtual.isBefore(dataFinal) && contarMeses < 12) {
			contarMeses += 1;
			if (lembretesMensais.containsKey(dataAtual.getMonth())) {
				for (LembreteMensal lembreteAtual : lembretesMensais.get(dataAtual.getMonth())) {
					lembreteAtual.imprimirLembrete();
				}
			}
			dataAtual = dataAtual.plusMonths(1);
		}
		for (dataAtual = dataInicial; dataAtual.isBefore(dataFinal); dataAtual = dataAtual.plusDays(1)) {
			String hashEvento = "" + dataAtual.getDayOfMonth() + dataAtual.getMonth().toString() + dataAtual.getYear();
			if (eventos.containsKey(hashEvento)) {
				System.out.printf("\n****||Eventos do Dia: %s||****\n", dataAtual.toString());
				for (Evento lembreteAtual : eventos.get(hashEvento)) {
					lembreteAtual.imprimirLembrete();
				}
			}
			if (reunioes.containsKey(hashEvento)) {
				System.out.printf("\n****||Reunioes do Dia: %s||****\n", dataAtual.toString());
				for (Reuniao lembreteAtual : reunioes.get(hashEvento)) {
					lembreteAtual.imprimirLembrete();
				}
			}
		}
		System.out.printf("****||Periodo: %s ate %s ||****\n\n", dataInicial.toString(), dataFinal.toString());
		return;
	}

	public static void main(String[] args) {
		
		LocalDate dataAtual = LocalDate.now();
		
		Lembrete lembrete1 = new Lembrete("Comprar carne.", dataAtual);
		lembrete1.imprimirLembrete();
		System.out.println();
		
		Agenda novaAgenda = new Agenda();
		novaAgenda.adicionarLembreteComum("Dar comida ao cachorro");
		novaAgenda.adicionarLembreteComum("Dar comida ao gato");
		
		DayOfWeek[] comidaDoPapagaio = new DayOfWeek[] { DayOfWeek.MONDAY, DayOfWeek.THURSDAY, DayOfWeek.SATURDAY };
		novaAgenda.adicionarLembreteSemanal("Dar comida ao papagaio", Arrays.asList(comidaDoPapagaio));
		
		novaAgenda.adicionarLembreteMensal("Viajar para praia", Month.JANUARY);
		novaAgenda.adicionarLembreteMensal("Aniversario do cachorro", Month.JULY);
		Evento ifch = new Evento("Ir para o IFCH", 15, Month.MAY, 2022);
		novaAgenda.adicionarEvento(ifch);
		
		Pessoa[] familia = new Pessoa[] { new Pessoa("Pai", "meupai@gmail.com", "(19)97520-3241"),
				new Pessoa("Mae", "minhamae@hotmail.com", "(19)97530-4010"),
				new Pessoa("Irmao", "meuirmao@ig.com.br", "(19)97565-3029") };
		Reuniao festaDeNatal = new Reuniao("Festa de natal da familia", 22, Month.NOVEMBER, 2021,
				Arrays.asList(familia));
		festaDeNatal.confirmarPresenca("Pai");
		novaAgenda.adicionarReuniao(festaDeNatal);
		festaDeNatal.confirmarPresenca("Irmao");
		
		System.out.printf("============================== Lembretes de hoje ==============================\n");
		novaAgenda.imprimirLembretesDoDia();
		System.out.printf("============================== Lembretes de daqui a 3 dias ==============================\n");
		novaAgenda.imprimirLembretesDoDia(dataAtual.plusDays(3));
		System.out.printf("============================== Lembretes de daqui a 4 dias ==============================\n");
		novaAgenda.imprimirLembretesDoDia(dataAtual.plusDays(4));
		
		LocalDate dataInicial = LocalDate.of(2021, 1, 2);
		LocalDate dataFinal = LocalDate.of(2022, 5, 20);
		System.out.printf("============================== Teste: Lembretes de um periodo ==============================\n");
		novaAgenda.imprimirLembretesDoPeriodo(dataInicial, dataFinal);
		
		System.out.printf("============================== Teste: Cancelar Lembrete, Gato Morreu :'( ==============================\n");
		novaAgenda.cancelarLembrete("Dar comida ao gato", TipoLembrete.LembreteComum);
		novaAgenda.imprimirLembretesDoDia();
		return;
	}
}
