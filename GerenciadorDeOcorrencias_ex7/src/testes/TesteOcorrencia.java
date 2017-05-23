package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import main.model.Funcionario;
import main.model.Ocorrencia;

public class TesteOcorrencia {

	private final String resumo = "Bug na classe Avião.";
	private final Ocorrencia.Prioridade prioridade = Ocorrencia.Prioridade.ALTA;
	private final Ocorrencia.Tipo tipo = Ocorrencia.Tipo.BUG;
	
	private Funcionario responsavel;
	
	@Before
	public void setup(){
		Ocorrencia.zerarUID();
		this.responsavel = new Funcionario("Fulano da Silva");
	}
	
	@Test
	public void criaNovaOcorrencia() {
		Ocorrencia ocorrencia = this.criaOcorrenciaValida();

		assertEquals(1, ocorrencia.getUid());
		assertEquals(resumo, ocorrencia.getResumo());
		assertEquals(prioridade, ocorrencia.getPrioridade());
		assertEquals(tipo, ocorrencia.getTipo());
		assertEquals(Ocorrencia.Estado.ABERTA, ocorrencia.getEstado());
		assertEquals(null, ocorrencia.getResponsavel());
	}
	
	@Test
	public void trocaResponsavelDeUmaOcorrencia() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaValida();
		ocorrencia.setResponsavel(responsavel);
		
		assertEquals(responsavel, ocorrencia.getResponsavel());
	}
	
	@Test(expected=Exception.class)
	public void trocaResponsavelDeUmaOcorrenciaCompletada() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaValida();
		
		ocorrencia.setEstado(Ocorrencia.Estado.COMPLETADA);
		ocorrencia.setResponsavel(responsavel);
	}
	
	@Test
	public void trocaPrioridadeDeUmaOcorrencia() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaValida();
		Ocorrencia.Prioridade outraPrioridade = Ocorrencia.Prioridade.BAIXA;
		
		ocorrencia.setPrioridade(outraPrioridade);
		
		assertEquals(outraPrioridade, ocorrencia.getPrioridade());
	}
	
	@Test(expected=Exception.class)
	public void trocaPrioridadeDeUmaOcorrenciaCompletada() throws Exception {
		Ocorrencia ocorrencia = this.criaOcorrenciaValida();
		Ocorrencia.Prioridade outraPrioridade = Ocorrencia.Prioridade.BAIXA;
		
		ocorrencia.setEstado(Ocorrencia.Estado.COMPLETADA);
		ocorrencia.setPrioridade(outraPrioridade);
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
		return new Ocorrencia(resumo, prioridade, tipo);
	}
}
