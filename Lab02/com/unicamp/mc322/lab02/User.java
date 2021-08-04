package com.unicamp.mc322.lab02;

import java.util.*;

public class User {
	private String nome;
	private String cpf;
	private String nascimento;
	private String genero;
	private boolean ehAssinante;
	private List<Playlist> playlist;

	public User(String nomeUsuario, String cpfUsuario) {
		nome = nomeUsuario;
		cpf = cpfUsuario;
		nascimento = "Não informado";
		genero = "Desconhecido";
		ehAssinante = false;
		playlist = new ArrayList<Playlist>();
	}

	public void modificarAssinatura(boolean assinatura) {
		ehAssinante = assinatura;
		return;
	}

	public void addPlaylist(Playlist play) {
		if (ehAssinante) {
			if (playlist.size() < 10 && play.retornarNumMusicas() < 100) {
				playlist.add(play);
			} else {
				System.out.printf(
						"ERRO: Numero de playlists ou musicas na playlist atual excedeu seu limite maximo permitido.\n");
			}
		} else {
			if (playlist.size() < 3 && play.retornarNumMusicas() < 10) {
				playlist.add(play);
			} else {
				System.out.printf(
						"ERRO: Numero de playlists ou musicas na playlist atual excedeu seu limite maximo permitido.\n");
			}
		}
		return;
	}

	public void removePlaylist(String nomeDaPlaylist) {
		Playlist aux;
		for (int i = 0; i < playlist.size(); i++) {
			aux = playlist.get(i);
			if (nomeDaPlaylist.equalsIgnoreCase(aux.retornarNome())) {
				playlist.remove(i);
				return;
			}
		}
		return;
	}

	public void transferirPlaylist(String nomeDaPlaylist, User destinatario) {
		Playlist aux;
		for (int i = 0; i < playlist.size(); i++) {
			aux = playlist.get(i);
			if (nomeDaPlaylist.equalsIgnoreCase(aux.retornarNome())) {
				destinatario.addPlaylist(aux);
				return;
			}
		}
		return;
	}

	public void showInformation() {
		System.out.printf("Nome: %s\nCpf: %s\nData de Nascimento: %s\nGenero: %s\nPremium: %b\n", nome, cpf, nascimento,
				genero, ehAssinante);
	}

	public void showPlaylists() {
		System.out.printf("Usuario: %s\nNumero de playlists: %d\n", nome, playlist.size());
		Playlist aux;
		for (int i = 0; i < playlist.size(); i++) {
			System.out.printf("Playlist %d: ", i+1);
			aux = playlist.get(i);
			aux.mostrarMusicas();
		}
		return;
	}
}
