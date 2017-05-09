package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa;

import br.ufsc.es.projetoPoquer.modelo.Poquer.DinheiroFicticio;
import br.ufsc.es.projetoPoquer.modelo.jogador.TipoJogador;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.PosicaoDoJogadorNaMesa;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.JogadorFeijaoEmTorneio;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.TorneioDeUmaMesa.Fichas;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.TipoRodada;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.rodadaComum.JogadorEmRodadaComum;

public class JogadorEmTorneioDeUmaMesa {
	
	private TipoJogador jogador;
	private JogadorEmRodadaComum jogadorEmRodada;
	private PosicaoDoJogadorNaMesa posiçãoDoJogadorNaMesa;
	
	public JogadorEmTorneioDeUmaMesa(TipoJogador jogador, Fichas fichas) {
		this.jogador = jogador;
		this.jogadorEmRodada = new JogadorEmRodadaComum(fichas);
	}
	
	public void receberPrêmio(DinheiroFicticio dinheiroFictício) {
		jogador.receberDinheiroFictício(dinheiroFictício);
	}
	
	public void receberDinheiroDaInscriçãoDeVolta(DinheiroFicticio dinheiroFictício) {
		jogador.receberDinheiroFictício(dinheiroFictício);
	}
	
	public void entrarEmRodada(NomeDeUsuario nomeDeUsuário ,TipoRodada rodada) {
		rodada.receberJogadorEmRodada(nomeDeUsuário, jogadorEmRodada);
	}
	
	public JogadorFeijaoEmTorneio fornecerJogadorFeijãoEmTorneio(NomeDeUsuario nomeDeUsuário) {
		JogadorFeijaoEmTorneio jogadorFeijãoEmTorneio = jogadorEmRodada.montarJogadorFeijãoEmTorneio();
		jogadorFeijãoEmTorneio.fixarNomeDeUsuário(nomeDeUsuário);
		jogadorFeijãoEmTorneio.fixarPosiçãoDoJogadorNaMesa(posiçãoDoJogadorNaMesa);
		
		return jogadorFeijãoEmTorneio;
	}
	
	protected void fixarPosiçãoDoJogadorNaMesa(PosicaoDoJogadorNaMesa posiçãoDoJogadorNaMesa) {
		this.posiçãoDoJogadorNaMesa = posiçãoDoJogadorNaMesa;
	}
	
	protected int fornecerPosiçãoDoJogadorNaMesaComoNúmero() {
	 	return posiçãoDoJogadorNaMesa.fornecerComoNúmero(); 
	}
	
	public boolean estáEliminado() {
		return jogadorEmRodada.estáEliminadoDoTorneio();
	}
}