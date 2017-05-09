package br.ufsc.es.projetoPoquer.modelo.colecaoMapa;

import java.util.LinkedList;
import java.util.Queue;

import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.JogadorFeijaoEmTorneio;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.JogadorEmTorneioDeUmaMesa;

public class FilaDeJogadoresEmTorneio extends FilaColecaoMapaAbstrato<NomeDeUsuario, JogadorEmTorneioDeUmaMesa, JogadorFeijaoEmTorneio> {

	@Override
	protected final Queue<JogadorFeijaoEmTorneio> fornecerObjetosFeijãoConcreto(Queue<NomeDeUsuario> chavesDosValores) {
		Queue<JogadorFeijaoEmTorneio> jogadoresFeijãoEmTorneio = new LinkedList<JogadorFeijaoEmTorneio>();
		for (NomeDeUsuario nomeDeUsuário : chavesDosValores) {
			JogadorFeijaoEmTorneio jogadorFeijãoEmTorneio = pegar(nomeDeUsuário).fornecerJogadorFeijãoEmTorneio(nomeDeUsuário);
			jogadoresFeijãoEmTorneio.add(jogadorFeijãoEmTorneio);
		}
		
		return jogadoresFeijãoEmTorneio;
	}
}