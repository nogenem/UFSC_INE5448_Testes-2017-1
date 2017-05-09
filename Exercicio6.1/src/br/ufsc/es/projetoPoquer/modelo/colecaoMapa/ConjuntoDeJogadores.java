package br.ufsc.es.projetoPoquer.modelo.colecaoMapa;

import java.util.HashSet;
import java.util.Set;

import br.ufsc.es.projetoPoquer.bancoDeDados.BancoDeDados;
import br.ufsc.es.projetoPoquer.modelo.jogador.TipoJogador;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao.JogadorFeijao;

public final class ConjuntoDeJogadores extends ConjuntoColecaoMapaAbstrato<NomeDeUsuario, TipoJogador, JogadorFeijao> {

	@Override
	protected Set<JogadorFeijao> fornecerObjetosFeijãoConcreto(Set<NomeDeUsuario> chavesDosValores) {
		Set<JogadorFeijao> jogadoresFeijão = new HashSet<JogadorFeijao>();
		for (NomeDeUsuario nomeDeUsuário : chavesDosValores) {
			jogadoresFeijão.add(BancoDeDados.fornecerInstância().fornecerJogador(nomeDeUsuário).fornecerConsulta());
		}
		
		return jogadoresFeijão;
	}
}