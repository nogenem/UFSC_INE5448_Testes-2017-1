package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.model.Ocorrencia;

public class TesteOcorrencia {

	@Test
	public void criaNovaOcorrencia() {
		String resumo = "Bug na classe Avião.";
		Ocorrencia.Prioridade prioridade = Ocorrencia.Prioridade.ALTA;
		Ocorrencia.Tipo tipo = Ocorrencia.Tipo.BUG;
		
		Ocorrencia ocorrencia = new Ocorrencia(resumo, 
				prioridade, tipo);

		assertTrue(ocorrencia.getUid() > 0);
		assertEquals(resumo, ocorrencia.getResumo());
		assertEquals(prioridade, ocorrencia.getPrioridade());
		assertEquals(tipo, ocorrencia.getTipo());
		assertEquals(Ocorrencia.Estado.ABERTA, ocorrencia.getEstado());
		assertEquals(null, ocorrencia.getResponsavel());
	}

}
