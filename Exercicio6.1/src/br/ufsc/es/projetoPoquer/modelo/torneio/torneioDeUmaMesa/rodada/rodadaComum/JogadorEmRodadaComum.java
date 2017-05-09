package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.rodadaComum;

import java.util.ArrayList;
import java.util.List;

import br.ufsc.es.projetoPoquer.modelo.colecaoMapa.ListaDeCartas;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.JogadorFeijaoEmTorneio;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.QuantidadeDeFichas;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.TorneioDeUmaMesa.Fichas;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.EstadoDoJogador;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.dados.feijao.JogadorFeijaoEmRodada;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Carta;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Dealer;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Jogada;

public class JogadorEmRodadaComum {
	
	private Fichas fichas;
	private EstadoDoJogador estadoDoJogador;
	private Jogada últimaJogada;
	private ListaDeCartas cartas;
	
	public JogadorEmRodadaComum(Fichas fichas) {
		this.fichas = fichas;
		estadoDoJogador = EstadoDoJogador.ESPERANDO_JOGADA;
		últimaJogada = new Jogada();
		cartas = new ListaDeCartas();
	}
	
	public void receberCartas(ListaDeCartas cartas) {
		this.cartas = cartas;
	}
	
	public void devolverCartas(Dealer dealer) {
		dealer.receberCartasDeVolta(cartas);
	}
	
	public Fichas apostar(EstadoDoJogador estadoDoJogador, Jogada últimaJogada) {
		this.estadoDoJogador = estadoDoJogador;
		this.últimaJogada = últimaJogada;
		
		return fichas.removerFichas(últimaJogada.fornecerApostaComoNúmero());
	}
	
	public void desistirDaRodada(EstadoDoJogador estadoDoJogador, Jogada últimaJogada) {
		this.estadoDoJogador = estadoDoJogador;
		this.últimaJogada = últimaJogada;
	}
	
	public void ganharFichas(Fichas fichasGanhas) {
		fichas.adicionarFichas(fichasGanhas);
	}
	
	public int fornecerQuantidadeDeFichasComoNúmero() {
		return fichas.fornecerQuantidadeDeFichas().fornecerComoNúmero();
	}
	
	public int fornecerQuantidadeDeFichasNaMesaComoNúmero() {
		return últimaJogada.fornecerApostaComoNúmero();
	}
	
	public JogadorFeijaoEmTorneio montarJogadorFeijãoEmTorneio() {
		JogadorFeijaoEmTorneio jogadorFeijãoEmTorneio = new JogadorFeijaoEmTorneio();
		jogadorFeijãoEmTorneio.fixarQuantidadeDeFichas(fichas.fornecerQuantidadeDeFichas());
		jogadorFeijãoEmTorneio.fixarQuantidadeDeFichasNaMesa(new QuantidadeDeFichas(últimaJogada.fornecerApostaComoNúmero()));
		jogadorFeijãoEmTorneio.fixarEstadoDoJogador(estadoDoJogador);
		
		return jogadorFeijãoEmTorneio;
	}
	
	public JogadorFeijaoEmRodada fornecerJogadorFeijãoEmRodada() {
		JogadorFeijaoEmRodada jogadorFeijãoEmRodada = new JogadorFeijaoEmRodada();
		jogadorFeijãoEmRodada.fixarCartasFeijão(cartas.fornecerCartasFeijão());
		
		return jogadorFeijãoEmRodada;
	}
	
	public boolean estáEliminadoDoTorneio() {
		return estadoDoJogador.estáEliminadoDoTorneio();
	}
	
	public boolean estáEliminadoDaRodada() {
		return estadoDoJogador.estáEliminadoDaRodada();
	}
	
	//Se der tempo vou tirar esse get nojento daqui
	public List<Carta> fornecerCartas(ListaDeCartas cartasAbertas) {
		List<Carta> todasAsCartas = new ArrayList<Carta>();
		for (Carta carta : cartasAbertas) {
			todasAsCartas.add(carta);
		}
		for (Carta carta : cartas) {
			todasAsCartas.add(carta);
		}
		
		return todasAsCartas;
	}
}