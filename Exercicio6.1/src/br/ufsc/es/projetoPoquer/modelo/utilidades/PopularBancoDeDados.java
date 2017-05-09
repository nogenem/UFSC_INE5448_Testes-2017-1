package br.ufsc.es.projetoPoquer.modelo.utilidades;

import br.ufsc.es.projetoPoquer.bancoDeDados.BancoDeDados;
import br.ufsc.es.projetoPoquer.modelo.adminitracao.MantenedorFeijao;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao.Email;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao.JogadorFeijao;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao.Nome;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao.Senha;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.Descricao;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.QuantidadeDeVagas;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.TorneioFeijao;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.ValorDaEntrada;

public class PopularBancoDeDados {
	
	private static MantenedorFeijao mantenedorZeus;
	private static MantenedorFeijao mantenedorAres;
	
	public static void main(String[] args) {
		popularJogadores();
		popularMantenedores();
		popularTorneios();
		System.out.println("O banco de dados foi populado.");
	}
	
	private static void popularJogadores() {
		JogadorFeijao hades = new JogadorFeijao();
		hades.fixarNome(new Nome("Hades"));
		hades.fixarNomeDeUsuário(new NomeDeUsuario("hades"));
		hades.fixarEmail(new Email("hades@submundo.net"));
		hades.fixarSenha(new Senha("jogador"));
		BancoDeDados.fornecerInstância().adicionarJogador(hades);
	}
	
	private static void popularMantenedores() {
		MantenedorFeijao zeus = new MantenedorFeijao();
		mantenedorZeus = zeus;
		zeus.fixarNomeDeMantenedor("zeus");
		zeus.fixarEmail("zeus@olimpo.com");
		zeus.fixarSenha("mantenedor");
		BancoDeDados.fornecerInstância().adicionarMantenedor(zeus);
		
		MantenedorFeijao ares = new MantenedorFeijao();
		mantenedorAres = ares;
		ares.fixarNomeDeMantenedor("ares");
		ares.fixarEmail("ares@olimpo.com");
		ares.fixarSenha("mantenedor");
		BancoDeDados.fornecerInstância().adicionarMantenedor(ares);
	}
	
	private static void popularTorneios() {
		TorneioFeijao texasHoldem = new TorneioFeijao();
		texasHoldem.fixarDescricao(new Descricao("Texas Holdem - No-Limit - Gratuito"));
		texasHoldem.fixarMantenedorCriador(mantenedorZeus);
		texasHoldem.fixarQuantidadeDeVagas(new QuantidadeDeVagas(9, 9));
		texasHoldem.fixarValorDaEntrada(new ValorDaEntrada(0));
		BancoDeDados.fornecerInstância().adicionarTorneio(texasHoldem);
		
		TorneioFeijao texasHoldemLimit = new TorneioFeijao();
		texasHoldemLimit.fixarDescricao(new Descricao("Texas Holdem - Limit - F$ 100,00"));
		texasHoldemLimit.fixarMantenedorCriador(mantenedorAres);
		texasHoldemLimit.fixarQuantidadeDeVagas(new QuantidadeDeVagas(6, 6));
		texasHoldemLimit.fixarValorDaEntrada(new ValorDaEntrada(100));
		BancoDeDados.fornecerInstância().adicionarTorneio(texasHoldemLimit);
		
		TorneioFeijao omaha = new TorneioFeijao();
		omaha.fixarDescricao(new Descricao("Omaha - Limit - Satélite para o BSOP"));
		omaha.fixarMantenedorCriador(mantenedorAres);
		omaha.fixarQuantidadeDeVagas(new QuantidadeDeVagas(27, 27));
		omaha.fixarValorDaEntrada(new ValorDaEntrada(1));
		BancoDeDados.fornecerInstância().adicionarTorneio(omaha);
	}
}