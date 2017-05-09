package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo;

import java.util.EnumSet;
import java.util.Iterator;

import br.ufsc.es.projetoPoquer.modelo.colecaoMapa.ConjuntoDeCartas;
import br.ufsc.es.projetoPoquer.modelo.colecaoMapa.FilaDeJogadoresEmRodada;
import br.ufsc.es.projetoPoquer.modelo.colecaoMapa.ListaDeCartas;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.utilidades.Sorteio;

public final class Dealer {
	
	private ConjuntoDeCartas baralho;
	private NomeDeUsuario nomeDeUsuárioDoJogadorDealer;
	
	//private static final int NÚMERO_DE_CARTAS_DO_BARALHO = 52;
	private static final int ZERO = 0;
	private static final int UM = 1;
	private static final int CARTAS_FECHADAS_POR_JOGADOR = 2;
	private static final int NÚMERO_DE_VALORES = 13;
	private static final int NÚMERO_DE_NAIPES = 4;
	private static final int TRÊS = 3;
	
	public Dealer() {
		baralho = new ConjuntoDeCartas();
		gerarBaralho();
	}
	
	public void fixarJogadorDealerNaMesa(NomeDeUsuario nomeDeUsuárioDoJogadorDealer) {
		this.nomeDeUsuárioDoJogadorDealer = nomeDeUsuárioDoJogadorDealer;
	}
	
	public NomeDeUsuario fornecerNomeDeUsuárioDoJogadorDealer() {
		return nomeDeUsuárioDoJogadorDealer;
	}
	
	public void darAsCartas(FilaDeJogadoresEmRodada jogadoresEmRodada) {
		ConjuntoDeCartas cartasSorteadas = sortearCartas(jogadoresEmRodada.fornecerQuantidade()*CARTAS_FECHADAS_POR_JOGADOR);
		Iterator<Carta> iterador = cartasSorteadas.iterator();
		while (iterador.hasNext()) {
			ListaDeCartas cartasDoJogador = new ListaDeCartas();
			Carta carta = iterador.next();
			cartasDoJogador.adicionar(carta);
			carta = iterador.next();
			cartasDoJogador.adicionar(carta);
			jogadoresEmRodada.reentrarNaFila().receberCartas(cartasDoJogador);
			
		}
	}
	
	public void darOFlop(ListaDeCartas cartas) {
		ConjuntoDeCartas conjuntoDeCartas = sortearCartas(TRÊS);
		receberCartasDeVolta(cartas);
		for (Carta carta : conjuntoDeCartas) {
			cartas.adicionar(carta);
		}
	}
	
	public void darOTurn(ListaDeCartas cartas) {
		ConjuntoDeCartas conjuntoDeCartas = sortearCartas(UM);
		for (Carta carta : conjuntoDeCartas) {
			cartas.adicionar(carta);
		}
	}
	
	public void darORiver(ListaDeCartas cartas) {
		ConjuntoDeCartas conjuntoDeCartas = sortearCartas(UM);
		for (Carta carta : conjuntoDeCartas) {
			cartas.adicionar(carta);
		}
	}
	
	public void receberCartasDeVolta(ListaDeCartas cartas) {
		for (Carta carta : cartas) {
			baralho.adicionar(carta, carta);
			cartas.remover(carta);
		}
	}
	
	private ConjuntoDeCartas sortearCartas(int quantidade) {
		Valor valores[] = Valor.values();
		Naipe naipes[] = Naipe.values();
		ConjuntoDeCartas cartasSorteadas = new ConjuntoDeCartas();
		for (int cont = ZERO; cont < quantidade; cont++) {
			boolean sorteado = false;
			while (!sorteado) {
				Valor valorSorteado = valores[Sorteio.sortear(ZERO, NÚMERO_DE_VALORES-UM)];
				Naipe naipeSorteado = naipes[Sorteio.sortear(ZERO, NÚMERO_DE_NAIPES-UM)];
				Carta cartaSorteada = new Carta(valorSorteado, naipeSorteado);
				if (baralho.contém(cartaSorteada)) {
					cartasSorteadas.adicionar(cartaSorteada, cartaSorteada);
					baralho.remover(cartaSorteada);
					sorteado = true;
				}
			}
		}
		
		return cartasSorteadas;
	}
	
	
	private void gerarBaralho() {
		for (Naipe naipe : Naipe.values()) {
			for (Valor valor : EnumSet.range(Valor.ÁS, Valor.REI)) {
				Carta carta = new Carta(valor, naipe);
				baralho.adicionar(carta, carta);
			}
		}
	}
		
	
}
