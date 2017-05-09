package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo;

import java.util.ArrayList;
import java.util.List;

import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;

public class Mao implements Comparable<Mao> {

	private List<Carta> mão = new ArrayList<Carta>();
	private RankingDeMao rankingDeMão = RankingDeMao.CARTA_ALTA;
	private NomeDeUsuario nomeDeUsuário;
	
	private static final int ZERO = 0;
	private static final int UM = 1;
	private static final int TRÊS = 3;
	private static final int QUATRO = 4;
	public final static int NÚMERO_DE_CARTAS_NO_JOGO = 5;
	
	public Mao(List<Carta> mão, RankingDeMao rankingDeMão, NomeDeUsuario nomeDeUsuário) {
		this.mão = mão;
		this.rankingDeMão = rankingDeMão;
		this.nomeDeUsuário = nomeDeUsuário;
	}

	@Override
	public int compareTo(Mao outraMão) {
		if (this.rankingDeMão.compareTo(outraMão.rankingDeMão) == ZERO) {
			for (int i = NÚMERO_DE_CARTAS_NO_JOGO - 1; i >= ZERO; i--) {
				switch (this.mão.get(i).compareTo(outraMão.mão.get(i))) {
				case 1:
					return 1;
				case -1:
					return -1;
				}
			}
			return 0;
		} else if (this.rankingDeMão.compareTo(outraMão.rankingDeMão) > ZERO)
			return 1;
		return -1;
	}
	
	public NomeDeUsuario fornecerNomeDeUsuario() {
		return nomeDeUsuário;
	}
	
	@Override
	public String toString() {
		String toString;
		switch (this.rankingDeMão) {
		case CARTA_ALTA:
			toString = "Carta mais alta " + this.mão.get(mão.size() - UM);
			break;
		case PAR:
			toString = "Par de "
					+ this.mão.get(mão.size() - UM).fornecerValor();
			break;

		case DOIS_PARES:
			toString = "Dois pares: "
					+ this.mão.get(mão.size() - UM).fornecerValor()
					+ " e "
					+ this.mão.get(mão.size() - TRÊS).fornecerValor();
			break;

		case FLUSH:
			toString = "Cinco cartas do mesmo naipe: " + this.mão;
			break;
		case FULL_HOUSE:
			toString = "Full House: Trinca de "
					+ this.mão.get(mão.size() - UM).fornecerValor()
					+ " e par de "
					+ this.mão.get(mão.size() - QUATRO).fornecerValor();
			break;
		case QUADRA:
			toString = "Quadra de "
					+ this.mão.get(mão.size() - UM).fornecerValor();
			break;
		case ROYAL_FLUSH:
			toString = "Royal Flush :) " + this.mão;
			break;
		case STRAIGHT:
			toString = "Sequência " + this.mão;
			break;
		case STRAIGHT_FLUSH:
			toString = "Sequência de mesmo naipe " + this.mão;
			break;
		case TRINCA:
			toString = "Trinca de "
					+ this.mão.get(mão.size() - UM).fornecerValor();
			break;
		default:
			toString = "Jogo inválido!";
			break;
		}
		return toString;
	}
}