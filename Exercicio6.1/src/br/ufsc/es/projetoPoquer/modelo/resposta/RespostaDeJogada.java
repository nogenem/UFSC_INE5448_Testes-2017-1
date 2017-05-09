package br.ufsc.es.projetoPoquer.modelo.resposta;

public class RespostaDeJogada {
	
	private boolean erro;
	private String mensagemDeErro;
	
	private static final String JOGADA_INVÁLIDA = "Jogada inválida";
	
	private RespostaDeJogada() {
		mensagemDeErro = "";
		erro = false;
	}
	
	private RespostaDeJogada(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
		erro = true;
	}
	
	public boolean possuiErro() {
		return erro;
	}
	
	public String fornecerMensagemDeErro() {
		return mensagemDeErro;
	}
	
	public static RespostaDeJogada criarRespostaComJogadaInválida() {
		return new RespostaDeJogada(JOGADA_INVÁLIDA);
	}
	
	public static RespostaDeJogada criarRespostaDeJogadaComSucesso() {
		return new RespostaDeJogada();
	}
}