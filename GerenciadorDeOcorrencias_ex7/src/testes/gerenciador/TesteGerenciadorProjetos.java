package testes.gerenciador;

import static org.junit.Assert.*;

import org.junit.Test;

import main.gerenciador.GerenciadorDeProjetos;
import main.model.Projeto;

public class TesteGerenciadorProjetos {

	@Test
	public void cadastraNovoProjeto() {
		GerenciadorDeProjetos gerenciador = new GerenciadorDeProjetos();
		Projeto projeto = new Projeto("Projeto inicial");
		boolean retorno = gerenciador.cadastraProjeto(projeto );
		
		assertTrue(retorno);
		assertEquals(1, gerenciador.getNumeroDeProjetos());
	}

}
