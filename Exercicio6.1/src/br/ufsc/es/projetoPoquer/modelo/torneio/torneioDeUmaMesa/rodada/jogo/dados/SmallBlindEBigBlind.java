package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados;

public class SmallBlindEBigBlind {

	private int smallBlind;
	private int bigBlind;
	
	public SmallBlindEBigBlind(int smallBlind, int bigBlind) {
		this.smallBlind = smallBlind;
		this.bigBlind = bigBlind;
	}
	
	public int fornecerSmallBlindComoNúmero() {
		return smallBlind;
	}
	
	public int fornecerBigBlindComoNúmero() {
		return bigBlind;
	}
}