package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import main.model.Funcionario;
import main.model.Ocorrencia;
import main.model.Projeto;

public class TesteProjeto {

	@Before
	public void setup(){
		Projeto.zerarUID();
	}
	
	@Test
	public void criaNovoProjeto() {
		Projeto projetoTeste = new Projeto("Projeto teste");
		
		assertEquals(1, projetoTeste.getUid());
		assertEquals("Projeto teste", projetoTeste.getNome());
		assertEquals(0, projetoTeste.getOcorrencias().size());
	}
	
	@Test
	public void adicionaUmaOcorrenciaAoProjetoComResponsavelNaoPertencenteAoProjeto() throws Exception {
		Projeto projetoTeste = new Projeto("Projeto teste");
		Ocorrencia ocorrencia = this.criaNovaOcorrenciaValida();
		
		projetoTeste.addOcorrencia(ocorrencia);
		
		assertEquals(1, projetoTeste.getNumeroDeOcorrencias());
	}
	
	@Test
	public void adicionaUmaOcorrenciaAoProjetoComResponsavelPertencenteAoProjeto() throws Exception {
		Projeto projetoTeste = new Projeto("Projeto teste");
		Ocorrencia ocorrencia = this.criaNovaOcorrenciaValida();
		
		projetoTeste.addParticipante(ocorrencia.getResponsavel());
		projetoTeste.addOcorrencia(ocorrencia);
		
		assertEquals(1, projetoTeste.getNumeroDeOcorrencias());
	}
	
	@Test(expected=Exception.class)
	public void adicionaUmaOcorrenciaAoProjetoSemResponsavel() throws Exception {
		Projeto projetoTeste = new Projeto("Projeto teste");
		Ocorrencia ocorrencia = this.criaNovaOcorrenciaValida();
		ocorrencia.setResponsavel(null);
		
		projetoTeste.addOcorrencia(ocorrencia);
		
		assertEquals(1, projetoTeste.getNumeroDeOcorrencias());
	}
	
	@Test
	public void adicionaDuasOcorrenciasAoProjeto() throws Exception {
		Projeto projetoTeste = new Projeto("Projeto teste");
		Ocorrencia ocorrencia1 = this.criaNovaOcorrenciaValida();
		Ocorrencia ocorrencia2 = this.criaNovaOcorrenciaValida();
		
		projetoTeste.addOcorrencia(ocorrencia1);
		projetoTeste.addOcorrencia(ocorrencia2);
		
		assertEquals(2, projetoTeste.getNumeroDeOcorrencias());
	}
	
	@Test(expected=Exception.class)
	public void adicionaMesmaOcorrenciaAoProjeto() throws Exception {
		Projeto projetoTeste = new Projeto("Projeto teste");
		Ocorrencia ocorrencia = this.criaNovaOcorrenciaValida();
		
		projetoTeste.addOcorrencia(ocorrencia);
		projetoTeste.addOcorrencia(ocorrencia);
	}
	
	@Test
	public void adicionaUmParticipanteAoProjeto() throws Exception {
		Projeto projetoTeste = new Projeto("Projeto teste");
		Funcionario funcionario = this.criaNovoFuncionarioValido();
		
		projetoTeste.addParticipante(funcionario);
		
		assertEquals(1, projetoTeste.getNumeroDeParticipantes());
	}
	
	@Test
	public void adicionaDoisParticipantesAoProjeto() throws Exception {
		Projeto projetoTeste = new Projeto("Projeto teste");
		Funcionario funcionario1 = this.criaNovoFuncionarioValido();
		Funcionario funcionario2 = this.criaNovoFuncionarioValido();
		
		projetoTeste.addParticipante(funcionario1);
		projetoTeste.addParticipante(funcionario2);
		
		assertEquals(2, projetoTeste.getNumeroDeParticipantes());
	}
	
	@Test(expected=Exception.class)
	public void adicionaMesmoParticipanteAoProjeto() throws Exception {
		Projeto projetoTeste = new Projeto("Projeto teste");
		Funcionario funcionario = this.criaNovoFuncionarioValido();
		
		projetoTeste.addParticipante(funcionario);
		projetoTeste.addParticipante(funcionario);
	}
	
	@Test
	public void igualdadeDeProjetosIguais() throws Exception {
		Projeto projetoTeste = new Projeto("Projeto teste");
		
		assertEquals(projetoTeste, projetoTeste);
	}
	
	@Test
	public void igualdadeDeProjetosNaoIguais() throws Exception {
		Projeto projetoTeste1 = new Projeto("Projeto teste 1");
		Projeto projetoTeste2 = new Projeto("Projeto teste 2");
		
		assertNotEquals(projetoTeste1, projetoTeste2);
	}
	
	@Test
	public void igualdadeDeProjetosPassandoNull() throws Exception {
		Projeto projetoTeste = new Projeto("Projeto teste");
		
		assertNotEquals(projetoTeste, null);
	}
	
	//Métodos uteis
	public Ocorrencia criaNovaOcorrenciaValida() throws Exception {
		Ocorrencia ocorrencia = new Ocorrencia("Bug na classe Avião.", 
				Ocorrencia.Prioridade.ALTA, Ocorrencia.Tipo.BUG);
		Funcionario responsavel = this.criaNovoFuncionarioValido();

		ocorrencia.setResponsavel(responsavel);
		return ocorrencia;
	}
	
	public Funcionario criaNovoFuncionarioValido(){
		return new Funcionario("Fulano da Silva");
	}
	
}
