package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.controller.ControladorDeDados;
import main.exception.ControladorDeDadosException;
import main.model.Funcionario;
import main.model.Ocorrencia;
import main.model.Projeto;

public class TesteControladorDeDados {
	
	private final String resumoOcorrencia = "Bug na classe Avião.";
	private final Ocorrencia.Prioridade prioridadeOcorrencia = Ocorrencia.Prioridade.ALTA;
	private final Ocorrencia.Tipo tipoOcorrencia = Ocorrencia.Tipo.BUG;
	
	private ControladorDeDados controlador;
	
	@Before
	public void setup(){
		this.controlador = new ControladorDeDados();
		Funcionario.zerarUID();
		Projeto.zerarUID();
		Ocorrencia.zerarUID();
	}
	
	// Cadastro de funcionario
	@Test
	public void cadastraUmNovoFuncionario() {
		long uidFunc = this.cadastraFuncionarioValido();
		
		assertEquals(1, uidFunc);
		assertEquals(1, controlador.getNumeroDeFuncionarios());
		assertTrue(controlador.funcionarioEstaCadastrado(uidFunc));
	}
	
	@Test
	public void cadastraDoisNovosFuncionarios() throws Exception {
		long uidFunc1 = this.cadastraFuncionarioValido();
		long uidFunc2 = this.cadastraFuncionarioValido();
		
		assertEquals(2, controlador.getNumeroDeFuncionarios());
		assertTrue(controlador.funcionarioEstaCadastrado(uidFunc1));
		assertTrue(controlador.funcionarioEstaCadastrado(uidFunc2));
	}
	
	// Cadastro de projeto
	@Test
	public void cadastraUmNovoProjeto() throws Exception {
		long uidProj = this.cadastraProjetoValido();
		
		assertEquals(1, uidProj);
		assertEquals(1, controlador.getNumeroDeProjetos());
		assertTrue(controlador.projetoEstaCadastrado(uidProj));
	}
	
	@Test
	public void cadastraDoisNovosProjetos() throws Exception {
		long uidProj1 = this.cadastraProjetoValido();
		long uidProj2 = this.cadastraProjetoValido();
		
		assertEquals(2, controlador.getNumeroDeProjetos());
		assertTrue(controlador.projetoEstaCadastrado(uidProj1));
		assertTrue(controlador.projetoEstaCadastrado(uidProj2));
	}
	
	// Cadastro de ocorrencia
	@Test
	public void cadastraUmaNovaOcorrencia() throws Exception {
		long uidOcorrencia = this.cadastraOcorrenciaValida();
		
		assertEquals(1, uidOcorrencia);
		assertEquals(1, controlador.getNumeroDeOcorrenciasAbertas());
		assertTrue(controlador.ocorrenciaEstaAberta(uidOcorrencia));
	}
	
	@Test
	public void cadastraDezOcorrenciasParaMesmoFuncionario() throws Exception {
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
		
		assertEquals(10, controlador.getNumeroDeOcorrenciasAbertas());
	}
	
	@Test(expected=ControladorDeDadosException.class)
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
	
	@Test(expected=ControladorDeDadosException.class)
	public void cadastraNovaOcorrenciaComUidFuncNaoCadastrado() throws Exception {
		long uidInvalidoDeFunc = 100;
		long uidProj = this.cadastraProjetoValido();

		controlador.cadastraOcorrencia(resumoOcorrencia, 
				prioridadeOcorrencia, tipoOcorrencia, uidInvalidoDeFunc, uidProj);
	}
	
	@Test(expected=ControladorDeDadosException.class)
	public void cadastraNovaOcorrenciaComUidProjNaoCadastrado() throws Exception {
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
	
	@Test(expected=ControladorDeDadosException.class)
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
	
	@Test
	public void procurarSegundaOcorrenciaCadastradaECompletada() throws Exception {
		this.cadastraOcorrenciaValida();
		long uidOcorrencia = this.cadastraOcorrenciaValida();
		controlador.concluirOcorrencia(uidOcorrencia);
		
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
