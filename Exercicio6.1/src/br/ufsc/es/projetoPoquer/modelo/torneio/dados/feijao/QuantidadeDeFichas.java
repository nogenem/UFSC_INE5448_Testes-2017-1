package br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao;

public class QuantidadeDeFichas {
	
	private int quantidadeDeFichas;
	
	private static final int ZERO = 0;
	
	public QuantidadeDeFichas() {
		quantidadeDeFichas = ZERO;
	}
	
	public QuantidadeDeFichas(int quantidadeDeFichas) {
		this.quantidadeDeFichas = quantidadeDeFichas;
	}
	
	public int fornecerComoNúmero() {
		return quantidadeDeFichas;
	}
}