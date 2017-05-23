package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import main.model.Projeto;

public class TesteProjeto {

	@Before
	public void setup(){
		Projeto.zerarUID();
	}
	
	@Test
	public void criaNovoProjeto() {
		String nome = "Projeto teste";
		Projeto proj = new Projeto(nome);
		
		assertEquals(1, proj.getUid());
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
