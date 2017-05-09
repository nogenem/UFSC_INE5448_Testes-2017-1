package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa;

import java.util.Queue;

import br.ufsc.es.projetoPoquer.modelo.Poquer.DinheiroFicticio;
import br.ufsc.es.projetoPoquer.modelo.colecaoMapa.FilaDeJogadoresEmTorneio;
import br.ufsc.es.projetoPoquer.modelo.jogador.TipoJogador;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.torneio.TipoTorneio;
import br.ufsc.es.projetoPoquer.modelo.torneio.configuracao.ConfiguracaoDeTorneioDeUmaMesa;
import br.ufsc.es.projetoPoquer.modelo.torneio.configuracao.Vagas;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.EstadoDoTorneio;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.PosicaoDoJogadorNaMesa;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.JogadorFeijaoEmTorneio;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.QuantidadeDeFichas;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.QuantidadeDeVagas;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.TorneioFeijao;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.TorneioFeijaoCompleto;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.ValorDaEntrada;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.TipoRodada;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Aposta;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Dealer;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.rodadaComum.RodadaComum;

public final class TorneioDeUmaMesa implements TipoTorneio<TipoJogador> {
	
	private TipoRodada rodada;
	private final Dealer dealer;
	private final FilaDeJogadoresEmTorneio jogadoresEmTorneio; 
	private EstadoDoTorneio estadoDoTorneio; 
	private final DinheiroFicticio dinheiroFictício;
	private final ConfiguracaoDeTorneioDeUmaMesa configuração;
	
	private static final int ZERO = 0;
	private static final int DEZ = 10;
	private static final int UM = 1;
	
	public TorneioDeUmaMesa(ConfiguracaoDeTorneioDeUmaMesa configuração, DinheiroFicticio dinheiroFicício, TorneioFeijao torneioFeijão) {
		this.dinheiroFictício = dinheiroFicício;
		this.configuração = configuração;
		jogadoresEmTorneio = new FilaDeJogadoresEmTorneio();
		dealer = new Dealer();
		rodada = new RodadaComum(configuração.fornecerBlinds().fornecerSmallBlindEBigBlind(), dealer, new Fichas(ZERO));
		estadoDoTorneio = EstadoDoTorneio.NÃO_INICIADO;
		torneioFeijão.fixarValorDaEntrada(new ValorDaEntrada(configuração.fornecerEntrada().fornecerComoNúmero()));
	}
	
	@Override
	public boolean inscreverJogador(NomeDeUsuario nomeDeUsuário, TipoJogador jogador) {
		if (jogadoresEmTorneio.contém(nomeDeUsuário)) {
			return true;
		}
		Vagas vagas = configuração.fornecerVagas();
		if (estadoDoTorneio.equals(EstadoDoTorneio.NÃO_INICIADO) && vagas.possuiVaga()) {
			int valorDaEntrada = configuração.fornecerEntrada().fornecerComoNúmero();
			DinheiroFicticio dinheiroFictícioPago = jogador.pagarEntradaNoTorneio(valorDaEntrada);
			if (dinheiroFictícioPago.fornecerQuantidade() == valorDaEntrada) {
				inscreverJogador(nomeDeUsuário, jogador, vagas, valorDaEntrada, dinheiroFictícioPago);
				return true;
			}
		}
		
		return false;
	}

	private void inscreverJogador(NomeDeUsuario nomeDeUsuário, 	TipoJogador jogador, Vagas vagas, 
			int valorDaEntrada, DinheiroFicticio dinheiroFictícioPago) {
		vagas.ocuparVaga();
		dinheiroFictício.adicionarAoMontante(dinheiroFictícioPago);
		JogadorEmTorneioDeUmaMesa jogadorEmTorneio = new JogadorEmTorneioDeUmaMesa(jogador, new Fichas(configuração.fornecerStackInicial().fornecerComoNúmero()));
		jogadoresEmTorneio.adicionar(nomeDeUsuário, jogadorEmTorneio);
		iniciarTorneioSeNecessário();
	}
	
