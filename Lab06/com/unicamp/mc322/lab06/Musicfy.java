package com.unicamp.mc322.lab06;

public class Musicfy {

	public static void main(String[] args) {
		User usuario1 = new User("Aurelio Paquim", "987.543.101-90", "Masculino", "23/10/1995");
		User usuario2 = new User("Mariana Pascoal", "120.432.765-40", "Feminino", "15/02/1997");

		Musica musica1 = new Musica("La Belle de Jour", "Alceu Valenca", 454);
		Musica musica2 = new Musica("Taxi Lunar", "Alceu Valenca", 207);
		Musica musica3 = new Musica("Tropicana", "Alceu Valenca", 315);
		Musica musica4 = new Musica("Anunciacao", "Alceu Valenca", 388);
		Musica musica5 = new Musica("Chao de Giz", "Ze Ramalho", 268);

		Album album1 = new Album("Melhores do Alceu");
		album1.adicionarMusica(musica1);
		album1.adicionarMusica(musica3);
		album1.adicionarMusica(musica4);
		Album album2 = new Album("Mpb mix");
		album2.adicionarMusica(musica1);
		album2.adicionarMusica(musica2);
		album2.adicionarMusica(musica5);
		System.out.printf("Estes foram os dois albuns criados:\n");
		album1.imprimirInfo();
		album2.imprimirInfo();

		Video video1 = new Video("Pegadinhas do Silvio Santos", "SBT", 420, Resolucao.res420p, 30);
		Video video2 = new Video("Banheira do Gugu", "SBT", 680, Resolucao.res720p, 60);
		Video video3 = new Video("10 horas de mendonça falando lineuzinho", "Gustavo Nalin", 36000, Resolucao.res1080p,
				60);

		Podcast podcast1 = new Podcast("Flow", "Monark", 7200, 6);
		System.out.printf("\n\nCriando novas playlists:\n");
		usuario1.modificarPremimum(true);
		Playlist play1 = usuario1.criarPlaylist("Musicas");
		Playlist play2 = usuario1.criarPlaylist("Videos");

		play1.adicionarMidia(album1, usuario1);
		play1.adicionarMidia(album2, usuario1);
		play2.adicionarMidia(video1, usuario1);
		play2.adicionarMidia(video2, usuario1);
		play2.adicionarMidia(video3, usuario1);
		System.out.printf("Dados Usuario1:\n");
		usuario1.imprimirInfo();

		play2.removerMidia(video1);
		System.out.printf("\nApos remover video1 e adicionar video2:\n");
		play2.adicionarMidia(video2, usuario1);
		play2.adicionarMidia(video3, usuario1);
		System.out.printf("Dados Usuario1:\n");
		usuario1.imprimirInfo();

		System.out.printf("\nVamos tentar transferir agora a playlist2 para o usuario nao premium:\n");
		if (usuario1.transferirPlaylist(usuario2, play2)) {
			System.out.printf("Transferencia bem sucedida.\n");
		} else {
			System.out.printf("Transferencia nao foi bem sucedida.\n");
		}
		System.out.printf("Dados Usuario2:\n");
		usuario2.imprimirInfo();
		
		System.out.printf("\nTeste das midias abaixo dos 60s:\n");
		Video video59s = new Video("Galo de Kalsa", "Fabioluiz100", 45, Resolucao.res4k, 60);
		Playlist play3 = usuario2.criarPlaylist("Galo de Kalsa");
		for (int i = 0; i < 30; i++) {
			play3.adicionarMidia(video59s, usuario2);
		}
		play3.imprimirInfo();
		System.out.println();

		System.out.printf("\nVamos tentar transferir agora a playlist1 para o usuario nao premium:\n");
		if (usuario1.transferirPlaylist(usuario2, play1)) {
			System.out.printf("Transferencia bem sucedida.\n");
		} else {
			System.out.printf("Transferencia bem sucedida.\n");
		}
		System.out.printf("Dados Usuario2:\n");
		usuario2.imprimirInfo();
		return;
	}

}
