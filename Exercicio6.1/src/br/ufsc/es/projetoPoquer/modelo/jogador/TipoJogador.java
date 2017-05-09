package br.ufsc.es.projetoPoquer.modelo.jogador;

import br.ufsc.es.projetoPoquer.modelo.Poquer.DinheiroFicticio;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.ChaveDeSecao;

public interface TipoJogador {
	
	public ChaveDeSecao gerarChaveDeSeção();
	
	public boolean validarChaveDeSeção(ChaveDeSecao outraChaveDeSeção);
	
	public DinheiroFicticio pagarEntradaNoTorneio(int valorDaEntrada);
	
	public void receberDinheiroFictício(DinheiroFicticio dinheiroFictício);
}