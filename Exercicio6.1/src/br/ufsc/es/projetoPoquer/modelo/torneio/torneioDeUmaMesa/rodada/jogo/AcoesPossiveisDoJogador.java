package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo;

import java.util.HashSet;
import java.util.Set;

import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.feijao.AcoesPossiveisFeijaoDoJogador;


public class AcoesPossiveisDoJogador {
	
	private Set<AcaoDoJogador> açõesPossíveisDoJogador;
	private Aposta apostaMínima;
	private Aposta apostaMáxima;
	
	public AcoesPossiveisDoJogador() {
		apostaMínima = new Aposta();
		apostaMáxima = new Aposta();
		açõesPossíveisDoJogador = new HashSet<AcaoDoJogador>();
	}
	
	public void permitirAção(AcaoDoJogador açãoDoJogador) {
		açõesPossíveisDoJogador.add(açãoDoJogador);
	}
	
	public boolean verificiarSeAçãoÉVálida(Jogada jogada) {
		AcaoDoJogador açãoDoJogador = jogada.fornecerAçãoDoJogador();
		if (açõesPossíveisDoJogador.contains(açãoDoJogador)) {
			if (açãoDoJogador.equals(AcaoDoJogador.AUMENTAR) || açãoDoJogador.equals(AcaoDoJogador.PAGAR)) {
				return verificarSeApostaÉVálida(jogada);
			}
			
			return true;
		}
		
		return false;
	}

	private boolean verificarSeApostaÉVálida(Jogada jogada) {
		int valorDaAposta = jogada.fornecerApostaComoNúmero();
		int valorDaApostaMínima = apostaMínima.fornecerComoNúmero();
		int valorDaApostaMáxima = apostaMáxima.fornecerComoNúmero();
		
		return ((valorDaApostaMínima <= valorDaAposta) && (valorDaAposta <= valorDaApostaMáxima)); 
	}
	
	public void fixarApostaMínima(Aposta aposta) {
		this.apostaMínima = aposta;
	}
	
	public void fixarApostaMáxima(Aposta aposta) {
		this.apostaMáxima = aposta;
	}
	
	public void limparAçõesPossíveis() {
		açõesPossíveisDoJogador = new HashSet<AcaoDoJogador>();
	}
	
	public int fornecerApostaMínimaComoNúmero() {
		return apostaMínima.fornecerComoNúmero();
	}
	
	public AcoesPossiveisFeijaoDoJogador fornecerAcõesPossíveisFeijãoDoJogador(int quantidadeDeFichasDoJogador) {
		Aposta apostaDoJogador = new Aposta(quantidadeDeFichasDoJogador);
		AcoesPossiveisFeijaoDoJogador açõesPossíveisFeijãoDoJogador = new AcoesPossiveisFeijaoDoJogador();
		açõesPossíveisFeijãoDoJogador.fixarApostaMínima(apostaMínima);
		açõesPossíveisFeijãoDoJogador.fixarApostaMáxima(apostaMáxima);
		if (verificiarSeAçãoÉVálida(new Jogada(AcaoDoJogador.AUMENTAR, apostaDoJogador))) {
			açõesPossíveisFeijãoDoJogador.fixarSePodeAumentar(true);
		} else {
			açõesPossíveisFeijãoDoJogador.fixarSePodeAumentar(false);
		}
		if (verificiarSeAçãoÉVálida(new Jogada(AcaoDoJogador.PAGAR, apostaDoJogador))) {
			açõesPossíveisFeijãoDoJogador.fixarSePodePagar(true);
		} else {
			açõesPossíveisFeijãoDoJogador.fixarSePodePagar(false);
		}
		if (verificiarSeAçãoÉVálida(new Jogada(AcaoDoJogador.PASSAR, apostaDoJogador))) {
			açõesPossíveisFeijãoDoJogador.fixarSePodePassar(true);
		} else {
			açõesPossíveisFeijãoDoJogador.fixarSePodePassar(false);
		}
		if (verificiarSeAçãoÉVálida(new Jogada(AcaoDoJogador.DESISTIR, apostaDoJogador))) {
			açõesPossíveisFeijãoDoJogador.fixarSePodeDesistir(true);
		} else {
			açõesPossíveisFeijãoDoJogador.fixarSePodeDesistir(false);
		}
		
		return açõesPossíveisFeijãoDoJogador;
	}
}