package br.ufsc.es.projetoPoquer.modelo.torneio.configuracao;

public final class Vagas {
	
	private int quantidadeDeVagas;
	private int quantidadeDeVagasOcupadas;
	
	private static final int ZERO = 0;
	
	public Vagas(int quantidadeDeVagas) {
		this.quantidadeDeVagas = Math.abs(quantidadeDeVagas);
		quantidadeDeVagasOcupadas = ZERO;
	}
	
	public boolean ocuparVaga() {
		boolean háVaga = false;
		if (quantidadeDeVagasOcupadas < quantidadeDeVagas) {
			quantidadeDeVagasOcupadas++;
			háVaga = true;
		} 
		
		return háVaga;
	}
	
	public boolean desocuparVaga() {
		boolean estáVazio = true;
		if (quantidadeDeVagas > ZERO) {
			quantidadeDeVagas--;
			estáVazio = false;
		}
		
		return estáVazio;
	}
	
	public boolean possuiVaga() {
		return (quantidadeDeVagasOcupadas < quantidadeDeVagas);
	}
	
	public int fornecerQuantidadeDeVagas() {
		return quantidadeDeVagas;
	}
	
	public int fornecerQuantidadeDeVagasOcupadas() {
		return quantidadeDeVagasOcupadas;
	}
}