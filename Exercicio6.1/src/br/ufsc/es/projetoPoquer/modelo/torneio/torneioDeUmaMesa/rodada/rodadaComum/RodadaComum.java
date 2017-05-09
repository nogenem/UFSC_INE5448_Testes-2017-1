package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.rodadaComum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.ufsc.es.projetoPoquer.modelo.colecaoMapa.FilaDeJogadoresEmRodada;
import br.ufsc.es.projetoPoquer.modelo.colecaoMapa.ListaDeCartas;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao.JogadorFeijao;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.TorneioDeUmaMesa.Fichas;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.TipoRodada;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.EstadoDaRodada;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.EstadoDoJogador;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.feijao.JogadorFeijaoEmRodada;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.feijao.RodadaFeijao;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.feijao.UltimaJogada;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.AcaoDoJogador;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.AcoesPossiveisDoJogador;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.AnalisadorDeJogos;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Aposta;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Carta;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Dealer;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Jogada;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Mao;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.SmallBlindEBigBlind;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.feijao.AcoesPossiveisFeijaoDoJogador;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.feijao.BlindsAtual;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.feijao.CartaFeijao;

public class RodadaComum implements TipoRodada {
	
	private final FilaDeJogadoresEmRodada jogadoresEmRodada;
	private ListaDeCartas cartasAbertas;
	private Fichas pote;
	private final SmallBlindEBigBlind smallBlindEBigBlind;
	private final Dealer dealer;
	private Jogada jogadaDoÚltimoJogador;
	private AcoesPossiveisDoJogador açõesPossíveisDoJogador;
	private EstadoDaRodada estadoDaRodada;
	private String vencedores = "";
	
	private static final int ZERO = 0;
	private static final int UM = 1;
	private static final int DOIS = 2;
	
	public RodadaComum(SmallBlindEBigBlind smallBlindEBigBlind, Dealer dealer, Fichas pote) {
		jogadoresEmRodada = new FilaDeJogadoresEmRodada();
		cartasAbertas = new ListaDeCartas();
		this.pote = pote;
		this.smallBlindEBigBlind = smallBlindEBigBlind;
		this.dealer = dealer;
		jogadaDoÚltimoJogador = new Jogada();
		açõesPossíveisDoJogador = new AcoesPossiveisDoJogador();
		estadoDaRodada = EstadoDaRodada.NÃO_INICIADA;
	}
	
	@Override
	public void run() {
		for (NomeDeUsuario nomeDeUsuário : jogadoresEmRodada) {
			JogadorEmRodadaComum jogadorEmRodada = jogadoresEmRodada.pegar(nomeDeUsuário);
			if (!jogadorEmRodada.estáEliminadoDoTorneio()) {
				jogadorEmRodada.apostar(EstadoDoJogador.ESPERANDO_JOGADA, new Jogada(AcaoDoJogador.NENHUMA, new Aposta()));
			} 
		}
		jogarPréFlop();
		jogarFlop();
		jogarTurn();
		jogarRiver();
		mostrarShowdown();
	}

	@Override
	public void receberJogadorEmRodada(NomeDeUsuario nomeDeUsuário, JogadorEmRodadaComum jogadorEmRodada) {
		jogadoresEmRodada.adicionar(nomeDeUsuário, jogadorEmRodada);
	}
	
	@Override
	public RodadaFeijao fornecerRodadaFeijão() {
		RodadaFeijao rodadaFeijão = new RodadaFeijao();
		JogadorFeijao jogadorFeijãoDaVez = new JogadorFeijao();
		jogadorFeijãoDaVez.fixarNomeDeUsuário(jogadoresEmRodada.verPrimeiro());
		rodadaFeijão.fixarJogadorDaVez(jogadorFeijãoDaVez);
		rodadaFeijão.fixarCartasFeijão(cartasAbertas.fornecerCartasFeijão());
		rodadaFeijão.fixarBlindsAtual(new BlindsAtual(smallBlindEBigBlind.fornecerSmallBlindComoNúmero(), 
				smallBlindEBigBlind.fornecerBigBlindComoNúmero()));
		rodadaFeijão.fixarValorDoPote(pote.fornecerQuantidadeDeFichas());
		rodadaFeijão.fixarÚltimaJogada(new UltimaJogada(jogadaDoÚltimoJogador.fornecerNomeDeUsuárioComTexto(), jogadaDoÚltimoJogador));
		rodadaFeijão.fixarVencedores(vencedores);
		
		return rodadaFeijão;
	}
	
