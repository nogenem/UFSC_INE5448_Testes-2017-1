package br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao;

public final class ValorDaEntrada {
	
	private int valorDaEntrada;
	
	public ValorDaEntrada(int valorDaEntrada) {
		this.valorDaEntrada = valorDaEntrada;
	}
	
	public int fornecerComoNúmero() {
		return valorDaEntrada;
	}
}