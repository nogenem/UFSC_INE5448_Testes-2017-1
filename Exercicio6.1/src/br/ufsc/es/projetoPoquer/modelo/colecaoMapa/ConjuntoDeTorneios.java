package br.ufsc.es.projetoPoquer.modelo.colecaoMapa;

import java.util.HashSet;
import java.util.Set;

import br.ufsc.es.projetoPoquer.modelo.jogador.TipoJogador;
import br.ufsc.es.projetoPoquer.modelo.torneio.TipoTorneio;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.Identificador;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.TorneioFeijao;

public final class ConjuntoDeTorneios extends ConjuntoColecaoMapaAbstrato<Identificador, TipoTorneio<TipoJogador>, TorneioFeijao> {

	@Override
	protected Set<TorneioFeijao> fornecerObjetosFeijãoConcreto(Set<Identificador> chavesDosValores) {
		Set<TorneioFeijao> torneiosFeijão = new HashSet<TorneioFeijao>();
		for (Identificador identificador : chavesDosValores) {
			torneiosFeijão.add(pegar(identificador).fornecerTorneioFeijão());
		}
		
		return torneiosFeijão;
	}
}