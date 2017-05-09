package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ufsc.es.projetoPoquer.modelo.colecaoMapa.ListaDeCartasIguais;

public class AnalisadorDeJogos {

	private static AnalisadorDeJogos INSTÂNCIA = new AnalisadorDeJogos();
	
	private static final int ZERO = 0;
	private static final int UM = 1;
	private static final int DOIS = 2;
	private static final int TRÊS = 3;
	public final static int QUATRO = 4;
	public final static int TAMANHO_DA_QUADRA = 4;
	public final static int TAMANHO_DA_TRINCA = 3;
	public final static int TAMANHO_DO_PAR = 2;
	public final static int NAIPES = 4;
	public final static int NÚMERO_DE_CARTAS_NO_JOGO = 5;

	public static AnalisadorDeJogos fornecerInstância() {
		return INSTÂNCIA;
	}

	private AnalisadorDeJogos() {
		
	}

	private boolean éSequênciaDeÁsAtéCinco(List<Carta> cartas) {
		// [2][3][4][5][x][x][A]
		return cartas.get(ZERO).éDois()
				&& cartas.get(ZERO).vemAntesDe(cartas.get(UM))
				&& cartas.get(UM).vemAntesDe(cartas.get(DOIS))
				&& cartas.get(DOIS).vemAntesDe(cartas.get(TRÊS))
				&& cartas.get(cartas.size() - UM).éÁs();
	}

	/**
	 * ALGORÍTMO OBTER JOGO 
	 * 
	 * @param Lista de cartas
	 * @return Ranking de mão de acordo com a lista de cartas + lista de cartas com o jogo e tamanho 5
	 * @author Chrystian de Sousa Guth <csguth@gmail.com>
	 */
	public RankingDeMao fornecerJogo(List<Carta> cartas) {
		// PARTE 1: Ordenamento das cartas via compareTo() >>
		Collections.sort(cartas);
		// <<

		// PARTE 2: Checar se tem Royal Flush / Straight Flush >>
		boolean temSequência;
		List<Carta> clone = new ArrayList<Carta>();
		clone.addAll(cartas);
		temSequência = temSequência(clone);
		if (temSequência) {
			if (sãoTodasDoMesmoNaipe(clone)) {
				if (clone.get(clone.size() - UM).éÁs()
						&& clone.get(clone.size() - DOIS).éRei()) {
					return RankingDeMao.ROYAL_FLUSH;
				}
				return RankingDeMao.STRAIGHT_FLUSH;
			}
		}
		// <<

		// PARTE 3: Sistema para identificar quadras/trincas/pares >>
		/*
		 * Cria uma lista de sublistas de cartas iguais, se existirem sublistas
		 * com tamanho 2, significa que o jogo tem par. Se existirem sublistas
		 * com tamanho 3, significa que o jogo tem trinca. Se existir uma
		 * sublista com tamanho 4, significa que o jogo tem uma quadra.
		 */
		int pares, trincas, quadras;
		pares = ZERO;
		trincas = ZERO;
		quadras = ZERO;
		List<ListaDeCartasIguais> listas = new ArrayList<ListaDeCartasIguais>();
		ListaDeCartasIguais lista = new ListaDeCartasIguais();
		listas.add(lista);
		for (int i = ZERO; i < cartas.size() - UM; i++) {
			lista.add(cartas.get(i));
			if (cartas.get(i).compareTo(cartas.get(i + UM)) != ZERO) {
				lista = new ListaDeCartasIguais();
				listas.add(lista);
			}
			if (i + UM == cartas.size() - UM)
				lista.add(cartas.get(i + UM));
		}
		// Ordena as sublistas de cartas iguais de acordo com o compareTo() da
		// classe ListaDeCartasIguais (cartaSozinha < Par < Trinca < Quadra)
		Collections.sort(listas);
		for (ListaDeCartasIguais list : listas) {
			switch (list.size()) {
			case TAMANHO_DO_PAR:
				pares++;
				break;
			case TAMANHO_DA_TRINCA:
				trincas++;
				break;
			case TAMANHO_DA_QUADRA:
				quadras++;
				break;
			}

		}
		System.out.println(listas);

		// PARTE 4: Checar se tem quadra >>
		if (quadras > ZERO) {
			cartas.clear();
			cartas.addAll(listas.get(listas.size() - DOIS));
			cartas.addAll(listas.get(listas.size() - UM));
			return RankingDeMao.QUADRA;
		}
		// <<

		// PARTE 5: Checar se tem fullhouse >>
		if (trincas > ZERO && pares > ZERO) {
			cartas.clear();
			cartas.addAll(ZERO, listas.get(listas.size() - UM));
			int i = listas.size() - DOIS;
			boolean achouOParDoFullHouse = false;
			while (i > ZERO && !achouOParDoFullHouse) {
				if (listas.get(i).size() == DOIS) {
					cartas.addAll(ZERO, listas.get(i));
					achouOParDoFullHouse = true;
				}
				i--;
			}
			return RankingDeMao.FULL_HOUSE;
		}
		// <<

		// Parte 6: Checar se tem cartas do mesmo naipe >>
		if (temFlush(cartas))
			return RankingDeMao.FLUSH;
		// <<

		// Parte 7: Checar se tem sequência >>
		if (temSequência) {
			cartas.clear();
			cartas.addAll(clone);
			return RankingDeMao.STRAIGHT;
		}
		// <<

		// Parte 8: Checar se tem trinca >>
		if (trincas > ZERO) {
			cartas.clear();
			for (int i = listas.size() - UM; i >= ZERO; i--)
				cartas.addAll(ZERO, listas.get(i));
			while (cartas.size() > NÚMERO_DE_CARTAS_NO_JOGO)
				cartas.remove(ZERO);
			return RankingDeMao.TRINCA;
		}
		// <<

		// Parte 9: Checar se tem dois pares >>
		if (pares > UM) {
			cartas.clear();
			cartas.addAll(ZERO, listas.get(listas.size() - UM));
			cartas.addAll(ZERO, listas.get(listas.size() - DOIS));
			cartas.add(ZERO, listas.get(listas.size() - TRÊS).get(ZERO));
			return RankingDeMao.DOIS_PARES;
		}
		// <<

		// Parte 10: Checar se tem um par >>
		if (pares > ZERO) {
			cartas.clear();
			cartas.addAll(ZERO, listas.get(listas.size() - UM));
			cartas.add(ZERO, listas.get(listas.size() - DOIS).get(ZERO));
			cartas.add(ZERO, listas.get(listas.size() - TRÊS).get(ZERO));
			cartas.add(ZERO, listas.get(listas.size() - QUATRO).get(ZERO));
			return RankingDeMao.PAR;
		}
		// <<

		// Parte 11: Se não tem nenhum jogo, remove as menores cartas até a
		// lista tenha 5 cartas
		while (cartas.size() > NÚMERO_DE_CARTAS_NO_JOGO)
			cartas.remove(ZERO);
		// <<

		// Parte 12: Finaliza, retornando o menor jogo possível
		return RankingDeMao.CARTA_ALTA;
		// <<
	}

