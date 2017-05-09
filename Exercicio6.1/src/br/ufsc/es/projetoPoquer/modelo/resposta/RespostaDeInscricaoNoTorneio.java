package br.ufsc.es.projetoPoquer.modelo.resposta;


public final class RespostaDeInscricaoNoTorneio implements TipoResposta {
	
	private boolean erro;
	private String mensagemDeErro;
	
	private static final String JOGADOR_NÃO_AUTENTICADO = "O jogador não está online ou sua seção espirou. Faça o login novamente."; 
	private static final String TORNEIO_INEXISTENTE = "O torneio não existe ou já terminou.";
	private static final String VAGAS_INSUFICIENTES = "Não há mais vagas nesse torneio ou você não possui dinheiro fictício suficiente para pagar a inscrição.";
	
	public static RespostaDeInscricaoNoTorneio criarRespostaComErroDeJogadorNaoAutenticado() {
		return new RespostaDeInscricaoNoTorneio(JOGADOR_NÃO_AUTENTICADO);
	}
	
	public static RespostaDeInscricaoNoTorneio criarRespostaComErroDeTorneioInexistente() {
		return new RespostaDeInscricaoNoTorneio(TORNEIO_INEXISTENTE);
	}
	
	public static RespostaDeInscricaoNoTorneio criarRespostaComErroDeVagasInsuficientes() {
		return new RespostaDeInscricaoNoTorneio(VAGAS_INSUFICIENTES);
	}
	
	public static RespostaDeInscricaoNoTorneio criarRespostaDeInscriçãoRealizadaComSucesso() {
		return new RespostaDeInscricaoNoTorneio();
	}
	
	private RespostaDeInscricaoNoTorneio(String mensagemDeErro) {
		erro = true;
		this.mensagemDeErro = mensagemDeErro;
	}
	
	public RespostaDeInscricaoNoTorneio() {
		erro = false;
	}
	
	@Override
	public boolean possuiErro() {
		return erro;
	}

	@Override
	public String fornecerMensagemDeErro() {
		return mensagemDeErro;
	}
}