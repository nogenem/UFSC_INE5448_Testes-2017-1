package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo;

import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;

public final class Jogada {
	
	private AcaoDoJogador açãoDoJogador;
	private Aposta aposta;
	private NomeDeUsuario nomeDeUsuário;
	
	public Jogada() {
		açãoDoJogador = AcaoDoJogador.NENHUMA;
		aposta = new Aposta();
		nomeDeUsuário = new NomeDeUsuario("");
	}
	
	public Jogada(AcaoDoJogador açãoDoJogador, Aposta aposta, NomeDeUsuario nomeDeUsuário) {
		this.açãoDoJogador = açãoDoJogador;
		this.aposta = aposta;
		this.nomeDeUsuário = nomeDeUsuário;
	}
	
	public Jogada(AcaoDoJogador açãoDoJogador, Aposta aposta) {
		this(açãoDoJogador, aposta, new NomeDeUsuario(""));
	}
	
	public AcaoDoJogador fornecerAçãoDoJogador() {
		return açãoDoJogador;
	}
	
	public int fornecerApostaComoNúmero() {
		return aposta.fornecerComoNúmero();
	}
	
	public String fornecerNomeDeUsuárioComTexto() {
		return nomeDeUsuário.fornecerComoTexto();
	}
}