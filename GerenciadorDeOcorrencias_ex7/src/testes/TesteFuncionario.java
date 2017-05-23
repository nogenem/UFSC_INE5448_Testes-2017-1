package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import main.model.Funcionario;

public class TesteFuncionario {

	@Before
	public void setup(){
		Funcionario.zerarUID();
	}
	
	@Test
	public void criaNovoFuncionario() {
		Funcionario fulano = new Funcionario("Fulano da Silva");
		
		assertEquals(1, fulano.getUid());
		assertEquals("Fulano da Silva", fulano.getNome());
	}
	
	@Test
	public void igudadeDeFuncionariosIguais() throws Exception {
		Funcionario fulano = new Funcionario("Fulano da Silva");
		
		assertEquals(fulano, fulano);
	}
	
	@Test
	public void igudadeDeFuncionariosNaoIguais() throws Exception {
		Funcionario fulano = new Funcionario("Fulano da Silva");
		Funcionario ciclano = new Funcionario("Ciclano de Souza");
		
		assertNotEquals(fulano, ciclano);
	}
	
	@Test
	public void igudadeDeFuncionariosPassandoNull() throws Exception {
		Funcionario fulano = new Funcionario("Fulano da Silva");
		
		assertNotEquals(fulano, null);
	}

}
