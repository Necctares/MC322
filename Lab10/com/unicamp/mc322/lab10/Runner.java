package com.unicamp.mc322.lab10;

import java.util.Arrays;

import com.unicamp.mc322.lab10.Pedido.*;
import com.unicamp.mc322.lab10.Restaurante.*;

public class Runner {

	public static void main(String[] args) {
		Cadastro iEat = new Cadastro();
		
		Cliente c1 = iEat.cadastrarCliente("Jose Pereira", "123456789-00", new Posicao(0,0));
		Cliente c2 = iEat.cadastrarCliente("Mariana Silva", "020101345-56", new Posicao(5,6));
		
		Entregador e1 = iEat.cadastrarEntregador("Mariano Jose", "202010011-98");
		Entregador e2 = iEat.cadastrarEntregador("Livia Marinho", "302221345-20");
		Entregador e3 = iEat.cadastrarEntregador("Kaio Silvestre", "190923434-20");
		
		Restaurante r1 = iEat.cadastrarRestaurante("Ki Comida", "2030-98", new Posicao(2,2));
		Restaurante r2 = iEat.cadastrarRestaurante("Marmitaria da Silvia", "1102-30", new Posicao(30,12));
		
		Lanche l1 = Lanche.criarLanche("5532A", "Abobrinha Frita com Requeijao", 20.50);
		Lanche l2 = Lanche.criarLanche("5502C", "Lasanha 4 Queijos", 26.30);
		Lanche l3 = Lanche.criarLanche("1522H", "Lasanha 4 Queijos", 21.50);
		Lanche l4 = Lanche.criarLanche("2542T", "Macarrao ao sugo", 16.00);
		Lanche l5 = Lanche.criarLanche("3032U", "Feijoada", 18.20);
		
		r1.adicionarAoCardapio(l5);
		r1.adicionarAoCardapio(l4);
		r1.adicionarAoCardapio(l2);
		r1.adicionarAoCardapio(l1);
		r1.aplicarDesconto("5532A", 20, TipoDesconto.PORCENTAGEM);
		r1.adicionarEntregador(e1);
		r1.adicionarEntregador(e2);
		
		r2.adicionarAoCardapio(l3);
		r2.adicionarAoCardapio(l1);
		r2.adicionarAoCardapio(l5);
		r2.aplicarDesconto("3032U", 3.00, TipoDesconto.MONETARIO);
		r2.adicionarEntregador(e1);
		r2.adicionarEntregador(e2);
		r2.adicionarEntregador(e3);
		
		Pedido p1 = new Pedido(c1, r1, false);
		p1.addItem(l5);
		p1.addItem(l5);
		p1.addItem(l1);
		p1.fecharPedido();
		
		Pedido p2 = new Pedido(c2, r1, true);
		p2.addItem(l2);
		p2.fecharPedido();
		
		Pedido p3 = new Pedido(c1, r2, true);
		p3.addItem(l1);
		p3.fecharPedido();
		
		Pedido p4 = new Pedido(c2, r2, true);
		p4.addItem(l3);
		p4.fecharPedido();
		
		r1.prepararProximoPedido();
		
		r1.prepararProximoPedido();
		r1.entregarProximoPedido();
		
		r2.prepararProximoPedido();
		r2.entregarProximoPedido();
		
		r2.prepararProximoPedido();
		r2.entregarProximoPedido();
		
		e1.encerrarEntrega();
		e2.encerrarEntrega();
		r1.retirarPedido(p1);
		
		p2.avaliarPedido(Arrays.asList(5), 5, 5, "Servico muito bom, lasanha estava muito saborosa.");
		p1.avaliarPedido(Arrays.asList(4,4,5), 5, null, "Poderiam diminuir a quantidade de gordura na feijoada.");
		p3.avaliarPedido(Arrays.asList(5), 3, 3, "Entregador ficou dando voltas no quarteirao, tentei ligar para o restaurante mas nao atenderam.");
		
		iEat.imprimirTodosCardapios();
		iEat.imprimirResumoPedidos();
		iEat.imprimirAvaliacoes();
	}

}
