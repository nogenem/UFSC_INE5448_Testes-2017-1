package br.ufsc.es.projetoPoquer.modelo.colecaoMapa;

import java.util.HashSet;
import java.util.Set;

import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Carta;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.feijao.CartaFeijao;

public class ConjuntoDeCartas extends ConjuntoColecaoMapaAbstrato<Carta, Carta, CartaFeijao> {

	@Override
	protected Set<CartaFeijao> fornecerObjetosFeijãoConcreto(Set<Carta> chavesDosValores) {
		Set<CartaFeijao> cartasFeijão = new HashSet<CartaFeijao>();
		for (Carta carta : chavesDosValores) {
			cartasFeijão.add(carta.fornecerCartaFeijão());
		}

		return cartasFeijão;
	}

}