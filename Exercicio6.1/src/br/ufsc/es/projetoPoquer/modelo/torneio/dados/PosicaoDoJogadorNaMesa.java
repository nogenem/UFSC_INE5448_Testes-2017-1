package br.ufsc.es.projetoPoquer.modelo.torneio.dados;

public final class PosicaoDoJogadorNaMesa {
	
	private int posição;
	
	public PosicaoDoJogadorNaMesa(int posição) {
		this.posição = posição;
	}
	
	public int fornecerComoNúmero() {
		return posição;
	}
}