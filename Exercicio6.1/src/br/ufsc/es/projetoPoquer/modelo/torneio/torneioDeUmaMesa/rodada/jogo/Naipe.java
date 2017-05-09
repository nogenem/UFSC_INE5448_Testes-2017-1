package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo;

public enum Naipe {
	
	ESPADAS("Espadas"),
	COPAS("Copas"),
	PAUS("Paus"),
	OURO("Ouro");
	
	private final String valorTextual;
	
	Naipe(String valorTextual) {
		this.valorTextual = valorTextual;
	}
	
	@Override
	public String toString() {
		return valorTextual;
	}
}