	private boolean sãoTodasDoMesmoNaipe(List<Carta> cartas) {
		for (int i = ZERO; i < cartas.size() - UM; i++)
			if (!cartas.get(i).éDoMesmoNaipeQue(cartas.get(i + UM)))
				return false;
		return true;
	}

	private boolean temFlush(List<Carta> cartas) {
		int[] cartasDoNaipe = new int[] { ZERO, ZERO, ZERO, ZERO };

		for (int i = ZERO; i < cartas.size(); i++) {
			Naipe naipe = cartas.get(i).fornecerNaipe();
			cartasDoNaipe[naipe.ordinal()]++;
		}

		for (int k = ZERO; k < NAIPES; k++) {
			if (cartasDoNaipe[k] >= NÚMERO_DE_CARTAS_NO_JOGO) {
				for (int i = ZERO; i < cartas.size(); i++)
					if (cartas.get(i).fornecerNaipe().ordinal() != k) {
						cartas.remove(cartas.get(i));
						i--;
					}
				while (cartas.size() > NÚMERO_DE_CARTAS_NO_JOGO)
					cartas.remove(ZERO);
				return true;
			}
		}
		return false;
	}

	private boolean temSequência(List<Carta> cartas) {
		if (éSequênciaDeÁsAtéCinco(cartas)) {
			// remove a 5ª e a 6ª carta
			while (cartas.size() > NÚMERO_DE_CARTAS_NO_JOGO)
				cartas.remove(QUATRO);
			cartas.add(ZERO, cartas.remove(cartas.size() - UM));
			return true;
		}

		boolean parada;
		int i, fim;

		for (int k = ZERO; k < TRÊS; k++) {

			i = k;
			parada = false;
			fim = i + NÚMERO_DE_CARTAS_NO_JOGO;
			while (i < fim - UM && !parada) {
				if (!cartas.get(i).vemAntesDe(cartas.get(i + UM)))
					parada = true;
				i++;
			}
			if (!parada) {
				switch (k) {
				case ZERO:
					// se a sequência começa na primeira carta, remove as duas
					// últimas
					cartas.remove(cartas.size() - UM);
					cartas.remove(cartas.size() - UM);
					break;
				case UM:
					// se a sequência começa na segunda carta, remove a primeira
					// e a última
					cartas.remove(ZERO);
					cartas.remove(cartas.size() - UM);
					break;
				case DOIS:
					// se a sequência começa na terceira carta, remove as duas
					// primeiras
					cartas.remove(ZERO);
					cartas.remove(ZERO);
				}
				return true;
			}

		}
		return false;
	}
}