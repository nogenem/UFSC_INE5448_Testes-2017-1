package br.ufsc.es.projetoPoquer.modelo;

import java.util.Collection;

import br.ufsc.es.projetoPoquer.bancoDeDados.BancoDeDados;
import br.ufsc.es.projetoPoquer.bancoDeDados.OperacaoDeDados;
import br.ufsc.es.projetoPoquer.modelo.adminitracao.MantenedorFeijao;
import br.ufsc.es.projetoPoquer.modelo.colecaoMapa.ConjuntoDeJogadores;
import br.ufsc.es.projetoPoquer.modelo.colecaoMapa.ConjuntoDeTorneios;
import br.ufsc.es.projetoPoquer.modelo.jogador.JogadorComum;
import br.ufsc.es.projetoPoquer.modelo.jogador.TipoJogador;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.ChaveDeSecao;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao.JogadorFeijao;
import br.ufsc.es.projetoPoquer.modelo.resposta.RespostaDeAutenticacao;
import br.ufsc.es.projetoPoquer.modelo.resposta.RespostaDeInscricaoNoTorneio;
import br.ufsc.es.projetoPoquer.modelo.resposta.RespostaDeJogada;
import br.ufsc.es.projetoPoquer.modelo.torneio.TipoTorneio;
import br.ufsc.es.projetoPoquer.modelo.torneio.configuracao.Blinds;
import br.ufsc.es.projetoPoquer.modelo.torneio.configuracao.ConfiguracaoDeTorneioDeUmaMesa;
import br.ufsc.es.projetoPoquer.modelo.torneio.configuracao.Entrada;
import br.ufsc.es.projetoPoquer.modelo.torneio.configuracao.FichasInicial;
import br.ufsc.es.projetoPoquer.modelo.torneio.configuracao.Vagas;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.Identificador;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.TorneioFeijao;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.TorneioFeijaoCompleto;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.TorneioDeUmaMesa;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Aposta;

public final class Poquer {
	
	private static final Poquer INSTÂNCIA = new Poquer();
	
	private ConjuntoDeJogadores jogadoresOnline; 
	private ConjuntoDeTorneios torneios;
	
	private static final String SENHA_DO_ADMINISTRADOR = "administrador";
	
	private Poquer() {
		jogadoresOnline = new ConjuntoDeJogadores();
		torneios = new ConjuntoDeTorneios();
	}
	
	public static Poquer fornecerInstância() {
		return INSTÂNCIA;
	}
	
	public RespostaDeAutenticacao autenticarJogador(JogadorFeijao jogadorFeijão) {
		OperacaoDeDados<JogadorFeijao> operaçãoDeDados = BancoDeDados.fornecerInstância()
				.fornecerJogador(jogadorFeijão.fornecerNomeDeUsuário());
		if (operaçãoDeDados.sucesso()) {
			return autenticarJogador(operaçãoDeDados.fornecerConsulta(), jogadorFeijão);
		} 
		
		return RespostaDeAutenticacao.criarRespostaComErroNaConsulta(operaçãoDeDados.fornecerMensagemDeErro());
	}
	
	private RespostaDeAutenticacao autenticarJogador(JogadorFeijao jogadorDoBancoDeDados, JogadorFeijao jogadorFeijão) {
		if (jogadorDoBancoDeDados.fornecerSenha().éIgual(jogadorFeijão.fornecerSenha())) {
			TipoJogador jogador = new JogadorComum(new DinheiroFicticio());
			jogadoresOnline.adicionar(jogadorFeijão.fornecerNomeDeUsuário(), jogador);
			return RespostaDeAutenticacao.criarRespostaComAutenticaçãoRealizadaComSucesso(jogador.gerarChaveDeSeção());
		}
		
		return RespostaDeAutenticacao.criarRepostaComErroDeSenhaInválida();
	}
	
	public RespostaDeInscricaoNoTorneio inscreverJogadorNoTorneio(JogadorFeijao jogadorFeijão, TorneioFeijao torneioFeijão) {
		NomeDeUsuario nomeDeUsuário = jogadorFeijão.fornecerNomeDeUsuário();
		Identificador identificador = torneioFeijão.fornecerIdentificador();
		if (jogadorEstáOnlineEChaveDeSeçãoEstáCorreta(nomeDeUsuário, jogadorFeijão.fornecerChaveDeSecao())) {
			return inscreverJogadorNoTorneioSeExistir(identificador, nomeDeUsuário);
		}
		
		return RespostaDeInscricaoNoTorneio.criarRespostaComErroDeJogadorNaoAutenticado();
	}
	
