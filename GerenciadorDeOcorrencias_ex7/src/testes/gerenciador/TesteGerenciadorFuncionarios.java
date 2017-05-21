package testes.gerenciador;

import static org.junit.Assert.*;

import org.junit.Test;

import main.gerenciador.GerenciadorDeFuncionarios;
import main.model.Funcionario;

public class TesteGerenciadorFuncionarios {

	@Test
	public void cadastraNovoFuncionario() {
		GerenciadorDeFuncionarios gerenciador = new GerenciadorDeFuncionarios();	
		long uid = gerenciador.cadastraFuncinario("Fulano da Silva");
		
		assertTrue(uid > 0);
		assertEquals(1, gerenciador.getNumeroDeFuncionarios());
	}
	
	@Test
	public void pegaFuncionarioPeloUidCorreto() throws Exception {
		GerenciadorDeFuncionarios gerenciador = new GerenciadorDeFuncionarios();	
		long uid = gerenciador.cadastraFuncinario("Fulano da Silva");
		Funcionario func = gerenciador.getFuncionario(uid);
		
		assertEquals(uid, func.getUid());
	}
	
	@Test(expected=Exception.class)
	public void pegaFuncionarioComUidIncorreto() throws Exception {
		GerenciadorDeFuncionarios gerenciador = new GerenciadorDeFuncionarios();
		gerenciador.getFuncionario(123);
	}
}
