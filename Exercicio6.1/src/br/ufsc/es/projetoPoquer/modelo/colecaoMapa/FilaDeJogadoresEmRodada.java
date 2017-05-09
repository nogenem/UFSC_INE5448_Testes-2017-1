package br.ufsc.es.projetoPoquer.modelo.colecaoMapa;

import java.util.LinkedList;
import java.util.Queue;

import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.feijao.JogadorFeijaoEmRodada;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.rodadaComum.JogadorEmRodadaComum;

public class FilaDeJogadoresEmRodada extends FilaColecaoMapaAbstrato<NomeDeUsuario, JogadorEmRodadaComum, JogadorFeijaoEmRodada> {
	 
	@Override
	protected Queue<JogadorFeijaoEmRodada> fornecerObjetosFeijãoConcreto(Queue<NomeDeUsuario> chavesDosValores) {
		Queue<JogadorFeijaoEmRodada> jogadoresFeijãoEmRodada = new LinkedList<JogadorFeijaoEmRodada>();
		for (NomeDeUsuario nomeDeUsuário : chavesDosValores) {
			jogadoresFeijãoEmRodada.add(pegar(nomeDeUsuário).fornecerJogadorFeijãoEmRodada());
		}
		
		return jogadoresFeijãoEmRodada;
	}
}