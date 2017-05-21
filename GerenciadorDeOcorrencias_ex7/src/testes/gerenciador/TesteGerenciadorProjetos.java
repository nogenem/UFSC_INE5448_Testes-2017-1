package testes.gerenciador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.gerenciador.GerenciadorDeProjetos;

public class TesteGerenciadorProjetos {

	@Test
	public void cadastraNovoProjeto() {
		GerenciadorDeProjetos gerenciador = new GerenciadorDeProjetos();
		long uid = gerenciador.cadastraProjeto("Projeto inicial");
		
		assertTrue(uid > 0);
		assertEquals(1, gerenciador.getNumeroDeProjetos());
	}

}
