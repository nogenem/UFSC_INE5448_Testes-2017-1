package br.ufsc.es.projetoPoquer.modelo.torneio;

import br.ufsc.es.projetoPoquer.modelo.jogador.TipoJogador;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.TorneioFeijao;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.TorneioFeijaoCompleto;

public interface TipoTorneio<T extends TipoJogador> {
	
	public boolean inscreverJogador(NomeDeUsuario nomeDeUsuário, T jogador);
	
	public boolean desinscreverJogador(NomeDeUsuario nomeDeUsuário);
	
	public boolean receberAumento(NomeDeUsuario nomeDeUsuário, br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Aposta aposta);

	public boolean receberDesistência(NomeDeUsuario nomeDeUsuário);
	
	public boolean receberPagamento(NomeDeUsuario nomeDeUsuário);
	
	public boolean receberPasso(NomeDeUsuario nomeDeUsuário);
	
	public TorneioFeijao fornecerTorneioFeijão();
	
	public TorneioFeijaoCompleto fornecerTorneioFeijãoCompleto(NomeDeUsuario nomeDeUsuário);
	
	public boolean torneioNaoTemInscritosENaoComeçou();
}