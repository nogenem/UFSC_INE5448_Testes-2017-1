package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import main.controller.ControladorDeDados;
import main.model.Ocorrencia;

public class TesteControladorDeDados {
	
	// Cadastro de funcionario
	@Test
	public void cadastraUmNovoFuncionario() {
		ControladorDeDados controlador = new ControladorDeDados();
		long uidFunc = controlador.cadastraFuncionario("Fulano da Silva");
		
		assertTrue(uidFunc > 0);
		assertEquals(1, controlador.getNumeroDeFuncionarios());
		assertTrue(controlador.funcionarioEstaCadastrado(uidFunc));
	}
	
	// Cadastro de projeto
	@Test
	public void cadastraUmNovoProjeto() throws Exception {
		ControladorDeDados controlador = new ControladorDeDados();
		long uidProj = controlador.cadastraProjeto("Projeto teste");
		
		assertTrue(uidProj > 0);
		assertEquals(1, controlador.getNumeroDeProjetos());
		assertTrue(controlador.projetoEstaCadastrado(uidProj));
	}
	
	// Cadastro de ocorrencia
	@Test
	public void cadastraUmaNovaOcorrencia() throws Exception {
		ControladorDeDados controlador = new ControladorDeDados();
		long uidFunc = controlador.cadastraFuncionario("Fulano da Silva");
		long uidProj = controlador.cadastraProjeto("Projeto teste");
		
		long uidOcorrencia = controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		
		assertTrue(uidOcorrencia > 0);
		assertEquals(1, controlador.getNumeroDeOcorrenciasAbertas());
		assertTrue(controlador.ocorrenciaEstaAberta(uidOcorrencia));
	}
	
	@Test(expected=Exception.class)
	public void cadastraOnzeOcorrenciasParaMesmoFuncionario() throws Exception {
		ControladorDeDados controlador = new ControladorDeDados();
		long uidFunc = controlador.cadastraFuncionario("Fulano da Silva");
		long uidProj = controlador.cadastraProjeto("Projeto teste");
		
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
	}
	
	@Test(expected=Exception.class)
	public void cadastraNovaOcorrenciaComUidFuncErrado() throws Exception {
		ControladorDeDados controlador = new ControladorDeDados();
		long uidFunc = 100;
		long uidProj = controlador.cadastraProjeto("Projeto teste");
		
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
	}
	
	@Test(expected=Exception.class)
	public void cadastraNovaOcorrenciaComUidProjErrado() throws Exception {
		ControladorDeDados controlador = new ControladorDeDados();
		long uidFunc = controlador.cadastraFuncionario("Fulano da Silva");
		long uidProj = 100;
		
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
	}
	
	@Test
	public void concluindoUmaOcorrencia() throws Exception {
		ControladorDeDados controlador = new ControladorDeDados();
		long uidFunc = controlador.cadastraFuncionario("Fulano da Silva");
		long uidProj = controlador.cadastraProjeto("Projeto teste");
		
		long uidOcorrencia = controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		
		controlador.concluirOcorrencia(uidOcorrencia);
	}
	
	@Test(expected=Exception.class)
	public void concluindoUmaOcorrenciaJaConcluida() throws Exception {
		ControladorDeDados controlador = new ControladorDeDados();
		long uidFunc = controlador.cadastraFuncionario("Fulano da Silva");
		long uidProj = controlador.cadastraProjeto("Projeto teste");
		
		long uidOcorrencia = controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		
		controlador.concluirOcorrencia(uidOcorrencia);
		controlador.concluirOcorrencia(uidOcorrencia);
	}
}
