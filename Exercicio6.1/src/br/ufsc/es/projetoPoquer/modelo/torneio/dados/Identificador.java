package br.ufsc.es.projetoPoquer.modelo.torneio.dados;

public final class Identificador {
	
	private int identificador;
	
	public Identificador(int identificador) {
		this.identificador = Math.abs(identificador);
	}
	
	public int fornecerComoNúmero() {
		return identificador;
	}
	
	@Override
	public int hashCode() {
		return identificador;
	}
	
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Identificador) {
			return (((Identificador) objeto).identificador == identificador);
		}
		
		return false;
	}
}