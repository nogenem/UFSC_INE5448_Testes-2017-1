package br.ufsc.es.projetoPoquer.bancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao.Email;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao.JogadorFeijao;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao.Nome;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao.Senha;

public final class MapeadorJogador {
	
	private Connection conexãoComBancoDeDados;
	
	private static final String JOGADOR_EXISTENTE = "Já existe um jogador com este nome de usuário.";
	private static final String JOGADOR_INEXISTENTE = "O jogador não está cadastrado.";
	
	public MapeadorJogador(Connection conexãoComBancoDeDados) {
		this.conexãoComBancoDeDados = conexãoComBancoDeDados;
	}
	
	public OperacaoDeDados<JogadorFeijao> adicionar(JogadorFeijao jogadorFeijão) {
		OperacaoDeDados<JogadorFeijao> operação = null;
		PreparedStatement sentença = null;
		try {
			sentença = conexãoComBancoDeDados.prepareStatement("INSERT INTO " +
					"jogador(nome, nomeDeUsuario, email, senha) VALUES(?, ?, ?, ?)");
			sentença.setString(1, jogadorFeijão.fornecerNome().fornecerComoTexto());	
			sentença.setString(2, jogadorFeijão.fornecerNomeDeUsuário().fornecerComoTexto());
			sentença.setString(3, jogadorFeijão.fornecerEmail().fornecerComoTexto());
			sentença.setString(4, jogadorFeijão.fornecerSenha().fornecerComoTexto());
			if (sentença.executeUpdate() == 1) {
				operação = new OperacaoDeDados<JogadorFeijao>();
			}
		} catch (SQLException erro) {
			operação = new OperacaoDeDados<JogadorFeijao>(JOGADOR_EXISTENTE);
		} finally {
			try {
				sentença.close();		
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
		
		return operação;
	}

	public OperacaoDeDados<JogadorFeijao> atualizar(JogadorFeijao jogadorFeijão) {
		OperacaoDeDados<JogadorFeijao> operação = null;
		PreparedStatement sentença = null;
		try {
			sentença = conexãoComBancoDeDados.prepareStatement("UPDATE jogador SET " +
					"nome=?, nomeDeUsuario=?, email=?, senha=? WHERE nomeDeUsuario=?");
			sentença.setString(1, jogadorFeijão.fornecerNome().fornecerComoTexto());	
			sentença.setString(2, jogadorFeijão.fornecerNomeDeUsuário().fornecerComoTexto());
			sentença.setString(3, jogadorFeijão.fornecerEmail().fornecerComoTexto());
			sentença.setString(4, jogadorFeijão.fornecerSenha().fornecerComoTexto());
			sentença.setString(5, jogadorFeijão.fornecerNomeDeUsuário().fornecerComoTexto());
			if (sentença.executeUpdate() == 0) {
				operação = new OperacaoDeDados<JogadorFeijao>(JOGADOR_INEXISTENTE);
			} else {
				operação = new OperacaoDeDados<JogadorFeijao>();
			}
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			try {
				sentença.close();		
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
		
		return operação;
	}

	public OperacaoDeDados<JogadorFeijao> fornecer(NomeDeUsuario identificador) {
		OperacaoDeDados<JogadorFeijao> operação = null;
		PreparedStatement sentença = null;
		ResultSet resultadoDaConsulta = null;
		try {
			sentença = conexãoComBancoDeDados.prepareStatement("SELECT nome, nomeDeUsuario, email, senha " +
					"FROM jogador WHERE nomeDeUsuario=?");
			sentença.setString(1, identificador.fornecerComoTexto());	
			resultadoDaConsulta = sentença.executeQuery();
			if (resultadoDaConsulta.next()) {
				JogadorFeijao jogadorFeijão = new JogadorFeijao();
				jogadorFeijão.fixarNome(new Nome(resultadoDaConsulta.getString("nome")));
				jogadorFeijão.fixarNomeDeUsuário(new NomeDeUsuario(resultadoDaConsulta.getString("nomeDeUsuario")));
				jogadorFeijão.fixarEmail(new Email(resultadoDaConsulta.getString("email")));
				jogadorFeijão.fixarSenha(new Senha(resultadoDaConsulta.getString("senha")));
				operação = new OperacaoDeDados<JogadorFeijao>(jogadorFeijão);
			}
			else {
				operação = new OperacaoDeDados<JogadorFeijao>(JOGADOR_INEXISTENTE);
			}
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			try {
				resultadoDaConsulta.close();
				sentença.close();		
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
		
		return operação;
	}

	public OperacaoDeDados<JogadorFeijao> remover(NomeDeUsuario identificador) {
		OperacaoDeDados<JogadorFeijao> operação = null;
		PreparedStatement sentença = null;
		try {
			sentença = conexãoComBancoDeDados.prepareStatement("DELETE FROM jogador WHERE nomeDeUsuario=?");
			sentença.setString(1, identificador.fornecerComoTexto());	
			if (sentença.executeUpdate() == 1) {
				operação = new OperacaoDeDados<JogadorFeijao>();
			}
			else {
				operação = new OperacaoDeDados<JogadorFeijao>(JOGADOR_INEXISTENTE);
			}
		} catch (SQLException erro) {
			erro.printStackTrace();
		} finally {
			try {
				sentença.close();		
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
		
		return operação;
	}
}