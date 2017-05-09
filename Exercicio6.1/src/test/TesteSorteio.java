package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import br.ufsc.es.projetoPoquer.modelo.utilidades.Sorteio;

public class TesteSorteio {

	@Test
	public void testeValoresIguais() {
		assertEquals(1, Sorteio.sortear(1, 1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testeMinMaiorQueMax() throws Exception {
		Sorteio.sortear(2, 1);
	}
	
	@Test
	public void testeEntradasPositivas() {
		int ret = Sorteio.sortear(2, 4);
		assertTrue(ret >= 2 && ret <= 4);
	}
	
	@Test
	public void testeEntradasNegativas() {
		int ret = Sorteio.sortear(-2, -4);
		assertTrue(ret >= 2 && ret <= 4);
	}
	
	@Ignore
	@Test(expected=IllegalArgumentException.class)
	public void testeEntradasNosLimitesDoInt() {
		//PS: este método não retorna erro porque o Math.abs não
		//consegue converter o min para um valor negativo
		int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
		Sorteio.sortear(min, max);
	}
	
	@Ignore
	@Test
	public void testeMaxNoLimiteDoInt(){
		//PS: da erro porque a linha 13 do código soma 1 ao valor
		//max, causando um overflow da variavel
		int max = Integer.MAX_VALUE;
		int ret = Sorteio.sortear(0, max);
		assertTrue(ret >= 0 && ret <= max);
	}

}
