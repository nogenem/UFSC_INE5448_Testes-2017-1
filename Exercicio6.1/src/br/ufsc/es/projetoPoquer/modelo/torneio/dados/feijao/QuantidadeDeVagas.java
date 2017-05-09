package br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao;

public final class QuantidadeDeVagas {
	
	private int totalDeVagas;
	private int vagasDisponíveis;
	
	public QuantidadeDeVagas(int totalDeVagas, int vagasDisponíveis) {
		this.totalDeVagas = totalDeVagas;
		this.vagasDisponíveis = vagasDisponíveis;
	}
	
	public int fornecerVagasOcupadas() {
		return totalDeVagas-vagasDisponíveis;
	}
	
	public int fornecerVagasDisponíveis() {
		return vagasDisponíveis;
	}
	
	public String fornecerComoTexto() {
		return vagasDisponíveis+"/"+totalDeVagas;
	}
}