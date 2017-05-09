package br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao;

import br.ufsc.es.projetoPoquer.modelo.adminitracao.MantenedorFeijao;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.Identificador;

public final class TorneioFeijao {
	
	private Identificador identificador;
	private Descricao descrição;
	private ValorDaEntrada valorDaEntrada;
	private QuantidadeDeVagas quantidadeDeVagas;
	private MantenedorFeijao mantenedorCriador;
	
	public void fixarIdentificador(Identificador identificador) {
		this.identificador = identificador;
	}
	
	public void fixarDescricao(Descricao descrição) {
		this.descrição = descrição;
	}
	
	public void fixarValorDaEntrada(ValorDaEntrada valorDaEntrada) {
		this.valorDaEntrada = valorDaEntrada;
	}
	
	public void fixarQuantidadeDeVagas(QuantidadeDeVagas quantidadeDeVagas) {
		this.quantidadeDeVagas = quantidadeDeVagas;
	}
	
	public void fixarMantenedorCriador(MantenedorFeijao mantenedorCriador) {
		this.mantenedorCriador = mantenedorCriador;
	}
	
	public Identificador fornecerIdentificador() {
		return identificador;
	}
	
	public Descricao fornecerDescrição() {
		return descrição;
	}
	
	public ValorDaEntrada fornecerValorDaEntrada() {
		return valorDaEntrada;
	}
	
	public QuantidadeDeVagas fornecerQuantidadeDeVagas() {
		return quantidadeDeVagas;
	}
	
	public MantenedorFeijao fornecerMantenedorCriador() {
		return mantenedorCriador;
	}
}