	@Override
	public boolean desinscreverJogador(NomeDeUsuario nomeDeUsuário) {
		if (estadoDoTorneio.equals(EstadoDoTorneio.NÃO_INICIADO) && jogadoresEmTorneio.contém(nomeDeUsuário)) {
			DinheiroFicticio dinheiroFictícioDevolvido = dinheiroFictício.removerDoMontante(configuração.fornecerEntrada().fornecerComoNúmero());
			jogadoresEmTorneio.pegar(nomeDeUsuário).receberDinheiroDaInscriçãoDeVolta(dinheiroFictícioDevolvido);
			jogadoresEmTorneio.remover(nomeDeUsuário);
			configuração.fornecerVagas().desocuparVaga();
			return true;
		}
		
		return false;
	}

	@Override
	public boolean receberAumento(NomeDeUsuario nomeDeUsuário, Aposta aposta) {
		return rodada.receberAumento(nomeDeUsuário, aposta);
	}

	@Override
	public boolean receberDesistência(NomeDeUsuario nomeDeUsuário) {
		return rodada.receberDesistência(nomeDeUsuário);
	}
	
	@Override
	public boolean receberPagamento(NomeDeUsuario nomeDeUsuário) {
		return rodada.receberPagamento(nomeDeUsuário);
	}
	
	@Override
	public boolean receberPasso(NomeDeUsuario nomeDeUsuário) {
		return rodada.receberPasso(nomeDeUsuário);
	}

	@Override
	public TorneioFeijao fornecerTorneioFeijão() {
		int quantidadeDeVagas = configuração.fornecerVagas().fornecerQuantidadeDeVagas();
		int quantidadeDeVagasDisponíveis = quantidadeDeVagas-configuração.fornecerVagas().fornecerQuantidadeDeVagasOcupadas();
		TorneioFeijao torneioFeijão = configuração.fornecerTorneioFeijão();
		torneioFeijão.fixarQuantidadeDeVagas(new QuantidadeDeVagas(quantidadeDeVagas, quantidadeDeVagasDisponíveis));
		
		return torneioFeijão;
	}
	
	@Override
	public TorneioFeijaoCompleto fornecerTorneioFeijãoCompleto(NomeDeUsuario nomeDeUsuário) {
		if (jogadoresEmTorneio.contém(nomeDeUsuário)) {
			TorneioFeijaoCompleto torneioFeijãoCompleto = new TorneioFeijaoCompleto();
			torneioFeijãoCompleto.fixarJogadoresFeijãoEmTorneio(jogadoresEmTorneio.fornecerObjetosFeijão());
			torneioFeijãoCompleto.fixarJogadorFeijãoEmRodada(rodada.fornecerJogadorFeijãoEmRodada(nomeDeUsuário));
			torneioFeijãoCompleto.fixarRodadaFeijão(rodada.fornecerRodadaFeijão());
			torneioFeijãoCompleto.fixarPosiçãoDoDealerNaMesa(new PosicaoDoJogadorNaMesa(fornecerPosiçãoDoJogadorDealerNaMesa()));
			
			return torneioFeijãoCompleto;
		}
		
		return null;
	}
	
	@Override
	public boolean torneioNaoTemInscritosENaoComeçou() {
		return (jogadoresEmTorneio.fornecerQuantidade() == 0 && !estadoDoTorneio.equals(EstadoDoTorneio.INICIADO));
	}
	
	private void iniciarTorneioSeNecessário() {
		atualizarPosiçãoDosJogadoresNaMesa();
		if (!configuração.fornecerVagas().possuiVaga()) {
			estadoDoTorneio = EstadoDoTorneio.INICIADO;
			iniciarNovaRodada();
		}
	}
	
