package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import main.model.Funcionario;
import main.model.Ocorrencia;

public class TesteOcorrencia {

	private final String resumoOcorrencia = "Bug na classe Avião.";
	private final Ocorrencia.Prioridade prioridadeOcorrencia = Ocorrencia.Prioridade.ALTA;
	private final Ocorrencia.Tipo tipoOcorrencia = Ocorrencia.Tipo.BUG;
	
	private Funcionario responsavelPelaOcorrencia;
	
	@Before
	public void setup(){
		Ocorrencia.zerarUID();
		this.responsavelPelaOcorrencia = new Funcionario("Fulano da Silva");
	}
	
	@Test
	public void criaNovaOcorrencia() {
		Ocorrencia ocorrencia = this.criaOcorrenciaValida();

		assertEquals(1, ocorrencia.getUid());
		assertEquals(resumoOcorrencia, ocorrencia.getResumo());
		assertEquals(prioridadeOcorrencia, ocorrencia.getPrioridade());
		assertEquals(tipoOcorrencia, ocorrencia.getTipo());
		assertEquals(Ocorrencia.Estado.ABERTA, ocorrencia.getEstado());
		assertEquals(null, ocorrencia.getResponsavel());
	}
	
	@Test
	public void atribuiResponsavelParaUmaOcorrencia() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaValida();
		ocorrencia.setResponsavel(responsavelPelaOcorrencia);
		
		assertEquals(responsavelPelaOcorrencia, ocorrencia.getResponsavel());
	}
	
	@Test(expected=Exception.class)
	public void atribuiResponsavelParaUmaOcorrenciaCompletada() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaValida();
		
		ocorrencia.setEstado(Ocorrencia.Estado.COMPLETADA);
		ocorrencia.setResponsavel(responsavelPelaOcorrencia);
	}
	
	@Test
	public void trocaPrioridadeDeUmaOcorrencia() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaValida();//Iniciada como ALTA
		Ocorrencia.Prioridade prioridadeBaixa = Ocorrencia.Prioridade.BAIXA;
		
		ocorrencia.setPrioridade(prioridadeBaixa);
		
		assertEquals(prioridadeBaixa, ocorrencia.getPrioridade());
	}
	
	@Test(expected=Exception.class)
	public void trocaPrioridadeDeUmaOcorrenciaCompletada() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaValida();//Iniciada como ALTA
		Ocorrencia.Prioridade prioridadeBaixa = Ocorrencia.Prioridade.BAIXA;
		
		ocorrencia.setEstado(Ocorrencia.Estado.COMPLETADA);
		ocorrencia.setPrioridade(prioridadeBaixa);
	}
	
	@Test
	public void igualdadeDeOcorrenciasIguais() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaValida();
		
		assertEquals(ocorrencia, ocorrencia);
	}
	
	@Test
	public void igualdadeDeProjetosNaoIguais() throws Exception {
		Ocorrencia ocorrencia1 = this.criaOcorrenciaValida();
		Ocorrencia ocorrencia2 = this.criaOcorrenciaValida();

		assertNotEquals(ocorrencia1, ocorrencia2);
	}
	
	@Test
	public void igualdadeDeProjetosPassandoNull() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaValida();
		
		assertNotEquals(ocorrencia, null);
	}

	// Métodos uteis
	private Ocorrencia criaOcorrenciaValida(){
		return new Ocorrencia(resumoOcorrencia, prioridadeOcorrencia, tipoOcorrencia);
	}
}
