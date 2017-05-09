package br.ufsc.es.projetoPoquer.modelo.utilidades;

public class Sorteio {
	
	public static final int UM = 1;
	
	public static int sortear(int menorValorPossível, int maiorValorPossível) {
		menorValorPossível = Math.abs(menorValorPossível);
		maiorValorPossível = Math.abs(maiorValorPossível);
		if (menorValorPossível > maiorValorPossível) {
			throw new IllegalArgumentException();
		}
		int quantidadeDeValoresPossíveis = (maiorValorPossível - menorValorPossível + UM);
		
		return ((int) (Math.random()*quantidadeDeValoresPossíveis)) + menorValorPossível;
	}
}
