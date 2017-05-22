package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.model.Funcionario;

public class TesteFuncionario {

	@Test
	public void criaNovoFuncionario() {
		String nome = "Fulano da Silva";
		Funcionario func = new Funcionario(nome);
		
		assertTrue(func .getUid() > 0);
		assertEquals(nome , func.getNome());
	}

}
