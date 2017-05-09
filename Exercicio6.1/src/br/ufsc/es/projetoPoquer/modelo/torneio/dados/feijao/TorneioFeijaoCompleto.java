package br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao;

import java.util.Collection;

import br.ufsc.es.projetoPoquer.modelo.torneio.dados.PosicaoDoJogadorNaMesa;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.feijao.JogadorFeijaoEmRodada;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.feijao.RodadaFeijao;

public final class TorneioFeijaoCompleto {
	
	private Collection<JogadorFeijaoEmTorneio> jogadoresFeijãoEmTorneio;
	private JogadorFeijaoEmRodada jogadorFeijãoEmRodada;
	private RodadaFeijao rodadaFeijão;
	private PosicaoDoJogadorNaMesa posiçãoDoDealerNaMesa;
	
	public void fixarJogadoresFeijãoEmTorneio(Collection<JogadorFeijaoEmTorneio> jogadoresFeijãoEmTorneio) {
		this.jogadoresFeijãoEmTorneio = jogadoresFeijãoEmTorneio;
	}
	
	public void fixarJogadorFeijãoEmRodada(JogadorFeijaoEmRodada jogadorFeijãoEmRodada) {
		this.jogadorFeijãoEmRodada = jogadorFeijãoEmRodada;
	}
	
	public void fixarRodadaFeijão(RodadaFeijao rodadaFeijão) {
		this.rodadaFeijão = rodadaFeijão;
	}

	public void fixarPosiçãoDoDealerNaMesa(PosicaoDoJogadorNaMesa posiçãoDoDealerNaMesa) {
		this.posiçãoDoDealerNaMesa = posiçãoDoDealerNaMesa;
	}
	
	public Collection<JogadorFeijaoEmTorneio> fornecerJogadoresFeijãoEmTorneio() {
		return jogadoresFeijãoEmTorneio;
	}
	
	public JogadorFeijaoEmRodada fornecerJogadorFeijãoEmRodada() {
		return jogadorFeijãoEmRodada;
	}
	
	public RodadaFeijao fornecerRodadaFeijão() {
		return rodadaFeijão;
	}
	
	public PosicaoDoJogadorNaMesa fornecerPosiçãoDoDealerNaMesa() {
		return posiçãoDoDealerNaMesa;
	}
}