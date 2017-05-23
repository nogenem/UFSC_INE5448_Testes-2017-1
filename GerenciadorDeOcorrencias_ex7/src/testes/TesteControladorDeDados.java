package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.controller.ControladorDeDados;
import main.model.Ocorrencia;

public class TesteControladorDeDados {
	
	private final String resumoOcorrencia = "Bug na classe Avião.";
	private final Ocorrencia.Prioridade prioridadeOcorrencia = Ocorrencia.Prioridade.ALTA;
	private final Ocorrencia.Tipo tipoOcorrencia = Ocorrencia.Tipo.BUG;
	
	private ControladorDeDados controlador;
	
	@Before
	public void setup(){
		this.controlador = new ControladorDeDados();
	}
	
	// Cadastro de funcionario
	@Test
	public void cadastraUmNovoFuncionario() {
		long uidFunc = this.cadastraFuncionarioValido();
		
		assertEquals(1, uidFunc);
		assertEquals(1, controlador.getNumeroDeFuncionarios());
		assertTrue(controlador.funcionarioEstaCadastrado(uidFunc));
	}
	
	// Cadastro de projeto
	@Test
	public void cadastraUmNovoProjeto() throws Exception {
		long uidProj = this.cadastraProjetoValido();
		
		assertEquals(1, uidProj);
		assertEquals(1, controlador.getNumeroDeProjetos());
		assertTrue(controlador.projetoEstaCadastrado(uidProj));
	}
	
	// Cadastro de ocorrencia
	@Test
	public void cadastraUmaNovaOcorrencia() throws Exception {
		long uidOcorrencia = this.cadastraOcorrenciaValida();
		
		assertEquals(1, uidOcorrencia);
		assertEquals(1, controlador.getNumeroDeOcorrenciasAbertas());
		assertTrue(controlador.ocorrenciaEstaAberta(uidOcorrencia));
	}
	
	@Test(expected=Exception.class)
	public void cadastraOnzeOcorrenciasParaMesmoFuncionario() throws Exception {
		long uidFunc = this.cadastraFuncionarioValido();
		long uidProj = this.cadastraProjetoValido();
		
		controlador.cadastraOcorrencia(resumoOcorrencia, 
				prioridadeOcorrencia, tipoOcorrencia, uidFunc, uidProj);
		controlador.cadastraOcorrencia(resumoOcorrencia, 
				prioridadeOcorrencia, tipoOcorrencia, uidFunc, uidProj);
		controlador.cadastraOcorrencia(resumoOcorrencia, 
				prioridadeOcorrencia, tipoOcorrencia, uidFunc, uidProj);
		controlador.cadastraOcorrencia(resumoOcorrencia, 
				prioridadeOcorrencia, tipoOcorrencia, uidFunc, uidProj);
		controlador.cadastraOcorrencia(resumoOcorrencia, 
				prioridadeOcorrencia, tipoOcorrencia, uidFunc, uidProj);
		controlador.cadastraOcorrencia(resumoOcorrencia, 
				prioridadeOcorrencia, tipoOcorrencia, uidFunc, uidProj);
		controlador.cadastraOcorrencia(resumoOcorrencia, 
				prioridadeOcorrencia, tipoOcorrencia, uidFunc, uidProj);
		controlador.cadastraOcorrencia(resumoOcorrencia, 
				prioridadeOcorrencia, tipoOcorrencia, uidFunc, uidProj);
		controlador.cadastraOcorrencia(resumoOcorrencia, 
				prioridadeOcorrencia, tipoOcorrencia, uidFunc, uidProj);
		controlador.cadastraOcorrencia(resumoOcorrencia, 
				prioridadeOcorrencia, tipoOcorrencia, uidFunc, uidProj);
		controlador.cadastraOcorrencia(resumoOcorrencia, 
				prioridadeOcorrencia, tipoOcorrencia, uidFunc, uidProj);
	}
	
	@Test(expected=Exception.class)
	public void cadastraNovaOcorrenciaComUidFuncErrado() throws Exception {
		long uidInvalidoDeFunc = 100;
		long uidProj = this.cadastraProjetoValido();

		controlador.cadastraOcorrencia(resumoOcorrencia, 
				prioridadeOcorrencia, tipoOcorrencia, uidInvalidoDeFunc, uidProj);
	}
	
	@Test(expected=Exception.class)
	public void cadastraNovaOcorrenciaComUidProjErrado() throws Exception {
		long uidFunc = this.cadastraFuncionarioValido();
		long uidInvalidoDeProj = 100;
		
		controlador.cadastraOcorrencia(resumoOcorrencia, 
				prioridadeOcorrencia, tipoOcorrencia, uidFunc, uidInvalidoDeProj);
	}
	
	@Test
	public void concluindoUmaOcorrencia() throws Exception {
		long uidOcorrencia = this.cadastraOcorrenciaValida();
		
		controlador.concluirOcorrencia(uidOcorrencia);
	}
	
	@Test(expected=Exception.class)
	public void concluindoUmaOcorrenciaJaConcluida() throws Exception {
		long uidOcorrencia = this.cadastraOcorrenciaValida();
		
		controlador.concluirOcorrencia(uidOcorrencia);
		controlador.concluirOcorrencia(uidOcorrencia);
	}
	
	@Test
	public void procurarUmaOcorrenciaNaoCadastrada() throws Exception {
		long uidInvalidoDeOcorrencia = 100;
		
		assertFalse(controlador.ocorrenciaEstaCadastrada(uidInvalidoDeOcorrencia));
	}
	
	@Test
	public void procurarUmaOcorrenciaCadastrada() throws Exception {
		long uidOcorrencia = this.cadastraOcorrenciaValida();
		
		assertTrue(controlador.ocorrenciaEstaCadastrada(uidOcorrencia));
	}
	
	// Métodos uteis
	private long cadastraFuncionarioValido(){
		return this.controlador.cadastraFuncionario("Fulano da Silva");
	}
	
	private long cadastraProjetoValido(){
		return this.controlador.cadastraProjeto("Projeto teste");
	}
	
	private long cadastraOcorrenciaValida() throws Exception {
		long uidFunc = this.cadastraFuncionarioValido();
		long uidProj = this.cadastraProjetoValido();
		return this.controlador.cadastraOcorrencia(resumoOcorrencia, prioridadeOcorrencia, 
				tipoOcorrencia, uidFunc, uidProj);
	}
}
