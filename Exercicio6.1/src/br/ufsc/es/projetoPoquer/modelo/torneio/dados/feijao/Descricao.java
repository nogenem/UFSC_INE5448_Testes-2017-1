package br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao;

public final class Descricao {
	
	private String descrição;
	
	public Descricao(String descrição) {
		this.descrição = descrição;
	}
	
	public String fornecerComoTexto() {
		return descrição;
	}
}