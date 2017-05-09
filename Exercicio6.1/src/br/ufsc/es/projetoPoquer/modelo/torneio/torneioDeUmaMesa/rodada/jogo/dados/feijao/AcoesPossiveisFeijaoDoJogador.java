package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.feijao;

import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Aposta;

public class AcoesPossiveisFeijaoDoJogador {
	
	private boolean podeAumentar;
	private boolean podePagar;
	private boolean podePassar;
	private boolean podeDesistir;
	private Aposta apostaMínima;
	private Aposta apostaMáxima;
	private boolean estáNaVez;
	
	public void fixarSePodeAumentar(boolean podeAumentar) {
		this.podeAumentar = podeAumentar;
	}
	
	public void fixarSePodePagar(boolean podePagar) {
		this.podePagar = podePagar;
	}
	
	public void fixarSePodePassar(boolean podePassar) {
		this.podePassar = podePassar;
	}
	
	public void fixarSePodeDesistir(boolean podeDesistir) {
		this.podeDesistir = podeDesistir;
	}
	
	public void fixarApostaMínima(Aposta apostaMínima) {
		this.apostaMínima = apostaMínima;
	}
	
	public void fixarApostaMáxima(Aposta apostaMáxima) {
		this.apostaMáxima = apostaMáxima;
	}
	
	public void fixarSeEstáNaVez(boolean estáNaVez) {
		this.estáNaVez = estáNaVez;
	}
	
	public boolean podeAumentar() {
		return podeAumentar;
	}
	
	public boolean podePagar() {
		return podePagar;
	}
	
	public boolean podePassar() {
		return podePassar;
	}
	
	public boolean podeDeisitir() {
		return podeDesistir;
	}
	
	public Aposta fornecerApostaMínima() {
		return apostaMínima;
	}
	
	public Aposta fornecerApostaMáxima() {
		return apostaMáxima;
	}
	
	public boolean estáNaVez() {
		return estáNaVez;
	}
}