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
	
	@Test
	public void igualdadeDeProjetosIguais() throws Exception {
		Projeto proj1 = new Projeto("Projeto teste 1");
		
		assertEquals(proj1, proj1);
	}
	
	@Test
	public void igualdadeDeProjetosNaoIguais() throws Exception {
		Projeto proj1 = new Projeto("Projeto teste 1");
		Projeto proj2 = new Projeto("Projeto teste 2");
		
		assertNotEquals(proj1, proj2);
	}
	
	@Test
	public void igualdadeDeProjetosPassandoNull() throws Exception {
		Projeto proj1 = new Projeto("Projeto teste 1");
		
		assertNotEquals(proj1, null);
	}

}
