package modelo;

import static org.junit.Assert.assertEquals;
import interfaces.IMercadoLeilao;
import interfaces.IUsuario;

import org.junit.Before;
import org.junit.Test;

public class TesteUmMercadoSerializado {

	private IMercadoLeilao mercado;
	private String nomeUsuario;
	private String enderecoUsuario;
	private String emailUsuario;
	private String cpfUsuario;
	private FabricaDeMercado fabrica;

	@Before
	public void setUp() {
		cpfUsuario = "062.193.859-93";
		mercado = CriadorDeObjetos.mercado();
		fabrica = CriadorDeObjetos.fabrica();

	}

	@Test
	public void desmotarParaODisco() throws Exception {
		fabrica.desmontar(mercado);
	}

	@Test
	public void montarParaOJavaOMercadoSerializado() throws Exception {
		fabrica.desmontar(mercado);
		IMercadoLeilao serializado = fabrica.montar();

		assertEquals(0, serializado.getProdutosVencidosENaoVendidos().size());
		assertEquals(0, serializado.getProdutosVendidos().size());
		assertEquals(0, serializado.getProdutosEmLeilao().size());
	}

	@Test
	public void montarParaOJavaOMercadoSerializadoComUmUsuario()
			throws Exception {
		mercado.cadastrarUsuario(nomeUsuario, enderecoUsuario, emailUsuario,
				cpfUsuario);
		IUsuario usuario = mercado.getUsuarioPor(cpfUsuario);
		fabrica.desmontar(mercado);
		IMercadoLeilao serializado = fabrica.montar();
		assertEquals(0, serializado.getProdutosVencidosENaoVendidos().size());
		assertEquals(0, serializado.getProdutosVendidos().size());
		assertEquals(0, serializado.getProdutosEmLeilao().size());
		assertEquals(usuario.getCpf(), serializado.getUsuarioPor(cpfUsuario).getCpf());
	}

}
