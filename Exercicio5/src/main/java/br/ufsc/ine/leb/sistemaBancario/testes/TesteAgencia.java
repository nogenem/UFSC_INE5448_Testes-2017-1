package br.ufsc.ine.leb.sistemaBancario.testes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

@FixtureSetup(TesteBanco.class)
public class TesteAgencia {
	@Fixture private SistemaBancario sistemaBancario;
	@Fixture private Banco bancoDoBrasil;

	private Agencia bancoDoBrasilCentro;
	
	@Before
	public void configurar() {
		bancoDoBrasilCentro = bancoDoBrasil.criarAgencia("Centro");
	}
	
	// Caso de Teste 2
	@Test
	public void testBancoDoBrasilCentro(){
		assertEquals("001", bancoDoBrasilCentro.obterIdentificador());
		assertEquals("Centro", bancoDoBrasilCentro.obterNome());
		assertEquals(bancoDoBrasil, bancoDoBrasilCentro.obterBanco());
	}
}
