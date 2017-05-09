package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo;

import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.feijao.CartaFeijao;

public class Carta implements Comparable<Carta> {

	private final Valor valor;
	private final Naipe naipe;

	public Carta(Valor valor, Naipe naipe) {
		this.valor = valor;
		this.naipe = naipe;
	}

	public CartaFeijao fornecerCartaFeijão() {
		return new CartaFeijao(valor.toString(), naipe.toString());
	}

	public boolean éIgual(Carta outraCarta) {
		return (éDoMesmoNaipeQue(outraCarta) && éDoMesmoValorQue(outraCarta));
	}

	public boolean éDoMesmoNaipeQue(Carta outraCarta) {
		return naipe.equals(outraCarta.naipe);
	}

	public boolean éDoMesmoValorQue(Carta outraCarta) {
		return valor.equals(outraCarta.valor);
	}

	public boolean vemAntesDe(Carta outraCarta) {
		return valor.éUmAMenosQue(outraCarta.valor) || éRei()
				&& outraCarta.éÁs();
	}

	public boolean éDois() {
		return valor.equals(Valor.DOIS);
	}

	public boolean éÁs() {
		return valor.equals(Valor.ÁS);
	}

	public boolean éRei() {
		return valor.equals(Valor.REI);
	}

	public Naipe fornecerNaipe() {
		return naipe;
	}

	public Valor fornecerValor() {
		return valor;
	}

	@Override
	public int compareTo(Carta outraCarta) {
		if (!valor.equals(Valor.ÁS) && outraCarta.valor.equals(Valor.ÁS)) {
			return -1;
		}
		if (valor.equals(Valor.ÁS) && !outraCarta.valor.equals(Valor.ÁS)) {
			return 1;
		}
		if (valor.éMenorQue(outraCarta.valor)) {
			return -1;
		}
		if (valor.éMaiorQue(outraCarta.valor)) {
			return 1;
		}

		return 0;
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Carta) {
			return éIgual((Carta) objeto);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return (valor.hashCode());
	}

	@Override
	public String toString() {
		return valor.toString() + " de " + naipe.toString();
	}
}
