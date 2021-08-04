package com.unicamp.mc322.lab05;

import java.time.Month;
import java.util.*;

public class Reuniao extends Evento {
	private List<Pessoa> pessoasConvidadas;
	private List<Pessoa> pessoasConfirmadas;

	Reuniao(String lembrete, int dia, Month mes, int ano, List<Pessoa> convidados) {
		super(lembrete, dia, mes, ano);
		pessoasConvidadas = new ArrayList<Pessoa>();
		pessoasConfirmadas = new ArrayList<Pessoa>();
		for (Pessoa atual : convidados) {
			pessoasConvidadas.add(atual);
		}
	}

	public void confirmarPresenca(String emailNomeOuTelefone) {
		boolean achouPessoa = false;
		for (Pessoa pessoaAtual : pessoasConvidadas) {
			achouPessoa = pessoaAtual.checarEmail(emailNomeOuTelefone) || pessoaAtual.checarNome(emailNomeOuTelefone)
					|| pessoaAtual.checarTelefone(emailNomeOuTelefone);
			if (achouPessoa) {
				pessoasConfirmadas.add(pessoaAtual);
				pessoasConvidadas.remove(pessoaAtual);
				return;
			}
		}
		if (!achouPessoa) {
			System.out.printf("Pessoa nao encontrada.\n");
		}
		return;
	}

	@Override
	protected void imprimirLembrete() {
		System.out.printf("****Lembrete****\nData da Reuniao: %d/%d/%d\nDescricao: %s\n", dia, mes.getValue(), ano,
				descricao);
		System.out.printf("Pessoas Confirmadas:\n");
		for (Pessoa pessoaAtual : pessoasConfirmadas) {
			pessoaAtual.imprimirDados();
		}
		System.out.printf("Pessoas Convidadas que nao confirmaram ainda:\n");
		for (Pessoa pessoaAtual : pessoasConvidadas) {
			pessoaAtual.imprimirDados();
		}
		System.out.println();
	}
}
