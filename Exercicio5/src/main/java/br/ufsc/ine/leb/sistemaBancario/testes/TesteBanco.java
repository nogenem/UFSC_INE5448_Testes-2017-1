package br.ufsc.ine.leb.sistemaBancario.testes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

public class TesteBanco {
	
	private SistemaBancario sistemaBancario;
	private Banco bancoDoBrasil;
	
	@Before
	public void configurar() {
		sistemaBancario = new SistemaBancario();
		bancoDoBrasil = sistemaBancario.criarBanco("Banco do Brasil", Moeda.BRL);
	}
	
	// Caso de Teste 1
	@Test
	public void caixaEconomica() {
		assertEquals("Banco do Brasil", bancoDoBrasil.obterNome());
		assertEquals(Moeda.BRL, bancoDoBrasil.obterMoeda());
	}

}
