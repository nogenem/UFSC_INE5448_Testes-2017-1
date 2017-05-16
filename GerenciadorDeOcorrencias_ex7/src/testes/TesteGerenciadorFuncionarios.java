package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import main.gerenciador.GerenciadorDeFuncionarios;
import main.model.Funcionario;

public class TesteGerenciadorFuncionarios {

	@Test
	public void cadastraNovoFuncionario() {
		GerenciadorDeFuncionarios gerenciador = new GerenciadorDeFuncionarios();	
		Funcionario funcionario = new Funcionario("Fulano da Silva");
		boolean retorno = gerenciador.cadastraFuncinario(funcionario);
		
		assertTrue(retorno);
		assertEquals(1, gerenciador.getNumeroDeFuncionarios());
	}

}