	private RespostaDeInscricaoNoTorneio inscreverJogadorNoTorneioSeExistir(Identificador identificador, NomeDeUsuario nomeDeUsuário) {
		if (torneios.contém(identificador)) { 
			return inscreverJogadorNoTorneio(identificador, nomeDeUsuário);
		}
		
		return RespostaDeInscricaoNoTorneio.criarRespostaComErroDeTorneioInexistente();
	}
	
	private RespostaDeInscricaoNoTorneio inscreverJogadorNoTorneio(Identificador identificador, NomeDeUsuario nomeDeUsuário) {
		TipoTorneio<TipoJogador> torneio = torneios.pegar(identificador);
		if (torneio.inscreverJogador(nomeDeUsuário, jogadoresOnline.pegar(nomeDeUsuário))) {
			return RespostaDeInscricaoNoTorneio.criarRespostaDeInscriçãoRealizadaComSucesso();
		}
		
		return RespostaDeInscricaoNoTorneio.criarRespostaComErroDeVagasInsuficientes();
	}
	
	private boolean jogadorEstáOnlineEChaveDeSeçãoEstáCorreta(NomeDeUsuario nomeDeUsuário, ChaveDeSecao chaveDeSeção) {
		if (jogadoresOnline.contém(nomeDeUsuário)) {
			return jogadoresOnline.pegar(nomeDeUsuário).validarChaveDeSeção(chaveDeSeção);
		}
			
		return false;
	}
	
	public Collection<TorneioFeijao> fornecerTorneiosFeijão() {
		return torneios.fornecerObjetosFeijão();
	}
	
	public TorneioFeijaoCompleto fornecerTorneioFeijãoCompleto(JogadorFeijao jogadorFeijão, TorneioFeijao torneioFeijão) {
		NomeDeUsuario nomeDeUsuário = jogadorFeijão.fornecerNomeDeUsuário();
		Identificador identificador = torneioFeijão.fornecerIdentificador();
		boolean jogadorEstáOnlineEAutenticado = jogadorEstáOnlineEChaveDeSeçãoEstáCorreta(nomeDeUsuário, jogadorFeijão.fornecerChaveDeSecao());
		boolean torneioExiste = torneios.contém(identificador);
		if (jogadorEstáOnlineEAutenticado && torneioExiste) {
			return torneios.pegar(identificador).fornecerTorneioFeijãoCompleto(nomeDeUsuário);
		}
		
		return null;
	}
	
	public RespostaDeJogada receberAumento(JogadorFeijao jogadorFeijão, TorneioFeijao torneioFeijão, Aposta aposta) {
		if (jogadorEstáOnlineEAutenticadoETorneioExiste(jogadorFeijão, torneioFeijão)) {
			if (torneios.pegar(torneioFeijão.fornecerIdentificador()).receberAumento(jogadorFeijão.fornecerNomeDeUsuário(), aposta)) {
				return RespostaDeJogada.criarRespostaDeJogadaComSucesso(); 
			} 
			return RespostaDeJogada.criarRespostaComJogadaInválida();
		}
		
		return null;
	}

	public RespostaDeJogada receberDesistência(JogadorFeijao jogadorFeijão, TorneioFeijao torneioFeijão) {
		if (jogadorEstáOnlineEAutenticadoETorneioExiste(jogadorFeijão, torneioFeijão)) {
			if (torneios.pegar(torneioFeijão.fornecerIdentificador()).receberDesistência(jogadorFeijão.fornecerNomeDeUsuário())) {
				return RespostaDeJogada.criarRespostaDeJogadaComSucesso(); 
			} 
			return RespostaDeJogada.criarRespostaComJogadaInválida();
		}
		
		return null;
	}
	
