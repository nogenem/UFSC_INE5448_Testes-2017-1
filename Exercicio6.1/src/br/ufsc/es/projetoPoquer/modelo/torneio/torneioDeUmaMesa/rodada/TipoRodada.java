package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada;

import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.feijao.JogadorFeijaoEmRodada;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.feijao.RodadaFeijao;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Aposta;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.rodadaComum.JogadorEmRodadaComum;

public interface TipoRodada extends Runnable {
	
	public void receberJogadorEmRodada(NomeDeUsuario nomeDeUsuário, JogadorEmRodadaComum jogadorEmRodada);
	
	public JogadorFeijaoEmRodada fornecerJogadorFeijãoEmRodada(NomeDeUsuario nomeDeUsuário);
	
	public RodadaFeijao fornecerRodadaFeijão();

	boolean receberAumento(NomeDeUsuario nomeDeUsuário, Aposta aposta);

	boolean receberDesistência(NomeDeUsuario nomeDeUsuário);

	boolean receberPagamento(NomeDeUsuario nomeDeUsuário);

	boolean receberPasso(NomeDeUsuario nomeDeUsuário);
}
