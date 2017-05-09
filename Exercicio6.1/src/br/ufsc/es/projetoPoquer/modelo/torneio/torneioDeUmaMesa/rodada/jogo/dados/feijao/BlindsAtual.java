package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.feijao;

public class BlindsAtual {
	
	private int smallBlind;
	private int bigBlind;
	
	public BlindsAtual(int smallBlind, int bigBlind) {
		this.smallBlind = smallBlind;
		this.bigBlind = bigBlind;
	}
	
	public String fornecerComoTexto() {
		return smallBlind+"/"+bigBlind;
	}
}