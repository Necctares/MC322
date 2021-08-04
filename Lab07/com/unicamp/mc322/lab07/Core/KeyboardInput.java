package com.unicamp.mc322.lab07.Core;

import java.util.Scanner;

public class KeyboardInput {
	/**
	 * Função para ler comandos do teclado.
	 * @return Uma string com o comando lido.
	 */
	public static String lerTeclado() {
		Scanner teclado = new Scanner(System.in);
		boolean esperandoComando = true;
		String comando = null;
		while (esperandoComando) {
			System.out.print("Digite um comando: ");
			comando = teclado.nextLine();
			if (comando.compareTo("sair") == 0) {
				esperandoComando = false;
			} else if (comando.compareToIgnoreCase("w") == 0) {
				esperandoComando = false;
			} else if (comando.compareToIgnoreCase("s") == 0) {
				esperandoComando = false;
			} else if (comando.compareToIgnoreCase("d") == 0) {
				esperandoComando = false;
			} else if (comando.compareToIgnoreCase("a") == 0) {
				esperandoComando = false;
			} else {
				System.out
						.printf("\nComando nao reconhecido, tente novamente (w,a,s,d para mover ra, sair para sair. ");
			}
		}
		return comando;
	}
}
