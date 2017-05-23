package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.model.Funcionario;
import main.model.Ocorrencia;

public class TesteOcorrencia {

	private final String resumo = "Bug na classe Avião.";
	private final Ocorrencia.Prioridade prioridade = Ocorrencia.Prioridade.ALTA;
	private final Ocorrencia.Tipo tipo = Ocorrencia.Tipo.BUG;
	
	private Ocorrencia ocorrencia;
	private Funcionario responsavel;
	
	@Before
	public void setup(){
		this.ocorrencia = new Ocorrencia(resumo, 
				prioridade, tipo);
		this.responsavel = new Funcionario("Fulano da Silva");
	}
	
	@Test
	public void criaNovaOcorrencia() {
		Ocorrencia ocorrencia = new Ocorrencia(resumo, 
				prioridade, tipo);

		assertTrue(ocorrencia.getUid() > 0);
		assertEquals(resumo, ocorrencia.getResumo());
		assertEquals(prioridade, ocorrencia.getPrioridade());
		assertEquals(tipo, ocorrencia.getTipo());
		assertEquals(Ocorrencia.Estado.ABERTA, ocorrencia.getEstado());
		assertEquals(null, ocorrencia.getResponsavel());
	}
	
	@Test
	public void trocaResponsavelDeUmaOcorrencia() throws Exception {
		ocorrencia.setResponsavel(responsavel);
		
		assertEquals(responsavel, ocorrencia.getResponsavel());
	}
	
	@Test(expected=Exception.class)
	public void trocaResponsavelDeUmaOcorrenciaCompletada() throws Exception {
		ocorrencia.setEstado(Ocorrencia.Estado.COMPLETADA);
		ocorrencia.setResponsavel(responsavel);
	}
	
	@Test
	public void trocaPrioridadeDeUmaOcorrencia() throws Exception {
		Ocorrencia.Prioridade outraPrioridade = Ocorrencia.Prioridade.BAIXA;
		
		ocorrencia.setPrioridade(outraPrioridade);
		
		assertEquals(outraPrioridade, ocorrencia.getPrioridade());
	}
	
	@Test(expected=Exception.class)
	public void trocaPrioridadeDeUmaOcorrenciaCompletada() throws Exception {
		Ocorrencia.Prioridade outraPrioridade = Ocorrencia.Prioridade.BAIXA;
		
		ocorrencia.setEstado(Ocorrencia.Estado.COMPLETADA);
		ocorrencia.setPrioridade(outraPrioridade);
	}
	
	@Test
	public void igualdadeDeOcorrenciasIguais() throws Exception {
		Ocorrencia ocorrencia1 = new Ocorrencia(resumo, prioridade, tipo);
		
		assertEquals(ocorrencia1, ocorrencia1);
	}
	
	@Test
	public void igualdadeDeProjetosNaoIguais() throws Exception {
		Ocorrencia ocorrencia1 = new Ocorrencia(resumo, prioridade, tipo);
		Ocorrencia ocorrencia2 = new Ocorrencia(resumo, prioridade, tipo);
		
		assertNotEquals(ocorrencia1, ocorrencia2);
	}
	
	@Test
	public void igualdadeDeProjetosPassandoNull() throws Exception {
		Ocorrencia ocorrencia1 = new Ocorrencia(resumo, prioridade, tipo);
		
		assertNotEquals(ocorrencia1, null);
	}

}
