package testes.model;

import static org.junit.Assert.*;

import org.junit.Test;

import main.model.Funcionario;

public class TesteFuncionario {

	@Test
	public void funcinarioComDadosCorretos() {
		Funcionario funcionario = new Funcionario("Fulano da Silva");
		
		assertTrue(funcionario.getUid() > 0);
		assertEquals("Fulano da Silva", funcionario.getNome());
	}

}
