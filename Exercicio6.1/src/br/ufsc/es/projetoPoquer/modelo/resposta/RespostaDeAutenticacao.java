package br.ufsc.es.projetoPoquer.modelo.resposta;

import br.ufsc.es.projetoPoquer.modelo.jogador.dados.ChaveDeSecao;

public final class RespostaDeAutenticacao implements TipoResposta {

	private boolean erro;
	private ChaveDeSecao chaveDeSeção;
	private String mensagemDeErro;
	
	private static final String SENHA_INVÁLIDA = "Senha inválida.";
	
	private RespostaDeAutenticacao(ChaveDeSecao chaveDeSeção) {
		erro = false;
		this.chaveDeSeção = chaveDeSeção;
	}
	
	private RespostaDeAutenticacao(String mensagemDeErro) {
		erro = true;
		this.mensagemDeErro = mensagemDeErro;
	}
	
	public static RespostaDeAutenticacao criarRespostaComAutenticaçãoRealizadaComSucesso(ChaveDeSecao chaveDeSeção) {
		return new RespostaDeAutenticacao(chaveDeSeção);
	}
	
	public static RespostaDeAutenticacao criarRespostaComErroNaConsulta(String mensagemDeErro) {
		return new RespostaDeAutenticacao(mensagemDeErro);
	}
	
	public static RespostaDeAutenticacao criarRepostaComErroDeSenhaInválida() {
		return new RespostaDeAutenticacao(SENHA_INVÁLIDA);
	}

	@Override
	public boolean possuiErro() {
		return erro;
	}
	
	@Override
	public String fornecerMensagemDeErro() {
		return mensagemDeErro;
	}
	
	public ChaveDeSecao fornecerChaveDeSecao() {
		return chaveDeSeção;
	}
}