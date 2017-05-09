package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.feijao;

import java.util.LinkedList;
import java.util.List;

import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao.JogadorFeijao;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.PosicaoDoJogadorNaMesa;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.QuantidadeDeFichas;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Jogada;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.feijao.BlindsAtual;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.feijao.CartaFeijao;

public final class RodadaFeijao {

	private JogadorFeijao jogadorDaVez;
	private PosicaoDoJogadorNaMesa posiçãoDoJogadorDealerNaMesa;
	private List<CartaFeijao> cartasFeijão;
	private QuantidadeDeFichas valorDoPote;
	private UltimaJogada últimaJogada;
	private BlindsAtual blinds;
	private String vencedores;
	
	public RodadaFeijao() {
		jogadorDaVez = new JogadorFeijao();
		posiçãoDoJogadorDealerNaMesa = new PosicaoDoJogadorNaMesa(10);
		cartasFeijão = new LinkedList<CartaFeijao>();
		valorDoPote = new QuantidadeDeFichas();
	}
	
	public void fixarJogadorDaVez(JogadorFeijao jogadorDaVez) {
		if (jogadorDaVez == null) {
			jogadorDaVez = new JogadorFeijao();
			jogadorDaVez.fixarNomeDeUsuário(new NomeDeUsuario(""));
		}
		this.jogadorDaVez = jogadorDaVez;
	}
	
	public void fixarPosiçãoDoJogadorDealerNaMesa(PosicaoDoJogadorNaMesa posiçãoDoJogadorDealerNaMesa) {
		this.posiçãoDoJogadorDealerNaMesa = posiçãoDoJogadorDealerNaMesa;
	}
	
	public void fixarCartasFeijão(List<CartaFeijao> cartasFeijão) {
		this.cartasFeijão = cartasFeijão;
	}
	
	public void fixarValorDoPote(QuantidadeDeFichas valorDoPote) {
		this.valorDoPote = valorDoPote;
	}
	
	public void fixarÚltimaJogada(UltimaJogada últimaJogada) {
		if (últimaJogada == null) {
			últimaJogada = new UltimaJogada("", new Jogada());
		}
		this.últimaJogada = últimaJogada;
	}
	
	public void fixarBlindsAtual(BlindsAtual blinds) {
		this.blinds = blinds;
	}
	
	public void fixarVencedores(String vencedores) {
		this.vencedores = vencedores;
	}
	
	public JogadorFeijao fornecerJogadorDaVez() {
		return jogadorDaVez;
	}
	
	public PosicaoDoJogadorNaMesa fornecerPosiçãoDoJogadorDealerNaMesa() {
		return posiçãoDoJogadorDealerNaMesa;
	}
	
	public List<CartaFeijao> fornecerCartasFeijão() {
		return cartasFeijão;
	}
	
	public QuantidadeDeFichas fornecerValorDoPote() {
		return valorDoPote;
	}
	
	public UltimaJogada fornecerÚltimaJogada() {
		return últimaJogada;
	}
	
	public BlindsAtual fornecerBlinds() {
		return blinds;
	}
	
	public String fornecerVencedores() {
		return vencedores;
	}
}