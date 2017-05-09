package br.ufsc.es.projetoPoquer.modelo.torneio.configuracao;

import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.TorneioFeijao;

public final class ConfiguracaoDeTorneioDeUmaMesa {
	
	private Blinds blinds;
	private Entrada entrada;
	private FichasInicial stackInicial;
	private TorneioFeijao torneioFeijão;
	//private Premiacao premiacao;
	//private Tempo esperaMaximaParaJogada;
	//private Tempo tempoDeIntervalo;
	//private Tempo tempoAtéOIntervalo;
	//private Tempo tempoEntreRodadas;
	//private Tempo tempoParaComeçarOTorneioApósVagasPreebchidas;
	private Vagas vagas;
	
	public ConfiguracaoDeTorneioDeUmaMesa(Blinds blinds, Entrada entrada, FichasInicial stackInicial, 
			Vagas vagas, TorneioFeijao torneioFeijão) {
		this.blinds = blinds;
		this.entrada = entrada;
		this.stackInicial = stackInicial;
		this.vagas = vagas;
		this.torneioFeijão = torneioFeijão;
	}
	
	public Blinds fornecerBlinds() {
		return blinds;
	}
	
	public Entrada fornecerEntrada() {
		return entrada;
	}
	
	public FichasInicial fornecerStackInicial() {
		return stackInicial;
	}
	
	public Vagas fornecerVagas() {
		return vagas;
	}
	
	public TorneioFeijao fornecerTorneioFeijão() {
		return torneioFeijão;
	}
}