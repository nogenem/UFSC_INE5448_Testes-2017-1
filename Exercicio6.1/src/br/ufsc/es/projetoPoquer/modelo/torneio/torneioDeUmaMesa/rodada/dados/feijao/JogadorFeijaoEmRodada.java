package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.feijao;

import java.util.List;

import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.feijao.AcoesPossiveisFeijaoDoJogador;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.feijao.CartaFeijao;

public final class JogadorFeijaoEmRodada {
	
	private List<CartaFeijao> cartasFeijão;
	private AcoesPossiveisFeijaoDoJogador açõesPossíveisFeijãoDoJogador;
	
	public void fixarCartasFeijão(List<CartaFeijao> cartasFeijão) {
		this.cartasFeijão = cartasFeijão;
	}
	
	public void fixarAçõesPossívelsFeijãoDoJogador(AcoesPossiveisFeijaoDoJogador açõesPossíveisFeijãoDoJogador) {
		this.açõesPossíveisFeijãoDoJogador = açõesPossíveisFeijãoDoJogador;
	}
	
	public List<CartaFeijao> fornecerCartasFeijão() {
		return cartasFeijão;
	}
	
	public AcoesPossiveisFeijaoDoJogador fornecerAçõesPossíveisFeijãoDoJogador() {
		return açõesPossíveisFeijãoDoJogador;
	}
}