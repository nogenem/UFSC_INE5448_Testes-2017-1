package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados;

public enum EstadoDoJogador {
	
	FORA_DO_TORNEIO("Eliminado"),
	FORA_DA_MESA("Fora da mesa"),
	FORA_DA_RODADA("Fora da rodada"),
	ESPERANDO_JOGADA("Esperando jogada"),
	AUMENTOU("Aumentou"),
	PAGOU("Pagou"),
	PAGOU_SMALL_BLIND("Pagou small blind"),
	PAGOU_BIG_BLIND("Pagou big blind"),
	PASSOU("Passou"),
	DESISTIU("Desistiu"),
	GANHOU_RODADA("Ganhou rodada"),
	GANHOU_TORNEIO("Ganhou torneio");
	
	private String textoDoEstado;
	
	EstadoDoJogador(String textoDoEstado) {
		this.textoDoEstado = textoDoEstado;
	}
	
	public String fornecerComoTexto() {
		return textoDoEstado;
	}
	
	public boolean estáEliminadoDaRodada() {
		return (this.equals(EstadoDoJogador.FORA_DO_TORNEIO) ||
				this.equals(EstadoDoJogador.FORA_DA_RODADA) ||
				this.equals(EstadoDoJogador.FORA_DA_MESA) ||
				this.equals(EstadoDoJogador.DESISTIU));
	}
	
	public boolean estáEliminadoDoTorneio() {
		return (this.equals(FORA_DO_TORNEIO));
	}
}