package com.unicamp.mc322.lab08.Core;

import java.util.*;
import java.time.LocalDate;
import java.time.Period;

import com.unicamp.mc322.lab08.Acao.*;
import com.unicamp.mc322.lab08.Tweet.*;

public class User {
	private String nome;
	private String email;
	private String senha;
	private String genero;
	private String pais;
	private LocalDate dataNascimento;
	private String user;
	private List<User> seguidores;
	private List<User> seguindo;
	private List<Acao> historico;
	private List<Tweet> posts;

	private User(String nome, String email, String senha, String genero, String pais, LocalDate dataNascimento,
			String user) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.genero = genero;
		this.pais = pais;
		this.dataNascimento = dataNascimento;
		this.user = user;
		seguidores = new ArrayList<User>();
		seguindo = new ArrayList<User>();
		historico = new ArrayList<Acao>();
		posts = new ArrayList<Tweet>();
	}

	static User criarUsuario(String nome, String email, String senha, String genero, String pais,
			LocalDate dataNascimento, String user) {
		Period periodo = Period.between(dataNascimento, LocalDate.now());
		if (periodo.getYears() >= 18) {
			User novoUsuario = new User(nome, email, senha, genero, pais, dataNascimento, user);
			return novoUsuario;
		} else {
			return null;
		}
	}

	public String getUser() {
		return user;
	}

	public void seguir(User alvo) {
		if (alvo != null) {
			Acao seguir = Seguir.seguirUsuario(alvo.nome);
			Acao seguido = Seguido.seguirUsuario(nome);
			this.historico.add(seguir);
			alvo.historico.add(seguido);
			this.seguindo.add(alvo);
			alvo.seguidores.add(this);
		}
		return;
	}

	public void publicarTweet(Tweet publicacao) {
		if (publicacao != null) {
			posts.add(publicacao);
			Acao publicou = Publicou.publicarTweet(publicacao);
			historico.add(publicou);
		}
		return;
	}

	public void darLike(Tweet publicacao) {
		if (publicacao != null) {
			Acao like = Like.gostarTweet(publicacao);
			historico.add(like);
		}
		return;
	}

	public void darRetweet(Tweet publicacao) {
		if (publicacao != null) {
			Acao rt = Retweet.compartilharTweet(publicacao);
			historico.add(rt);
		}
		return;
	}

	public void comentarTweet(Tweet publicacao, String comentario) {
		if (publicacao != null) {
			Acao comment = Comentario.comentarTweet(publicacao, comentario);
			historico.add(comment);
		}
		return;
	}

	public void printInfo() {
		System.out.printf("******* User Summary *******\nUser: %s\nNome: %s\nEmail: %s\nSenha: %s\n", user, nome, email,
				senha);
		System.out.printf("Data de nascimento: %d/%d/%d\nPais de origem: %s\nGenero: %s\n\n",
				dataNascimento.getDayOfMonth(), dataNascimento.getMonthValue(), dataNascimento.getYear(), pais, genero);
		System.out.printf("%s possui %d seguidores:\n", user, seguidores.size());
		for (User atual : seguidores) {
			System.out.printf("%s\t", atual.user);
		}
		System.out.printf("\n\n%s segue %d pessoas:\n", user, seguindo.size());
		for (User atual : seguindo) {
			System.out.printf("%s\t", atual.user);
		}
		System.out.printf("\n\n%s possui %d publicacoes:\n", user, posts.size());
		for (Tweet atual : posts) {
			atual.imprimirInfo();
			System.out.println();
		}
		System.out.printf("Linha do tempo de %s:\n", user);
		for (int atual = historico.size() - 1; atual >= 0; atual--) {
			System.out.printf("%s %s\n\n", user, historico.get(atual).informarAcao());
		}
		return;
	}
}
