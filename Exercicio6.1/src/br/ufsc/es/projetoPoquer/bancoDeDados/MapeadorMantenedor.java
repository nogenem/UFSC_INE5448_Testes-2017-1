package br.ufsc.es.projetoPoquer.bancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.ufsc.es.projetoPoquer.modelo.adminitracao.MantenedorFeijao;

public final class MapeadorMantenedor {
	
	private Connection conexãoComBancoDeDados;
	
	private static final String ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS = "Houve um erro de operação no banco de dados.";
	
	public MapeadorMantenedor(Connection conexãoComBancoDeDados) {
		this.conexãoComBancoDeDados = conexãoComBancoDeDados;
	}
	
	public OperacaoDeDados<MantenedorFeijao> adicionar(MantenedorFeijao elemento) {
		OperacaoDeDados<MantenedorFeijao> operação = null;
		PreparedStatement sentença = null;
		try {
			sentença = conexãoComBancoDeDados.prepareStatement("INSERT INTO " +
					"mantenedor(nomeDeMantenedor, email, senha) VALUES(?, ?, ?)");
			sentença.setString(1, elemento.fornecerNomeDeMantenedor());
			sentença.setString(2, elemento.fornecerEmail());
			sentença.setString(3, elemento.fornecerSenha());
			if (sentença.executeUpdate() == 1) {
				operação = new OperacaoDeDados<MantenedorFeijao>();
			} else {
				operação = new OperacaoDeDados<MantenedorFeijao>(ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS);
			}
		} catch (SQLException erro) {
			operação = new OperacaoDeDados<MantenedorFeijao>(ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS);
		} finally {
			try {
				sentença.close();		
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
		
		return operação;
	}

	public OperacaoDeDados<MantenedorFeijao> fornecerTodos() {
		OperacaoDeDados<MantenedorFeijao> operação = null;
		PreparedStatement sentença = null;
		ResultSet resultadoDaConsulta = null;
		try {
			sentença = conexãoComBancoDeDados.prepareStatement("SELECT nomeDeMantenedor, email, senha " +
					"FROM mantenedor WHERE true");
			resultadoDaConsulta = sentença.executeQuery();
			List<MantenedorFeijao> mantenedoresFeijão = new LinkedList<MantenedorFeijao>();
			while (resultadoDaConsulta.next()) {
				MantenedorFeijao mantenedorFeijão = new MantenedorFeijao();
				mantenedorFeijão.fixarNomeDeMantenedor(resultadoDaConsulta.getString("nomeDeMantenedor"));
				mantenedorFeijão.fixarEmail(resultadoDaConsulta.getString("email"));
				mantenedorFeijão.fixarSenha(resultadoDaConsulta.getString("senha"));
				mantenedoresFeijão.add(mantenedorFeijão);
			}
			operação = new OperacaoDeDados<MantenedorFeijao>(mantenedoresFeijão);
		} catch (SQLException erro) {
			operação = new OperacaoDeDados<MantenedorFeijao>(ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS);
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

	public OperacaoDeDados<MantenedorFeijao> fornecer(String identificador) {
		OperacaoDeDados<MantenedorFeijao> operação = null;
		PreparedStatement sentença = null;
		ResultSet resultadoDaConsulta = null;
		try {
			sentença = conexãoComBancoDeDados.prepareStatement("SELECT nomeDeMantenedor, email, senha " +
					"FROM mantenedor WHERE nomeDeMantenedor=?");
			sentença.setString(1, identificador);
			resultadoDaConsulta = sentença.executeQuery();
			if (resultadoDaConsulta.next()) {
				MantenedorFeijao mantenedorFeijão = new MantenedorFeijao();
				mantenedorFeijão.fixarNomeDeMantenedor(resultadoDaConsulta.getString("nomeDeMantenedor"));
				mantenedorFeijão.fixarEmail(resultadoDaConsulta.getString("email"));
				mantenedorFeijão.fixarSenha(resultadoDaConsulta.getString("senha"));
				operação = new OperacaoDeDados<MantenedorFeijao>(mantenedorFeijão);
			} else {
				operação = new OperacaoDeDados<MantenedorFeijao>(ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS);
			}
		} catch (SQLException erro) {
			operação = new OperacaoDeDados<MantenedorFeijao>(ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS);
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
	
	public OperacaoDeDados<MantenedorFeijao> remover(String identificador) {
		OperacaoDeDados<MantenedorFeijao> operação = null;
		PreparedStatement sentença = null;
		try {
			sentença = conexãoComBancoDeDados.prepareStatement("DELETE FROM mantenedor WHERE nomeDeMantenedor=?");
			sentença.setString(1, identificador);	
			if (sentença.executeUpdate() == 1) {
				operação = new OperacaoDeDados<MantenedorFeijao>();
			}
			else {
				operação = new OperacaoDeDados<MantenedorFeijao>(ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS);
			}
		} catch (SQLException erro) {
			operação = new OperacaoDeDados<MantenedorFeijao>(ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS);
		} finally {
			try {
				sentença.close();		
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
		
		return operação;
	}

	public OperacaoDeDados<MantenedorFeijao> atualizar(MantenedorFeijao elemento) {
		return new OperacaoDeDados<MantenedorFeijao>(ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS);
	}
}