package com.unicamp.mc322.lab13;

import java.time.LocalDate;
import java.time.Month;
import com.unicamp.mc322.lab13.Container.*;
import com.unicamp.mc322.lab13.Estrategia.*;
import com.unicamp.mc322.lab13.Pedidos.*;
import com.unicamp.mc322.lab13.Exceptions.*;

/**
 * 
 * @author Alan Freitas Ribeiro, RA: 193400
 *
 */
public class Runner {

	public static void main(String[] args) {
		ICrazyDS crazyDS = new ICrazyDSContainer(new PriorityScore());

		IOrder order1 = new PedidoInternet(
				new Pessoa("Marcio Araujo Reis", "123456789-10", LocalDate.of(1990, Month.AUGUST, 13)));
		IOrder order2 = new PedidoInternet(
				new Pessoa("Lorena Freitas Garcia", "303221789-10", LocalDate.of(1986, Month.JANUARY, 11)));
		IOrder order3 = new PedidoInternet(
				new Pessoa("Marcio Araujo Reis", "123456789-10", LocalDate.of(1990, Month.AUGUST, 13)));
		IOrder ordem1 = new PedidoFisico(
				new Pessoa("Gabriel Lorient Gusman", "223561749-15", LocalDate.of(1986, Month.JANUARY, 11)));

		crazyDS.adicionarElemento(order1);
		crazyDS.adicionarElemento(order2);
		crazyDS.adicionarElemento(order3);

		System.out.println("---- A: Elements ----");
		crazyDS.imprimirListaPrioritaria();

		System.out.println("---- B: Getting and removing the element with highest priority ----");
		IOrder p1;
		try {
			p1 = crazyDS.obterMaiorPrioridade();
			System.out.println("-selected element");
			p1.imprimirReduzido();
			crazyDS.removerElemento(p1);
			System.out.println("-elements");
			crazyDS.imprimirListaPrioritaria();
		} catch (CrazyDSOutOfBoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("---- C: Adding an old person ----");
        IOrder order4 = new PedidoInternet(new Pessoa("Lorival dos Santos Pereira", "210322432-97", LocalDate.of( 1880 , Month.JUNE , 1 )));
        crazyDS.adicionarElemento(order4);
        crazyDS.imprimirListaPrioritaria();;
        System.out.println("-selected element");
        IOrder p2 = crazyDS.obterMaiorPrioridade();
        p2.imprimirCompleto();
        
        System.out.println("---- D: Checking an exception ----");
        IOrder ordem2;
        try {
            IOrder p3 = crazyDS.obterElementoNaPosicao(1000);
        } catch (CrazyDSOutOfBoundException e) {
            System.out.printf("-ok: Show error in logs :\t%s\n", e.getMessage());
        }
        try {
        	ordem2 = new PedidoInternet(
    				new Pessoa("Gabriel Lorient Gusman", "223561749-15", LocalDate.of(2040, Month.JANUARY, 11)));
        } catch(IllegalPersonArgumentException o) {
        	System.out.printf("Erro encontrado: %s\n", o.getMessage());
        	ordem2 = new PedidoInternet(
    				new Pessoa("Gabriel Lorient Gusman", "223561749-15", LocalDate.of(1986, Month.JANUARY, 11)));
        }
        
        System.out.println("---- E: Checking Priority Orders ----");
        ICrazyDS crazyDS2 = new ICrazyDSContainer(new PriorityScore());
        ICrazyDS crazyDS3 = new ICrazyDSContainer(new StorePriorityScore());
        crazyDS2.adicionarElemento(order1);
        crazyDS2.adicionarElemento(order2);
        crazyDS2.adicionarElemento(order3);
        crazyDS2.adicionarElemento(order4);
        crazyDS2.adicionarElemento(ordem1);
        crazyDS2.adicionarElemento(ordem2);
        
        crazyDS3.adicionarElemento(order1);
        crazyDS3.adicionarElemento(order2);
        crazyDS3.adicionarElemento(order3);
        crazyDS3.adicionarElemento(order4);
        crazyDS3.adicionarElemento(ordem1);
        crazyDS3.adicionarElemento(ordem2);
        System.out.println("---- Elementos em DS2 (PriorityScore)----");
        crazyDS2.imprimirListaPrioritaria();
        System.out.println("---- Elementos em DS3 (StorePriorityScore)----");
        crazyDS3.imprimirListaPrioritaria();
	}

}
