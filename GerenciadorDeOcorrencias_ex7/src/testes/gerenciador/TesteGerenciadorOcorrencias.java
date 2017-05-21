package testes.gerenciador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.gerenciador.GerenciadorDeFuncionarios;
import main.gerenciador.GerenciadorDeOcorrencias;
import main.model.Ocorrencia;

public class TesteGerenciadorOcorrencias {

	@Test
	public void cadastraNovaOcorrencia() throws Exception {
		GerenciadorDeOcorrencias gerenciadorDeOcorrencias = new GerenciadorDeOcorrencias();	
		GerenciadorDeFuncionarios gerenciadorDeFuncionarios = new GerenciadorDeFuncionarios();
		
		long uidFunc = gerenciadorDeFuncionarios.cadastraFuncinario("Fulano da Silva");
		long uidOcorrencia = gerenciadorDeOcorrencias.cadastraOcorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião", 
				gerenciadorDeFuncionarios.getFuncionario(uidFunc));
		
		assertTrue(uidOcorrencia > 0);
		assertEquals(1, gerenciadorDeOcorrencias.getNumeroTotalDeOcorrencias());
	}
	
	@Test(expected=Exception.class)
	public void cadastraOnzeOcorrenciasParaMesmoFuncionario() throws Exception {
		GerenciadorDeOcorrencias gerenciadorDeOcorrencias = new GerenciadorDeOcorrencias();	
		GerenciadorDeFuncionarios gerenciadorDeFuncionarios = new GerenciadorDeFuncionarios();
		
		long uidFunc = gerenciadorDeFuncionarios.cadastraFuncinario("Fulano da Silva");
		
		gerenciadorDeOcorrencias.cadastraOcorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião", 
				gerenciadorDeFuncionarios.getFuncionario(uidFunc));
		gerenciadorDeOcorrencias.cadastraOcorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião", 
				gerenciadorDeFuncionarios.getFuncionario(uidFunc));
		gerenciadorDeOcorrencias.cadastraOcorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião", 
				gerenciadorDeFuncionarios.getFuncionario(uidFunc));
		gerenciadorDeOcorrencias.cadastraOcorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião", 
				gerenciadorDeFuncionarios.getFuncionario(uidFunc));
		gerenciadorDeOcorrencias.cadastraOcorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião", 
				gerenciadorDeFuncionarios.getFuncionario(uidFunc));
		gerenciadorDeOcorrencias.cadastraOcorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião", 
				gerenciadorDeFuncionarios.getFuncionario(uidFunc));
		gerenciadorDeOcorrencias.cadastraOcorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião", 
				gerenciadorDeFuncionarios.getFuncionario(uidFunc));
		gerenciadorDeOcorrencias.cadastraOcorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião", 
				gerenciadorDeFuncionarios.getFuncionario(uidFunc));
		gerenciadorDeOcorrencias.cadastraOcorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião", 
				gerenciadorDeFuncionarios.getFuncionario(uidFunc));
		gerenciadorDeOcorrencias.cadastraOcorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião", 
				gerenciadorDeFuncionarios.getFuncionario(uidFunc));
		gerenciadorDeOcorrencias.cadastraOcorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião", 
				gerenciadorDeFuncionarios.getFuncionario(uidFunc));
	}
}
