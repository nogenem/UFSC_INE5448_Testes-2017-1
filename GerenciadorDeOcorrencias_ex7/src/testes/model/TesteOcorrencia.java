package testes.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.model.Ocorrencia;

public class TesteOcorrencia {

	@Test
	public void criaNovoOcorrencia() {
		Ocorrencia ocorrencia = new Ocorrencia(Ocorrencia.Prioridade.ALTA, 
				Ocorrencia.Tipo.BUG, "Bug encontrado na classe Avião");
		
		assertTrue(ocorrencia.getUid() > 0);
		assertEquals(Ocorrencia.Prioridade.ALTA, ocorrencia.getPrioridade());
		assertEquals(Ocorrencia.Tipo.BUG, ocorrencia.getTipo());
		assertEquals("Bug encontrado na classe Avião", ocorrencia.getResumo());
		assertEquals(Ocorrencia.Status.ABERTA, ocorrencia.getStatus());
		assertEquals(null, ocorrencia.getResponsavel());
	}

}
