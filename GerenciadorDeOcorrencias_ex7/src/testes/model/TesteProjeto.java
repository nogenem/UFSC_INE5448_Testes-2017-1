package testes.model;

import static org.junit.Assert.*;

import org.junit.Test;

import main.model.Projeto;

public class TesteProjeto {

	@Test
	public void criaUmNovoProjeto() {
		Projeto projeto = new Projeto("Projeto inicial");
		
		assertTrue(projeto.getUid() > 0);
		assertEquals("Projeto inicial", projeto.getNome());
	}

}
