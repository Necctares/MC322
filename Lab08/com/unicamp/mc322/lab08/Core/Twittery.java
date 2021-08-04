package com.unicamp.mc322.lab08.Core;

import java.time.LocalDate;
import java.util.HashMap;

public class Twittery {
	private HashMap<String, String> cadastros;
	private HashMap<String, User> usuarios;

	public Twittery() {
		cadastros = new HashMap<String, String>();
		usuarios = new HashMap<String, User>();
	}

	public User criarUsuario(String nome, String email, String senha, String genero, String pais,
			LocalDate dataNascimento, String user) {
		if (cadastros.containsKey(email) || cadastros.containsValue(user)) {
			return null;
		} else {
			User novoUsuario = User.criarUsuario(nome, email, senha, genero, pais, dataNascimento, user);
			if (novoUsuario != null) {
				cadastros.put(email, user);
				usuarios.put(user, novoUsuario);
				return novoUsuario;
			} else {
				return null;
			}
		}
	}
}
