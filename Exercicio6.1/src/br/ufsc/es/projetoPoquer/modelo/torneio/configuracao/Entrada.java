package br.ufsc.es.projetoPoquer.modelo.torneio.configuracao;

public final class Entrada {
	
	private int entrada;
	
	public Entrada(int entrada) {
		this.entrada = Math.abs(entrada);
	}
	
	public int fornecerComoNúmero() {
		return entrada;
	}
}