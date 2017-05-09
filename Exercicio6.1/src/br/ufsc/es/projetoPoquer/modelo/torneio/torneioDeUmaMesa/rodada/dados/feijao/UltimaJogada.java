package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.feijao;

import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.AcaoDoJogador;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Jogada;

public final class UltimaJogada {
	
	private String nomeDeUsuário;
	private Jogada jogada;
	
	public UltimaJogada(String nomeDeUsuário, Jogada jogada) {
		this.nomeDeUsuário = nomeDeUsuário;
		this.jogada = jogada;
	} 
	
	public String fornecerComoTexto() {
		if ((nomeDeUsuário == null) || (jogada.fornecerAçãoDoJogador().equals(AcaoDoJogador.NENHUMA))) {
			return "Nenhuma jogada";
		}
		String texto = nomeDeUsuário + " " + jogada.fornecerAçãoDoJogador().fornecerComoTexto();
		if (jogada.fornecerAçãoDoJogador().equals(AcaoDoJogador.AUMENTAR)) {
			texto += " " + jogada.fornecerApostaComoNúmero();
		}
		
		return texto;
	}
}