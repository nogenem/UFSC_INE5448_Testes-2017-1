package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo;

public enum RankingDeMao {

	CARTA_ALTA("Carta mais alta"), 
	PAR("Par"), 
	DOIS_PARES("Dois Pares"), 
	TRINCA("Trinca"), 
	STRAIGHT("Straight"), 
	FLUSH("Flush"), 
	FULL_HOUSE("Full House"), 
	QUADRA("Quadra"), 
	STRAIGHT_FLUSH("Straight Flush"), 
	ROYAL_FLUSH("Royal Flush");

	private final String nome;

	private RankingDeMao(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}
}