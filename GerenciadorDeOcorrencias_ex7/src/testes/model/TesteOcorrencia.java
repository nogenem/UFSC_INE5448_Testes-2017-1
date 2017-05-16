package testes.model;

import static org.junit.Assert.*;

import org.junit.Test;

import main.model.Ocorrencia;

public class TesteOcorrencia {

	@Test
	public void criaNovoOcorrencia() {
		Ocorrencia ocorrencia = new Ocorrencia("Bug encontrado na classe Avião");
		
		assertTrue(ocorrencia.getUid() > 0);
		assertEquals("Bug encontrado na classe Avião", ocorrencia.getResumo());
	}

}
