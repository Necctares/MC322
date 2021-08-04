package com.unicamp.mc322.lab08;

import java.time.LocalDate;

import com.unicamp.mc322.lab08.Core.*;
import com.unicamp.mc322.lab08.Tweet.*;

public class Runner {

	public static void main(String[] args) {
		Twittery twittery = new Twittery();
		User user1 = twittery.criarUsuario("Joao Costa", "joaoc@gmail.com", "jo@o12345", "masculino", "Brasil",
				LocalDate.of(1986, 5, 2), "joaoc");
		User user2 = twittery.criarUsuario("Maria Silva", "msilva@outlook.com", "m@ria@", "feminino", "Argentina",
				LocalDate.of(1975, 4, 6), "msilva");
		User user3 = twittery.criarUsuario("Carlos Vargas", "carlos.vargas@gmail.com", "carlos123", "masculino",
				"Brasil", LocalDate.of(2001, 12, 21), "varguinhas");

		user1.seguir(user2);
		user1.seguir(user3);
		user3.seguir(user1);
		user2.seguir(user1);
		user2.seguir(user3);

		Tweet praia = Video.criarTweet("Primeiro dia na praia", 20, Extensao.avi, 20 * 3, user1.getUser());
		user1.publicarTweet(praia);
		user3.darLike(praia);
		user3.comentarTweet(praia, "Parabens pelo passeio");

		Tweet cachorro = Imagem.criarTweet("Cachorro Dormindo", Resolucao.res1080p.retornarNumPixels(), Extensao.gif,
				Resolucao.res1080p, "cachorro dormindo na varanda", user3.getUser());
		user3.publicarTweet(cachorro);
		user1.darLike(cachorro);
		user2.darLike(cachorro);
		user1.darRetweet(cachorro);
		user2.comentarTweet(cachorro, "Como cresceu seu cachorro");
		
		user1.printInfo();
		user2.printInfo();
		user3.printInfo();
		
	}

}
