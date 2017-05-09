package br.ufsc.es.projetoPoquer.bancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.ufsc.es.projetoPoquer.modelo.adminitracao.MantenedorFeijao;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao.JogadorFeijao;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.TorneioFeijao;

public final class BancoDeDados {
	
	private static final BancoDeDados INSTÂNCIA = new BancoDeDados();
	
	private Connection conexãoComBancoDeDados;
	private MapeadorJogador mapeadorJogador;
	private MapeadorMantenedor mapeadorMantenedor;
	private MapeadorTorneio mapeadorTorneio;
	
	private BancoDeDados() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		   	conexãoComBancoDeDados = DriverManager.getConnection("jdbc:mysql://localhost/poquer","root","");
		} catch (ClassNotFoundException erro) {
			erro.printStackTrace();
		} catch (SQLException erro) {
			erro.printStackTrace();
		}
		mapeadorJogador = new MapeadorJogador(conexãoComBancoDeDados);
		mapeadorMantenedor = new MapeadorMantenedor(conexãoComBancoDeDados);
		mapeadorTorneio = new MapeadorTorneio(conexãoComBancoDeDados);
	}
	
	public static BancoDeDados fornecerInstância() {
		return INSTÂNCIA;
	}
	
	public OperacaoDeDados<JogadorFeijao> adicionarJogador(JogadorFeijao jogadorFeijão) {
		return mapeadorJogador.adicionar(jogadorFeijão);
	}
	
	public OperacaoDeDados<JogadorFeijao> atualizarJogador(JogadorFeijao jogadorFeijão) {
		return mapeadorJogador.atualizar(jogadorFeijão);
	}
	
	public OperacaoDeDados<JogadorFeijao> fornecerJogador(NomeDeUsuario nomeDeUsuário) {
		return mapeadorJogador.fornecer(nomeDeUsuário);
	}
	
	public OperacaoDeDados<JogadorFeijao> removerJogador(NomeDeUsuario nomeDeUsuário) {
		return mapeadorJogador.remover(nomeDeUsuário);
	}
	
	public OperacaoDeDados<MantenedorFeijao> adicionarMantenedor(MantenedorFeijao mantenedorFeijão) {
		return mapeadorMantenedor.adicionar(mantenedorFeijão);
	}
	
	public OperacaoDeDados<MantenedorFeijao> atualizarMantenedor(MantenedorFeijao mantenedorFeijão) {
		return mapeadorMantenedor.atualizar(mantenedorFeijão);
	}
	
	public OperacaoDeDados<MantenedorFeijao> fornecerMantenedores() {
		return mapeadorMantenedor.fornecerTodos();
	}
	
	public OperacaoDeDados<MantenedorFeijao> fornecerMantenedor(String nomeDeMantenedor) {
		return mapeadorMantenedor.fornecer(nomeDeMantenedor);
	}
	
	public OperacaoDeDados<MantenedorFeijao> removerMantenedor(String nomeDeMantnedor) {
		return mapeadorMantenedor.remover(nomeDeMantnedor);
	}
	
	public OperacaoDeDados<TorneioFeijao> adicionarTorneio(TorneioFeijao torneioFeijão) {
		return mapeadorTorneio.adicionar(torneioFeijão);
	}
	
	public OperacaoDeDados<TorneioFeijao> fornecerTorneios() {
		return mapeadorTorneio.fornecerTodos();
	}
	
	public OperacaoDeDados<TorneioFeijao> removerTorneio(String identificador) {
		return mapeadorTorneio.remover(identificador);
	}
}