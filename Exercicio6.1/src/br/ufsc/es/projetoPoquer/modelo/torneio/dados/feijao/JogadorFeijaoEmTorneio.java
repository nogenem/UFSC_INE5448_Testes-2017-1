package br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao;

import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.PosicaoDoJogadorNaMesa;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.EstadoDoJogador;

public class JogadorFeijaoEmTorneio {
	
	private NomeDeUsuario nomeDeUsuário;
	private QuantidadeDeFichas quantidadeDeFichas;
	private QuantidadeDeFichas quantidadeDeFichasNaMesa;
	private EstadoDoJogador estadoDoJogador;
	private PosicaoDoJogadorNaMesa posiçãoDoJogadorNaMesa;
	
	public void fixarNomeDeUsuário(NomeDeUsuario nomeDeUsuário) {
		this.nomeDeUsuário = nomeDeUsuário;
	}
	
	public void fixarQuantidadeDeFichas(QuantidadeDeFichas quantidadeDeFichas) {
		this.quantidadeDeFichas = quantidadeDeFichas;
	}
	
	public void fixarQuantidadeDeFichasNaMesa(QuantidadeDeFichas quantidadeDeFichasNaMesa) {
		this.quantidadeDeFichasNaMesa = quantidadeDeFichasNaMesa;
	}
	
	public void fixarEstadoDoJogador(EstadoDoJogador estadoDoJogador) {
		this.estadoDoJogador = estadoDoJogador;
	}
	
	public void fixarPosiçãoDoJogadorNaMesa(PosicaoDoJogadorNaMesa posiçãoDoJogadorNaMesa) {
		this.posiçãoDoJogadorNaMesa = posiçãoDoJogadorNaMesa;
	}
	
	public NomeDeUsuario fornecerNomeDeUsuário() {
		return nomeDeUsuário;
	}
	
	public QuantidadeDeFichas fornecerQuantidadeDeFichas() {
		return quantidadeDeFichas;
	}
	
	public QuantidadeDeFichas fornecerQuantidadeDeFichasNaMesa() {
		return quantidadeDeFichasNaMesa;
	}
	
	public EstadoDoJogador fornecerEstadoDoJogador() {
		return estadoDoJogador;
	}
	
	public PosicaoDoJogadorNaMesa fornecerPosiçãoDoJogadorNaMesa() {
		return posiçãoDoJogadorNaMesa;
	}
}