	public RespostaDeJogada receberPagamento(JogadorFeijao jogadorFeijão, TorneioFeijao torneioFeijão) {
		if (jogadorEstáOnlineEAutenticadoETorneioExiste(jogadorFeijão, torneioFeijão)) {
			if (torneios.pegar(torneioFeijão.fornecerIdentificador()).receberPagamento(jogadorFeijão.fornecerNomeDeUsuário())) {
				return RespostaDeJogada.criarRespostaDeJogadaComSucesso(); 
			} 
			return RespostaDeJogada.criarRespostaComJogadaInválida();
		}
		
		return null;
	}
	
	public RespostaDeJogada receberPasso(JogadorFeijao jogadorFeijão, TorneioFeijao torneioFeijão) {
		if (jogadorEstáOnlineEAutenticadoETorneioExiste(jogadorFeijão, torneioFeijão)) {
			if (torneios.pegar(torneioFeijão.fornecerIdentificador()).receberPasso(jogadorFeijão.fornecerNomeDeUsuário())) {
				return RespostaDeJogada.criarRespostaDeJogadaComSucesso(); 
			} 
			return RespostaDeJogada.criarRespostaComJogadaInválida();
		}
		
		return null;
	}
	
	private boolean jogadorEstáOnlineEAutenticadoETorneioExiste(JogadorFeijao jogadorFeijão, TorneioFeijao torneioFeijão) {
		boolean jogadorEstáOnlineEAutenticado = jogadorEstáOnlineEChaveDeSeçãoEstáCorreta(jogadorFeijão.fornecerNomeDeUsuário(), 
				jogadorFeijão.fornecerChaveDeSecao());
		boolean torneioExiste = torneios.contém(torneioFeijão.fornecerIdentificador());
		
		return (jogadorEstáOnlineEAutenticado && torneioExiste);
	}
	
	public boolean autenticarAdministrador(String senha) {
		return SENHA_DO_ADMINISTRADOR.equals(senha);
	}
	
	public boolean autenticarMantenedor(String nomeDeMantenedor, String senha) {
		OperacaoDeDados<MantenedorFeijao> operacaoDeDados = BancoDeDados.fornecerInstância().fornecerMantenedor(nomeDeMantenedor);
		if (operacaoDeDados.sucesso() && operacaoDeDados.fornecerConsulta().fornecerSenha().equals(senha)) {
			return true;
		}
		
		return false;
	}
	
	public boolean torneioPodeSerDeletado(Identificador identificadorDoTorneio) {
		return (torneios.contém(identificadorDoTorneio)) ? torneios.pegar(identificadorDoTorneio)
				.torneioNaoTemInscritosENaoComeçou() : true;
	}
	
	public void abrirTorneio(TorneioFeijao torneioFeijão) {
		torneioFeijão.fixarIdentificador(new Identificador(torneios.fornecerQuantidade()+1));
		ConfiguracaoDeTorneioDeUmaMesa configuração = new ConfiguracaoDeTorneioDeUmaMesa(
				new Blinds(50, 100),
				new Entrada(torneioFeijão.fornecerValorDaEntrada().fornecerComoNúmero()), 
				new FichasInicial(1500), 
				new Vagas(torneioFeijão.fornecerQuantidadeDeVagas().fornecerVagasDisponíveis()),
				torneioFeijão);
		TipoTorneio<TipoJogador> torneio = new TorneioDeUmaMesa(configuração, new DinheiroFicticio(0), torneioFeijão);
		torneios.adicionar(torneioFeijão.fornecerIdentificador(), torneio);
	}
	
	public final class DinheiroFicticio {
		
		private int montante;
		
		private static final int ZERO = 0; 
		
		private DinheiroFicticio() {
			montante = ZERO;
		}
		
		private DinheiroFicticio(int montante) {
			this.montante = montante;
		}
		
		public void adicionarAoMontante(DinheiroFicticio dinheiro) {
			montante += dinheiro.montante;
			dinheiro.montante = ZERO;
		}
		
		public DinheiroFicticio removerDoMontante(int quantidadde) {
			DinheiroFicticio dinheiro;
			quantidadde = Math.abs(quantidadde);
			if (quantidadde <= montante) {
				montante = montante-quantidadde;
				dinheiro = new DinheiroFicticio(quantidadde);
			} else {
				dinheiro = new DinheiroFicticio(ZERO);
			}
			
			return dinheiro;
		}
		
		public int fornecerQuantidade() {
			return montante;
		}
	}
}