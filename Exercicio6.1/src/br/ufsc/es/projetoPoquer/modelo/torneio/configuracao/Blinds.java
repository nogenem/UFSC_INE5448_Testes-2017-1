package br.ufsc.es.projetoPoquer.modelo.torneio.configuracao;

import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.SmallBlindEBigBlind;

public final class Blinds {
	
	private int smallBlindInicial;
	private int bigBlindInicial;
	
	public Blinds(int smallBlindInicial, int bigBlindInicial) {
		this.smallBlindInicial = smallBlindInicial;
		this.bigBlindInicial = bigBlindInicial;
	}
	
	public SmallBlindEBigBlind fornecerSmallBlindEBigBlind() {
		return new SmallBlindEBigBlind(smallBlindInicial, bigBlindInicial);
	}
}