	private void iniciarNovaRodada() {
		jogadoresEmTorneio.reentrarNaFila();
		dealer.fixarJogadorDealerNaMesa(jogadoresEmTorneio.verÚltimo());
		rodada = new RodadaComum(configuração.fornecerBlinds().fornecerSmallBlindEBigBlind(), 
				dealer, new Fichas(ZERO));
		for (NomeDeUsuario nomeDeUsuário : jogadoresEmTorneio) {
			JogadorEmTorneioDeUmaMesa jogadorEmTorneio = jogadoresEmTorneio.pegar(nomeDeUsuário);
			if (!jogadorEmTorneio.estáEliminado()) {
				jogadorEmTorneio.entrarEmRodada(nomeDeUsuário, rodada);
			}
		}
		Thread execuçãoDeRodada = new Thread(rodada);
		Thread execuçãoDeFinalizaçãoDeRodada = new Thread(new FinalizadorDeRodada(this, execuçãoDeRodada));
		execuçãoDeFinalizaçãoDeRodada.start();
		execuçãoDeRodada.start();
	}
	
	protected void terminarRodada() {
		NomeDeUsuario nomeDeUsuárioVencedor = null;
		int quantidadeDeJogadoresNoTorneio = ZERO;
		for (NomeDeUsuario nomeDeUsuário : jogadoresEmTorneio) {
			if (!jogadoresEmTorneio.pegar(nomeDeUsuário).estáEliminado()) {
				nomeDeUsuárioVencedor = nomeDeUsuário;
				quantidadeDeJogadoresNoTorneio++;
			}
		}
		if (quantidadeDeJogadoresNoTorneio > UM) {
			iniciarNovaRodada();
			System.out.println("Rodada Terminada");
		} else {
			System.out.println("Houve um vencedor: "+nomeDeUsuárioVencedor.fornecerComoTexto());
		}
	}
	
	private int fornecerPosiçãoDoJogadorDealerNaMesa() {
		if (estadoDoTorneio.equals(EstadoDoTorneio.INICIADO)) {
		return jogadoresEmTorneio.pegar(dealer.fornecerNomeDeUsuárioDoJogadorDealer())
				.fornecerPosiçãoDoJogadorNaMesaComoNúmero();
		}
		
		return DEZ;
	}
	
	private Queue<JogadorFeijaoEmTorneio> atualizarPosiçãoDosJogadoresNaMesa() {
		Queue<JogadorFeijaoEmTorneio> jogadoresFeijãoEmTorneio = jogadoresEmTorneio.fornecerObjetosFeijão();
		int posiçãoNaMesa = ZERO;
		for (JogadorFeijaoEmTorneio jogadorFeijaoEmTorneio : jogadoresFeijãoEmTorneio) {
			PosicaoDoJogadorNaMesa posiçãoDoJogadorNaMesa = new PosicaoDoJogadorNaMesa(posiçãoNaMesa); 
			jogadoresEmTorneio.pegar(jogadorFeijaoEmTorneio.fornecerNomeDeUsuário())
				.fixarPosiçãoDoJogadorNaMesa(posiçãoDoJogadorNaMesa);
			jogadorFeijaoEmTorneio.fixarPosiçãoDoJogadorNaMesa(posiçãoDoJogadorNaMesa);
			posiçãoNaMesa++;
		}
		
		return jogadoresFeijãoEmTorneio;
	}
	
	
	public final class Fichas {
		
		private int quantidade;
		
		private static final int ZERO = 0; 
		
		private Fichas(int stack) {
			this.quantidade = stack;
		}		
		
		public void adicionarFichas(Fichas stack) {
			quantidade += stack.quantidade;
			stack.quantidade = ZERO;
		}
		
		public Fichas removerFichas(int quantidadeDeFichas) {
			Fichas stack;
			if (quantidadeDeFichas <= this.quantidade) {
				this.quantidade = this.quantidade-quantidadeDeFichas;
				stack = new Fichas(quantidadeDeFichas);
			} else {
				stack = new Fichas(ZERO);
			}
			
			return stack;
		}
		
		public QuantidadeDeFichas fornecerQuantidadeDeFichas() {
			return new QuantidadeDeFichas(quantidade);
		}
	}
}