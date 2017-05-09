package br.ufsc.es.projetoPoquer.modelo.colecaoMapa;

import java.util.HashSet;
import java.util.Set;

import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.JogadorFeijaoEmTorneio;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.JogadorEmTorneioDeUmaMesa;

public final class ConjuntoDeJogadoresEmTorneio extends ConjuntoColecaoMapaAbstrato<NomeDeUsuario, JogadorEmTorneioDeUmaMesa, JogadorFeijaoEmTorneio> {

	@Override
	protected Set<JogadorFeijaoEmTorneio> fornecerObjetosFeijãoConcreto(Set<NomeDeUsuario> chavesDosValores) {
		Set<JogadorFeijaoEmTorneio> jogadoresFeijãoEmTorneio = new HashSet<JogadorFeijaoEmTorneio>();
		for (NomeDeUsuario nomeDeUsuário : chavesDosValores) {
			JogadorFeijaoEmTorneio jogadorFeijãoEmTorneio = pegar(nomeDeUsuário).fornecerJogadorFeijãoEmTorneio(nomeDeUsuário);
			jogadoresFeijãoEmTorneio.add(jogadorFeijãoEmTorneio);
		}
		return jogadoresFeijãoEmTorneio;
	}
}