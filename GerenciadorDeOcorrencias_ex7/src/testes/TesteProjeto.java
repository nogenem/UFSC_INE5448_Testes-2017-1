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
		Projeto projetoTeste = new Projeto("Projeto teste");
		
		assertEquals(1, projetoTeste.getUid());
		assertEquals("Projeto teste", projetoTeste.getNome());
		assertEquals(0, projetoTeste.getOcorrencias().size());
	}
	
	@Test
	public void igualdadeDeProjetosIguais() throws Exception {
		Projeto projetoTeste = new Projeto("Projeto teste");
		
		assertEquals(projetoTeste, projetoTeste);
	}
	
	@Test
	public void igualdadeDeProjetosNaoIguais() throws Exception {
		Projeto projetoTeste1 = new Projeto("Projeto teste 1");
		Projeto projetoTeste2 = new Projeto("Projeto teste 2");
		
		assertNotEquals(projetoTeste1, projetoTeste2);
	}
	
	@Test
	public void igualdadeDeProjetosPassandoNull() throws Exception {
		Projeto projetoTeste = new Projeto("Projeto teste");
		
		assertNotEquals(projetoTeste, null);
	}

}