	@Override
	public JogadorFeijaoEmRodada fornecerJogadorFeijãoEmRodada(NomeDeUsuario nomeDeUsuário) {
		JogadorFeijaoEmRodada jogadorFeijãoEmRodada = new JogadorFeijaoEmRodada();
		AcoesPossiveisFeijaoDoJogador açõesPossiveisFeijãoDoJogador = new AcoesPossiveisFeijaoDoJogador();
		açõesPossiveisFeijãoDoJogador.fixarSeEstáNaVez(false);
		if (começou() && jogadoresEmRodada.contém(nomeDeUsuário)) {
			jogadorFeijãoEmRodada = jogadoresEmRodada.pegar(nomeDeUsuário).fornecerJogadorFeijãoEmRodada();
			açõesPossiveisFeijãoDoJogador.fixarSeEstáNaVez(false);
			if (jogadoresEmRodada.verPrimeiro().equals(nomeDeUsuário)) {
				açõesPossiveisFeijãoDoJogador = açõesPossíveisDoJogador.fornecerAcõesPossíveisFeijãoDoJogador(jogadoresEmRodada
						.pegar(nomeDeUsuário).fornecerQuantidadeDeFichasComoNúmero());
				açõesPossiveisFeijãoDoJogador.fixarSeEstáNaVez(true);
			}
		}
		jogadorFeijãoEmRodada.fixarAçõesPossívelsFeijãoDoJogador(açõesPossiveisFeijãoDoJogador);

		return jogadorFeijãoEmRodada;
	}
	
	private void jogarPréFlop() {
		estadoDaRodada = EstadoDaRodada.PRÉ_FLOP;
		dealer.darAsCartas(jogadoresEmRodada);
		cobrarBlinds();
		jogarRodada(new Aposta(smallBlindEBigBlind.fornecerBigBlindComoNúmero()));
	}
	
	private void jogarFlop() {
		estadoDaRodada = EstadoDaRodada.FLOP;
		dealer.darOFlop(cartasAbertas);
		atualizarMesa();
		jogarRodada(new Aposta());
	}
	
	private void jogarTurn() {
		estadoDaRodada = EstadoDaRodada.TURN ;
		dealer.darOTurn(cartasAbertas);
		atualizarMesa();
		jogarRodada(new Aposta());
	}
	
	private void jogarRiver() {
		estadoDaRodada = EstadoDaRodada.RIVER;
		dealer.darORiver(cartasAbertas);
		atualizarMesa();
		jogarRodada(new Aposta());
	}
	
