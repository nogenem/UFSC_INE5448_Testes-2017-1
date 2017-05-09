package br.ufsc.es.projetoPoquer.modelo.colecaoMapa;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Carta;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.feijao.CartaFeijao;

public class ListaDeCartas implements Iterable<Carta> {

	private List<Carta> cartas;
	
	public ListaDeCartas() {
		cartas = new LinkedList<Carta>();
	}
	
	public void adicionar(Carta carta) {
		cartas.add(carta);
	}
	
	public boolean contém(Carta carta) {
		return cartas.contains(carta);
	}
	
	public Carta fornecer(int posição) {
		return cartas.get(posição);
	}
	
	public void remover(Carta carta) {
		cartas.remove(carta);
	}

	public List<CartaFeijao> fornecerCartasFeijão() {
		List<CartaFeijao> cartasFeijão = new LinkedList<CartaFeijao>();
		for (Carta carta : cartas) {
			cartasFeijão.add(carta.fornecerCartaFeijão());
		}

		return cartasFeijão;
	}

	public int fornecerQuantidade() {
		return cartas.size();
	}
	
	@Override
	public Iterator<Carta> iterator() {
		return cartas.iterator();
	}
}