package br.ufsc.es.projetoPoquer.modelo.jogador.dados;

public final class ChaveDeSecao {
	
	private String chaveDeSeção;
	
	private static final int QUANTIDADE_DE_DÍGITOS = 32;
	private static final int QUANTIDADE_DE_NÚMEROS = 10;
	private static final int QUANTIDADE_DE_CARACTERES = 26;
	private static final int PRIMEIRO_CARACTERE = 65;
	private static final int ZERO = 0;
	private static final int DOIS = 2;
	
	public ChaveDeSecao() {
		gerarChaveDeSeção();
	}
	
	public ChaveDeSecao(String chaveDeSeção) {
		this.chaveDeSeção = chaveDeSeção;
	}
	
	public boolean éIgual(ChaveDeSecao outraChaveDeSeção) {
		return chaveDeSeção.equals(outraChaveDeSeção.chaveDeSeção);
	}

	public String fornecerComoTexto() {
		return chaveDeSeção;
	}
	
	private void gerarChaveDeSeção() {
		chaveDeSeção = "";
		for (int cont = ZERO; cont < QUANTIDADE_DE_DÍGITOS; cont++) {
			int número = (int) (Math.random()*QUANTIDADE_DE_NÚMEROS);
			String caractere = "";
			if (número%DOIS == ZERO) {
				caractere += (char) ((int) (Math.random()*QUANTIDADE_DE_CARACTERES) + PRIMEIRO_CARACTERE);
			} else {
				caractere += número;
			}
			chaveDeSeção = chaveDeSeção+caractere;
		}
	}
}