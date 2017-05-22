package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import main.controller.ControladorDeDados;

public class TesteControladorDeDados {

	@Test
	public void cadastraUmNovoFuncionario() {
		ControladorDeDados controlador = new ControladorDeDados();
		long uidFunc = controlador.cadastraFuncionario("Fulano da Silva");
		
		assertTrue(uidFunc > 0);
		assertEquals(1, controlador.getNumeroDeFuncionarios());
	}
	
	@Test
	public void cadastraUmNovoProjeto() throws Exception {
		ControladorDeDados controlador = new ControladorDeDados();
		long uidProj = controlador.cadastraProjeto("Projeto teste");
		
		assertTrue(uidProj > 0);
		assertEquals(1, controlador.getNumeroDeProjetos());
	}

}
