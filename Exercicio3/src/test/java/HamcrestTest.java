
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.number.OrderingComparison.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HamcrestTest {

	// Exemplo
	@Test
	public void testEquals() {
		assertThat("Gilney", equalTo("Gilney"));
	}
	
	// Exercicio 1
	@Test
	public void testObjectMatcher() throws Exception {
		Object obj1 = new Object();
		Object obj2 = new Object();
		assertThat(obj1, not(equalTo(obj2)));
	}
	
	@Test
	public void testNumberMatcher() throws Exception {
		float n1 = 9.9999999f;//A falta de precisão transforma este numero para 10
		float n2 = 10;
		assertThat(n1, greaterThanOrEqualTo(n2));
	}

	@Test
	public void testTextMatcher() throws Exception {
		String s1 = "Banana";
		String s2 = "ana";
		assertThat(s1, containsString(s2));
	}
	
	@Test
	public void testArrayMatcher() throws Exception {
		List<Integer> impares = Arrays.asList(1,3,5,7,9);
		assertThat(impares, not(hasItem(2)));
	}
	
	@Test
	public void testCompositionMatcher() throws Exception {
		String name = "Gilney Nathanael Mathias";
		assertThat(name, allOf(startsWith("Gil"), endsWith("ias")));
	}
}