	private void mostrarShowdown() {
		if (!terminou()) {
			List<Mao> mãos = new ArrayList<Mao>();
			vencedores = "Mãos Dos Jogadores:";
			for (NomeDeUsuario nomeDeUsuário : jogadoresEmRodada) {
				JogadorEmRodadaComum jogadorEmRodada = jogadoresEmRodada.pegar(nomeDeUsuário);
				if (!jogadorEmRodada.estáEliminadoDaRodada()) {
					List<Carta> cartasDoJogador = jogadorEmRodada.fornecerCartas(cartasAbertas);
					Mao mãoDoJogador = new Mao(cartasDoJogador, 
							AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartasDoJogador), 
							nomeDeUsuário);
					mãos.add(mãoDoJogador);
					CartaFeijao cartaFeijãoA = jogadorEmRodada.fornecerJogadorFeijãoEmRodada().fornecerCartasFeijão().get(ZERO);
					CartaFeijao cartaFeijaoB = jogadorEmRodada.fornecerJogadorFeijãoEmRodada().fornecerCartasFeijão().get(UM);
					vencedores += "<br />"+nomeDeUsuário.fornecerComoTexto()+": ["+cartaFeijãoA.mostrarBonitinho()+", "+ cartaFeijaoB.mostrarBonitinho()+"]";
				}
			}
			Collections.sort(mãos);
			Mao mãoVencedora = mãos.get(mãos.size()-UM);
			Iterator<Mao> iterador = mãos.iterator();
			while (iterador.hasNext()) {
				Mao mão = iterador.next();
				if (mão.compareTo(mãoVencedora) != ZERO) {
					JogadorEmRodadaComum jogadorEmRodada = jogadoresEmRodada.pegar(mão.fornecerNomeDeUsuario());
					if (jogadorEmRodada.fornecerQuantidadeDeFichasComoNúmero() == ZERO) {
						jogadorEmRodada.desistirDaRodada(EstadoDoJogador.FORA_DO_TORNEIO, new Jogada(AcaoDoJogador.NENHUMA, new Aposta()));
					} else {
						jogadorEmRodada.desistirDaRodada(EstadoDoJogador.FORA_DA_RODADA, new Jogada(AcaoDoJogador.NENHUMA, new Aposta()));
					}
					iterador.remove();
				} 
			}
			int quantidadeDeVencedores = mãos.size();
			vencedores += "<br /><br />Vencedor(es):";
			int prêmio = pote.fornecerQuantidadeDeFichas().fornecerComoNúmero()/quantidadeDeVencedores;
			for (int contador = ZERO; contador < quantidadeDeVencedores; contador++) {
				vencedores += "<br />"+mãos.get(contador).fornecerNomeDeUsuario().fornecerComoTexto()+" com " + mãos.get(contador).toString();
				jogadoresEmRodada.pegar(mãos.get(contador).fornecerNomeDeUsuario()).ganharFichas(pote.removerFichas(prêmio));
			}
			System.out.println(vencedores);
			try {
				Thread.sleep(3000);
				vencedores = "";
				Thread.sleep(30000);
			} catch (InterruptedException erro) {
				erro.printStackTrace();
			}
		}
	}
	
	private synchronized void jogarRodada(Aposta apostaMínima) {
		jogadaDoÚltimoJogador = new Jogada();
		açõesPossíveisDoJogador = new AcoesPossiveisDoJogador();
		açõesPossíveisDoJogador.fixarApostaMínima(apostaMínima);
		NomeDeUsuario nomeDeUsuárioDoÚltimoJogadorAFalar = jogadoresEmRodada.verÚltimo();
		boolean houveAposta = true;
		while (!terminou() && houveAposta) {
			boolean terminouÓrbita = false;
			while (!terminouÓrbita && houveAposta) {
				JogadorEmRodadaComum jogadorEmRodadaDaVez = jogadoresEmRodada.pegar(jogadoresEmRodada.verPrimeiro());
				if (jogadoresEmRodada.verPrimeiro().equals(nomeDeUsuárioDoÚltimoJogadorAFalar)) {
					terminouÓrbita = true;
				}
				int quantidadeDeFichasNaMesaDoJogadorDaVez = jogadorEmRodadaDaVez.fornecerQuantidadeDeFichasNaMesaComoNúmero();
				int quantidadeDeFichasApostadas = açõesPossíveisDoJogador.fornecerApostaMínimaComoNúmero();
				if (!jogadorEmRodadaDaVez.estáEliminadoDaRodada() && 
						(quantidadeDeFichasNaMesaDoJogadorDaVez < quantidadeDeFichasApostadas ||
						(terminouÓrbita && quantidadeDeFichasNaMesaDoJogadorDaVez <= quantidadeDeFichasApostadas && estadoDaRodada.equals(EstadoDaRodada.PRÉ_FLOP)) ||
						(quantidadeDeFichasApostadas == ZERO))) {
					atualizarAçõesPòssíveisDoJogador(quantidadeDeFichasNaMesaDoJogadorDaVez, jogadorEmRodadaDaVez.fornecerQuantidadeDeFichasComoNúmero());
					try {
						wait();
					} catch (InterruptedException erro) {
						erro.printStackTrace();
					}
					açõesPossíveisDoJogador.limparAçõesPossíveis();
					if (jogadaDoÚltimoJogador.fornecerAçãoDoJogador().equals(AcaoDoJogador.AUMENTAR)) {
						houveAposta = true;
						açõesPossíveisDoJogador.fixarApostaMínima(new Aposta(jogadaDoÚltimoJogador.fornecerApostaComoNúmero()));
					}
				}
				jogadoresEmRodada.reentrarNaFila();
			}
			terminarRodadaSeNecessário();
			houveAposta = false;
			for (NomeDeUsuario nomeDeUsuário : jogadoresEmRodada) {
				JogadorEmRodadaComum jogadorEmRodadaComum = jogadoresEmRodada.pegar(nomeDeUsuário);
				if (!jogadorEmRodadaComum.estáEliminadoDaRodada() && jogadorEmRodadaComum.fornecerQuantidadeDeFichasNaMesaComoNúmero() < 
						açõesPossíveisDoJogador.fornecerApostaMínimaComoNúmero()) {
					houveAposta = true;
				}
			}
		}
	}
	
	private void atualizarMesa() {
		colocarSmallBlindNoÍnicioDaFila();
		for (NomeDeUsuario nomeDeUsuário : jogadoresEmRodada) {
			JogadorEmRodadaComum jogadorEmRodadaComum = jogadoresEmRodada.pegar(nomeDeUsuário);
			if (!jogadorEmRodadaComum.estáEliminadoDaRodada()) {
				jogadorEmRodadaComum.apostar(EstadoDoJogador.ESPERANDO_JOGADA, new Jogada(AcaoDoJogador.NENHUMA, new Aposta()));
			}
			if (jogadorEmRodadaComum.estáEliminadoDaRodada() && !jogadorEmRodadaComum.estáEliminadoDoTorneio()) {
				jogadorEmRodadaComum.desistirDaRodada(EstadoDoJogador.FORA_DA_RODADA, new Jogada(AcaoDoJogador.NENHUMA, new Aposta()));
			}
		}
	}
	
	private void cobrarBlinds() {
		cobrarSmallBlindOuBigBlind(smallBlindEBigBlind.fornecerSmallBlindComoNúmero(), EstadoDoJogador.PAGOU_SMALL_BLIND);
		if (!terminou()) {
			cobrarSmallBlindOuBigBlind(smallBlindEBigBlind.fornecerBigBlindComoNúmero(), EstadoDoJogador.PAGOU_BIG_BLIND);
		}
		jogadaDoÚltimoJogador = new Jogada(AcaoDoJogador.AUMENTAR, new Aposta(smallBlindEBigBlind.fornecerBigBlindComoNúmero()), jogadoresEmRodada.verÚltimo());
	}
	
	private void cobrarSmallBlindOuBigBlind(int valorDoBlind, EstadoDoJogador estadoDoJogador) {
		JogadorEmRodadaComum jogadorBlind = jogadoresEmRodada.reentrarNaFila();
		Fichas fichasRecebidasDoJogadorBlind;
		Jogada jogada = new Jogada(AcaoDoJogador.AUMENTAR, new Aposta(valorDoBlind), jogadoresEmRodada.verÚltimo());
		fichasRecebidasDoJogadorBlind = jogadorBlind.apostar(estadoDoJogador, jogada);
		if (fichasRecebidasDoJogadorBlind.fornecerQuantidadeDeFichas().fornecerComoNúmero() != valorDoBlind) {
			Jogada jogadaDeDesistência = new Jogada(AcaoDoJogador.DESISTIR, new Aposta(), jogadoresEmRodada.verÚltimo()); 
			jogadorBlind.desistirDaRodada(EstadoDoJogador.FORA_DO_TORNEIO, jogadaDeDesistência);
			jogadorBlind.devolverCartas(dealer);
		}
		pote.adicionarFichas(fichasRecebidasDoJogadorBlind);
		terminarRodadaSeNecessário();
	}
	
	private void terminarRodadaSeNecessário() {
		int jogadoresNaRodada = ZERO;
		NomeDeUsuario nomeDeUsuárioDoVencendor = null;
		for (NomeDeUsuario nomeDeUsuário : jogadoresEmRodada) {
			if (!jogadoresEmRodada.pegar(nomeDeUsuário).estáEliminadoDaRodada()) {
				nomeDeUsuárioDoVencendor = nomeDeUsuário;
				jogadoresNaRodada++;
			} 
		}
		if (jogadoresNaRodada < DOIS) {
			jogadoresEmRodada.pegar(nomeDeUsuárioDoVencendor).ganharFichas(pote);
			dealer.receberCartasDeVolta(cartasAbertas);
			estadoDaRodada = EstadoDaRodada.TERMINADA;
		}
	}
	
	private void colocarSmallBlindNoÍnicioDaFila() {
		Iterator<NomeDeUsuario> iterador = jogadoresEmRodada.iterator();
		boolean smallBlindNoÍnicioDaFila = false;
		int vezesParaReentrarNaFila = ZERO;
		while (iterador.hasNext() && !smallBlindNoÍnicioDaFila) {
			if (iterador.next().equals(dealer.fornecerNomeDeUsuárioDoJogadorDealer())) {
				smallBlindNoÍnicioDaFila = true;
			}
			vezesParaReentrarNaFila++;
		}
		for (int contador = ZERO; contador < vezesParaReentrarNaFila; contador++) {
			jogadoresEmRodada.reentrarNaFila();
		}
	}
	
	private void atualizarAçõesPòssíveisDoJogador(int quantidadeDeFichasNaMesaDoJogador, 
			int quantidadeDeFichasDoJogador) {
		int quantidadeDeFichasApostadas = açõesPossíveisDoJogador.fornecerApostaMínimaComoNúmero();
		açõesPossíveisDoJogador.fixarApostaMáxima(new Aposta(quantidadeDeFichasDoJogador));
		if (quantidadeDeFichasDoJogador >= quantidadeDeFichasApostadas && quantidadeDeFichasNaMesaDoJogador != quantidadeDeFichasApostadas) {
			açõesPossíveisDoJogador.permitirAção(AcaoDoJogador.PAGAR);
		}
		if (quantidadeDeFichasDoJogador >= DOIS*quantidadeDeFichasApostadas) {
			açõesPossíveisDoJogador.permitirAção(AcaoDoJogador.AUMENTAR);
		}
		if (quantidadeDeFichasNaMesaDoJogador == quantidadeDeFichasApostadas) {
			açõesPossíveisDoJogador.permitirAção(AcaoDoJogador.PASSAR);
		}
		açõesPossíveisDoJogador.permitirAção(AcaoDoJogador.DESISTIR);
	}
	
	private boolean começou() {
		return !(estadoDaRodada.equals(EstadoDaRodada.NÃO_INICIADA));
	}
	
	private boolean terminou() {
		return estadoDaRodada.equals(EstadoDaRodada.TERMINADA);
	}
	
	private boolean jogadorExisteEEstáNaVez(NomeDeUsuario nomeDeUsuário) {
		return jogadoresEmRodada.contém(nomeDeUsuário) && 
				nomeDeUsuário.equals(jogadoresEmRodada.verPrimeiro());
	}
	
	@Override
	public synchronized boolean receberAumento(NomeDeUsuario nomeDeUsuário, Aposta aposta) {
		Jogada jogadaDesejada = new Jogada(AcaoDoJogador.AUMENTAR, aposta, nomeDeUsuário);
		if (jogadorExisteEEstáNaVez(nomeDeUsuário) && 
				açõesPossíveisDoJogador.verificiarSeAçãoÉVálida(jogadaDesejada) && aposta.fornecerComoNúmero() > smallBlindEBigBlind.fornecerBigBlindComoNúmero()) {
			jogadaDoÚltimoJogador = jogadaDesejada;
			JogadorEmRodadaComum jogadorEmRodada = jogadoresEmRodada.pegar(nomeDeUsuário);
			jogadorEmRodada.ganharFichas(pote.removerFichas(jogadorEmRodada.fornecerQuantidadeDeFichasNaMesaComoNúmero()));
			pote.adicionarFichas(jogadorEmRodada.apostar(EstadoDoJogador.AUMENTOU, jogadaDoÚltimoJogador));
			
			notify();
			return true;
		}
		
		return false;
	}

	@Override
	public synchronized boolean receberDesistência(NomeDeUsuario nomeDeUsuário) {
		Jogada jogadaDesejada = new Jogada(AcaoDoJogador.DESISTIR, new Aposta(), nomeDeUsuário);
		if (jogadorExisteEEstáNaVez(nomeDeUsuário) && 
				açõesPossíveisDoJogador.verificiarSeAçãoÉVálida(jogadaDesejada)) {
			jogadaDoÚltimoJogador = jogadaDesejada;
			JogadorEmRodadaComum jogadorEmRodada = jogadoresEmRodada.pegar(nomeDeUsuário);
			jogadorEmRodada.desistirDaRodada(EstadoDoJogador.DESISTIU, jogadaDoÚltimoJogador);
			jogadorEmRodada.devolverCartas(dealer);
			notify();
			return true;
		}
		
		return false;
	}
	
	@Override
	public synchronized boolean receberPagamento(NomeDeUsuario nomeDeUsuário) {
		Jogada jogadaDesejada = new Jogada(AcaoDoJogador.PAGAR, new Aposta(açõesPossíveisDoJogador.fornecerApostaMínimaComoNúmero()), nomeDeUsuário);
		if (jogadorExisteEEstáNaVez(nomeDeUsuário) && 
				açõesPossíveisDoJogador.verificiarSeAçãoÉVálida(jogadaDesejada)) {
			jogadaDoÚltimoJogador = jogadaDesejada;
			JogadorEmRodadaComum jogadorEmRodada = jogadoresEmRodada.pegar(nomeDeUsuário);
			jogadorEmRodada.ganharFichas(pote.removerFichas(jogadorEmRodada.fornecerQuantidadeDeFichasNaMesaComoNúmero()));
			pote.adicionarFichas(jogadorEmRodada.apostar(EstadoDoJogador.PAGOU, jogadaDoÚltimoJogador));
			notify();
			return true;
		}
		
		return false;
	}
	
	@Override
	public synchronized boolean receberPasso(NomeDeUsuario nomeDeUsuário) {
		Jogada jogadaDesejada = new Jogada(AcaoDoJogador.PASSAR, new Aposta(açõesPossíveisDoJogador.fornecerApostaMínimaComoNúmero()), nomeDeUsuário);
		if (jogadorExisteEEstáNaVez(nomeDeUsuário) && 
				açõesPossíveisDoJogador.verificiarSeAçãoÉVálida(jogadaDesejada)) {
			jogadaDoÚltimoJogador = jogadaDesejada;
			pote.adicionarFichas(jogadoresEmRodada.pegar(nomeDeUsuário).apostar(EstadoDoJogador.PASSOU, jogadaDoÚltimoJogador));
			jogadoresEmRodada.pegar(nomeDeUsuário).ganharFichas(pote.removerFichas(jogadaDoÚltimoJogador.fornecerApostaComoNúmero()));
			notify();
			return true;
		}
		
		return false;
	}
	
}