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
		String nome = "Fulano da Silva";
		Funcionario func = new Funcionario(nome);
		
		assertEquals(1, func.getUid());
		assertEquals(nome , func.getNome());
	}
	
	@Test
	public void igudadeDeFuncionariosIguais() throws Exception {
		Funcionario fulano = new Funcionario("Fulano da Silva");
		
		assertEquals(fulano, fulano);
	}
	
	@Test
	public void igudadeDeFuncionariosNaoIguais() throws Exception {
		Funcionario fulano = new Funcionario("Fulano da Silva");
		Funcionario joe = new Funcionario("Joe Doe");
		
		assertNotEquals(fulano, joe);
	}
	
	@Test
	public void igudadeDeFuncionariosPassandoNull() throws Exception {
		Funcionario fulano = new Funcionario("Fulano da Silva");
		Funcionario joe = null;
		
		assertNotEquals(fulano, joe);
	}

}
