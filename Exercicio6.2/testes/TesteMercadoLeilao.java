import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import modelo.Lance;
import modelo.MercadoLeilao;
import modelo.ProdutoLeilao;
import modelo.Usuario;

public class TesteMercadoLeilao {
	
	private MercadoLeilao mercado;
	private Usuario leiloador;
	private Usuario comprador;
	private ProdutoLeilao produto;
	
	// Implicit Setup
	@Before
	public void setup() throws Exception {
		mercado = new MercadoLeilao();
		criaLeiloador();
		cadastraProduto();
		criaComprador();
	}
	
	// Testes
	@Test(expected=Exception.class)
	public void testeLanceComUsuarioNaoCadastrado() throws Exception {
		mercado.daLance(produto.getNome(), "333.333.333", produto.getLanceMinimo());
	}
	
	@Test(expected=Exception.class)
	public void testeLanceComValorAbaixoDoLanceMin() throws Exception {
		mercado.daLance(produto.getNome(), comprador.getCpf(), produto.getLanceMinimo()-10);
	}
	
	@Test(expected=Exception.class)
	public void testeLanceComProdutoErrado() throws Exception {
		mercado.daLance("Balão", comprador.getCpf(), 1000.0);
	}
	
	@Test
	public void testeLanceEfetuado() throws Exception {
		double valor = produto.getLanceMinimo()+10;
		mercado.daLance(produto.getNome(), comprador.getCpf(), valor);
		
		List<Lance> lances = mercado.retornaLancesDeUmUsuario(comprador.getCpf());
		Lance lance = lances.get(0);
		assertEquals(lance, lance.getProdutoQueRecebeuOLance().getLanceMaisRecente());
		assertEquals(comprador.getCpf(), lance.getCpfDonoDoLance());
		assertEquals(produto.getNome(), lance.getNomeProdutoQueRecebeuOLance());
		assertEquals(valor, lance.getValorDoLance(), 0);
	}
	
	// Métodos uteis
	private void criaLeiloador() throws Exception {
		leiloador = new Usuario("111.111.111", "João da Silva");
		leiloador.setEmail("joao@mail.com");
		leiloador.setEndereco("Av. São João");
		mercado.cadastrarUsuario(leiloador.getNome(), leiloador.getEndereco(), 
				leiloador.getEmail(), leiloador.getCpf());
	}
	
	private void cadastraProduto() throws Exception{
		produto = new ProdutoLeilao("Guitarra", "Uma linda guitarra...", 150.00, leiloador);
		produto.setDataLimite(new Date(new Date().getTime() + 3000));
		mercado.cadastrarProduto(produto.getNome(), produto.getDescricao(), 
				produto.getLanceMinimo(), leiloador.getCpf(), produto.getDataLimite());
	}
	
	private void criaComprador() throws Exception {
		comprador = new Usuario("222.222.222", "Maria Rosario");
		comprador.setEmail("maria@mail.com");
		comprador.setEndereco("Av. Santa Maria");
		mercado.cadastrarUsuario(comprador.getNome(), comprador.getEndereco(), 
				comprador.getEmail(), comprador.getCpf());
	}

}
