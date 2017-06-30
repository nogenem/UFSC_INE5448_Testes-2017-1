package fixtures;

import java.util.Date;

import fitlibrary.SetUpFixture;
import modelo.FabricaDeMercado;
import modelo.MercadoLeilao;

public class ProdutoSetUpFixtureTest extends SetUpFixture {

	public ProdutoSetUpFixtureTest(){
		super();
	}
	
	public void nomeDescricaoLanceMinimoDataLimiteNomeCompradorCpfComprador(String nome, String descricao, 
			double lanceMinimo, Date dataLimite, String nomeComprador, String cpfComprador){
		try{
			FabricaDeMercado fabrica = new FabricaDeMercado();
			MercadoLeilao mercado = new MercadoLeilao();
			
			mercado.cadastrarUsuario("Patricia", "UFSC", "patricia@email.com", "323.667.674-47");
			mercado.cadastrarUsuario(nomeComprador, "UFSC", nomeComprador+"@email.com", cpfComprador);
			mercado.cadastrarProduto(nome, descricao, lanceMinimo, cpfComprador, dataLimite);
			fabrica.desmontar(mercado);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
