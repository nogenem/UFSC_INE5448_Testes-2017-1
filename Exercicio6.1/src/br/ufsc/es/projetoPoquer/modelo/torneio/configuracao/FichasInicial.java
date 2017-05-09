package br.ufsc.es.projetoPoquer.modelo.torneio.configuracao;

public class FichasInicial {
	
	private int quantidade;
	
	public FichasInicial(int quantidade) {
		this.quantidade = Math.abs(quantidade);
	}
	
	public int fornecerComoNúmero() {
		return quantidade;
	}
}