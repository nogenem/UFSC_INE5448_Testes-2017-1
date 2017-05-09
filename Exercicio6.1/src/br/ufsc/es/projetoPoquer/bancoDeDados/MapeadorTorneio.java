package br.ufsc.es.projetoPoquer.bancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.ufsc.es.projetoPoquer.modelo.adminitracao.MantenedorFeijao;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.Identificador;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.Descricao;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.QuantidadeDeVagas;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.TorneioFeijao;
import br.ufsc.es.projetoPoquer.modelo.torneio.dados.feijao.ValorDaEntrada;

public final class MapeadorTorneio {
	
	private Connection conexãoComBancoDeDados;
	
	private static final String ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS = "Houve um erro de operação no banco de dados.";
	
	public MapeadorTorneio(Connection conexãoComBancoDeDados) {
		this.conexãoComBancoDeDados = conexãoComBancoDeDados;
	}
	
	public OperacaoDeDados<TorneioFeijao> adicionar(TorneioFeijao elemento) {
		OperacaoDeDados<TorneioFeijao> operação = null;
		PreparedStatement sentença = null;
		try {
			sentença = conexãoComBancoDeDados.prepareStatement("INSERT INTO " +
					"torneio(descricao, valorDaEntrada, vagasOcupadas, vagasDisponiveis, mantenedorCriador) " +
					"VALUES(?, ?, ?, ?, ?)");
			sentença.setString(1, elemento.fornecerDescrição().fornecerComoTexto());
			sentença.setInt(2, elemento.fornecerValorDaEntrada().fornecerComoNúmero());
			sentença.setInt(3, elemento.fornecerQuantidadeDeVagas().fornecerVagasOcupadas());
			sentença.setInt(4, elemento.fornecerQuantidadeDeVagas().fornecerVagasDisponíveis());
			sentença.setString(5, elemento.fornecerMantenedorCriador().fornecerNomeDeMantenedor());
			if (sentença.executeUpdate() == 1) {
				operação = new OperacaoDeDados<TorneioFeijao>();
			} else {
				operação = new OperacaoDeDados<TorneioFeijao>(ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS);
			}
		} catch (SQLException erro) {
			operação = new OperacaoDeDados<TorneioFeijao>(ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS);
		} finally {
			try {
				sentença.close();		
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
		
		return operação;
	}

	public OperacaoDeDados<TorneioFeijao> fornecerTodos() {
		OperacaoDeDados<TorneioFeijao> operação = null;
		PreparedStatement sentença = null;
		ResultSet resultadoDaConsulta = null;
		try {
			sentença = conexãoComBancoDeDados.prepareStatement("SELECT identificador, descricao, valorDaEntrada, vagasOcupadas, vagasDisponiveis, " +
					"mantenedorCriador FROM torneio WHERE true");
			resultadoDaConsulta = sentença.executeQuery();
			List<TorneioFeijao> torneiosFeijão = new LinkedList<TorneioFeijao>();
			while (resultadoDaConsulta.next()) {
				TorneioFeijao torneioFeijão = new TorneioFeijao();
				torneioFeijão.fixarIdentificador(new Identificador(resultadoDaConsulta.getInt("identificador")));
				torneioFeijão.fixarDescricao(new Descricao(resultadoDaConsulta.getString("descricao")));
				torneioFeijão.fixarValorDaEntrada(new ValorDaEntrada(resultadoDaConsulta.getInt("valorDaEntrada")));
				torneioFeijão.fixarQuantidadeDeVagas(new QuantidadeDeVagas(resultadoDaConsulta.getInt("vagasOcupadas"), resultadoDaConsulta.getInt("vagasOcupadas")+resultadoDaConsulta.getInt("vagasDisponiveis")));
				MantenedorFeijao mantenedorCriador = new MantenedorFeijao();
				mantenedorCriador.fixarNomeDeMantenedor(resultadoDaConsulta.getString("mantenedorCriador"));
				torneioFeijão.fixarMantenedorCriador(mantenedorCriador);
				torneiosFeijão.add(torneioFeijão);
			}
			operação = new OperacaoDeDados<TorneioFeijao>(torneiosFeijão);
		} catch (SQLException erro) {
			erro.printStackTrace();
			operação = new OperacaoDeDados<TorneioFeijao>(ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS);
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

	public OperacaoDeDados<TorneioFeijao> remover(String identificador) {
		OperacaoDeDados<TorneioFeijao> operação = null;
		PreparedStatement sentença = null;
		try {
			sentença = conexãoComBancoDeDados.prepareStatement("DELETE FROM torneio WHERE identificador=?");
			sentença.setString(1, identificador);	
			if (sentença.executeUpdate() == 1) {
				operação = new OperacaoDeDados<TorneioFeijao>();
			}
			else {
				operação = new OperacaoDeDados<TorneioFeijao>(ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS);
			}
		} catch (SQLException erro) {
			operação = new OperacaoDeDados<TorneioFeijao>(ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS);
		} finally {
			try {
				sentença.close();		
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
		
		return operação;
	}

	public OperacaoDeDados<TorneioFeijao> atualizar(TorneioFeijao elemento) {
		return new OperacaoDeDados<TorneioFeijao>(ERRO_DE_OPERAÇÃO_NO_BANCO_DE_DADOS);
	}
}