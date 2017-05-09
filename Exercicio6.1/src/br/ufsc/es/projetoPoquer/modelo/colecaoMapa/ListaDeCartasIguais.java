package br.ufsc.es.projetoPoquer.modelo.colecaoMapa;

import java.util.ArrayList;
import java.util.List;

import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Carta;

public class ListaDeCartasIguais extends ArrayList<Carta> implements
		List<Carta>, Comparable<ListaDeCartasIguais> {

	private static final long serialVersionUID = 1L;

	@Override
	public int compareTo(ListaDeCartasIguais outraLista) {
		if (size() < outraLista.size())
			return -1;
		if (size() > outraLista.size())
			return 1;
		return this.get(0).compareTo(outraLista.get(0));
	}
}