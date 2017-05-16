package testes.gerenciador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.gerenciador.GerenciadorDeOcorrencias;
import main.model.Funcionario;
import main.model.Ocorrencia;

public class TesteGerenciadorOcorrencias {

	@Test
	public void cadastraNovaOcorrencia() throws Exception {
		GerenciadorDeOcorrencias gerenciador = new GerenciadorDeOcorrencias();	
		Funcionario funcionario = new Funcionario("Fulano da Silva");
		Ocorrencia ocorrencia = new Ocorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião");
		boolean retorno = gerenciador.cadastraOcorrencia(ocorrencia, funcionario);
		
		assertTrue(retorno);
		assertEquals(1, gerenciador.getNumeroTotalDeOcorrencias());
	}
	
	@Test(expected=Exception.class)
	public void cadastraOnzeOcorrenciasParaMesmoFuncionario() throws Exception {
		GerenciadorDeOcorrencias gerenciador = new GerenciadorDeOcorrencias();	
		Funcionario funcionario = new Funcionario("Fulano da Silva");
		Ocorrencia ocorrencia = new Ocorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião");
		
		gerenciador.cadastraOcorrencia(ocorrencia, funcionario);
		gerenciador.cadastraOcorrencia(ocorrencia, funcionario);
		gerenciador.cadastraOcorrencia(ocorrencia, funcionario);
		gerenciador.cadastraOcorrencia(ocorrencia, funcionario);
		gerenciador.cadastraOcorrencia(ocorrencia, funcionario);
		gerenciador.cadastraOcorrencia(ocorrencia, funcionario);
		gerenciador.cadastraOcorrencia(ocorrencia, funcionario);
		gerenciador.cadastraOcorrencia(ocorrencia, funcionario);
		gerenciador.cadastraOcorrencia(ocorrencia, funcionario);
		gerenciador.cadastraOcorrencia(ocorrencia, funcionario);
		gerenciador.cadastraOcorrencia(ocorrencia, funcionario);
	}

}
