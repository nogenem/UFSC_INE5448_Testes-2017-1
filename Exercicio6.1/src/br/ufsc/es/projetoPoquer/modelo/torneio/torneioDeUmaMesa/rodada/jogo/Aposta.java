package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo;
 
public final class Aposta {
	
	private int valorDaAposta;
	
	private static final int ZERO = 0; 
	
	public Aposta() {
		valorDaAposta = ZERO;
	}
	
	public Aposta(int valorDaAposta) {
		this.valorDaAposta = valorDaAposta;
	}
	
	public int fornecerComoNúmero() {
		return valorDaAposta;
	}
}