package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import main.exception.OcorrenciaException;
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
		Ocorrencia ocorrencia = this.criaOcorrenciaDeBugComPrioridadeAltaValida();

		assertEquals(1, ocorrencia.getUid());
		assertEquals(resumoOcorrencia, ocorrencia.getResumo());
		assertEquals(prioridadeOcorrencia, ocorrencia.getPrioridade());
		assertEquals(tipoOcorrencia, ocorrencia.getTipo());
		assertEquals(Ocorrencia.Estado.ABERTA, ocorrencia.getEstado());
		assertEquals(this.responsavelPelaOcorrencia, ocorrencia.getResponsavel());
	}
	
	@Test
	public void atribuiResponsavelParaUmaOcorrencia() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaDeBugComPrioridadeAltaValida();
		Funcionario novoResponsavel = new Funcionario("Ciclano de Souza");
		ocorrencia.setResponsavel(novoResponsavel);
		
		assertEquals(novoResponsavel, ocorrencia.getResponsavel());
	}
	
	@Test(expected=OcorrenciaException.class)
	public void atribuiResponsavelParaUmaOcorrenciaCompletada() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaDeBugComPrioridadeAltaValida();
		
		ocorrencia.setEstado(Ocorrencia.Estado.COMPLETADA);
		ocorrencia.setResponsavel(responsavelPelaOcorrencia);
	}
	
	@Test
	public void trocaPrioridadeDeUmaOcorrencia() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaDeBugComPrioridadeAltaValida();
		Ocorrencia.Prioridade prioridadeBaixa = Ocorrencia.Prioridade.BAIXA;
		
		ocorrencia.setPrioridade(prioridadeBaixa);
		
		assertEquals(prioridadeBaixa, ocorrencia.getPrioridade());
	}
	
	@Test(expected=OcorrenciaException.class)
	public void trocaPrioridadeDeUmaOcorrenciaCompletada() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaDeBugComPrioridadeAltaValida();
		Ocorrencia.Prioridade prioridadeBaixa = Ocorrencia.Prioridade.BAIXA;
		
		ocorrencia.setEstado(Ocorrencia.Estado.COMPLETADA);
		ocorrencia.setPrioridade(prioridadeBaixa);
	}
	
	@Test
	public void igualdadeDeOcorrenciasIguais() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaDeBugComPrioridadeAltaValida();
		
		assertEquals(ocorrencia, ocorrencia);
	}
	
	@Test
	public void igualdadeDeProjetosNaoIguais() throws Exception {
		Ocorrencia ocorrencia1 = this.criaOcorrenciaDeBugComPrioridadeAltaValida();
		Ocorrencia ocorrencia2 = this.criaOcorrenciaDeBugComPrioridadeAltaValida();

		assertNotEquals(ocorrencia1, ocorrencia2);
	}
	
	@Test
	public void igualdadeDeProjetosPassandoNull() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaDeBugComPrioridadeAltaValida();
		
		assertNotEquals(ocorrencia, null);
	}
	
	@Test
	public void testeParaAumentarCoberturaDoTeste() throws Exception {
		//https://stackoverflow.com/questions/4512358/emma-coverage-on-enum-types
		
		assertEquals(3, Ocorrencia.Prioridade.values().length);
		assertEquals(Ocorrencia.Prioridade.BAIXA, Ocorrencia.Prioridade.valueOf("BAIXA"));
		
		assertEquals(3, Ocorrencia.Tipo.values().length);
		assertEquals(Ocorrencia.Tipo.BUG, Ocorrencia.Tipo.valueOf("BUG"));
		
		assertEquals(2, Ocorrencia.Estado.values().length);
		assertEquals(Ocorrencia.Estado.COMPLETADA, Ocorrencia.Estado.valueOf("COMPLETADA"));
	}
	
	// Métodos uteis
	private Ocorrencia criaOcorrenciaDeBugComPrioridadeAltaValida(){
		return new Ocorrencia(resumoOcorrencia, prioridadeOcorrencia, 
				tipoOcorrencia, this.responsavelPelaOcorrencia);
	}
}
