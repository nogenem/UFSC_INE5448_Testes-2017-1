package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo;

public enum AcaoDoJogador {
	AUMENTAR("aumentou para"),
	PAGAR("pagou"),
	PASSAR("passou"),
	DESISTIR("desistiu"),
	
	ENTRAR("entrou"),
	SAIR("saiu"),
	NENHUMA("");
	
	private final String valorTextual;
	
	AcaoDoJogador(String valorTextual) {
		this.valorTextual = valorTextual;
	}

	public String fornecerComoTexto() {
		return valorTextual;
	}
}