package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import main.model.Projeto;

public class TesteProjeto {

	@Test
	public void criaNovoProjeto() {
		String nome = "Projeto teste";
		Projeto proj = new Projeto(nome);
		
		assertTrue(proj.getUid() > 0);
		assertEquals(nome, proj.getNome());
		assertNotEquals(null, proj.getOcorrencias());
	}

}
