package br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao;

public final class Nome {
	
	private String nome;
	
	public Nome(String nome) {
		this.nome = nome;
	}
	
	public String fornecerComoTexto() {
		return nome;
	}
}