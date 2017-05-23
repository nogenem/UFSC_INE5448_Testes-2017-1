package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.controller.ControladorDeDados;
import main.model.Ocorrencia;

public class TesteControladorDeDados {
	
	private ControladorDeDados controlador;
	private long uidFunc;
	private long uidProj;
	
	@Before
	public void setup(){
		this.controlador = new ControladorDeDados();
		this.uidFunc = this.controlador.cadastraFuncionario("Fulano da Silva");
		this.uidProj = this.controlador.cadastraProjeto("Projeto teste");
	}
	
	// Cadastro de funcionario
	@Test
	public void cadastraUmNovoFuncionario() {
		long uidFunc = controlador.cadastraFuncionario("Fulano Souza da Silva");
		
		assertTrue(uidFunc > 0);
		assertEquals(1, controlador.getNumeroDeFuncionarios());
		assertTrue(controlador.funcionarioEstaCadastrado(uidFunc));
	}
	
	// Cadastro de projeto
	@Test
	public void cadastraUmNovoProjeto() throws Exception {
		long uidProj = controlador.cadastraProjeto("Projeto teste 2");
		
		assertTrue(uidProj > 0);
		assertEquals(1, controlador.getNumeroDeProjetos());
		assertTrue(controlador.projetoEstaCadastrado(uidProj));
	}
	
	// Cadastro de ocorrencia
	@Test
	public void cadastraUmaNovaOcorrencia() throws Exception {
		long uidOcorrencia = controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		
		assertTrue(uidOcorrencia > 0);
		assertEquals(1, controlador.getNumeroDeOcorrenciasAbertas());
		assertTrue(controlador.ocorrenciaEstaAberta(uidOcorrencia));
	}
	
	@Test(expected=Exception.class)
	public void cadastraOnzeOcorrenciasParaMesmoFuncionario() throws Exception {
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
		long uidFunc = 100;
		
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
	}
	
	@Test(expected=Exception.class)
	public void cadastraNovaOcorrenciaComUidProjErrado() throws Exception {
		long uidProj = 100;
		
		controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
	}
	
	@Test
	public void concluindoUmaOcorrencia() throws Exception {
		long uidOcorrencia = controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		
		controlador.concluirOcorrencia(uidOcorrencia);
	}
	
	@Test(expected=Exception.class)
	public void concluindoUmaOcorrenciaJaConcluida() throws Exception {
		long uidOcorrencia = controlador.cadastraOcorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG, uidFunc, uidProj);
		
		controlador.concluirOcorrencia(uidOcorrencia);
		controlador.concluirOcorrencia(uidOcorrencia);
	}
}
