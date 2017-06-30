package fixtures;

import fitlibrary.SetUpFixture;
import modelo.FabricaDeMercado;
import modelo.MercadoLeilao;

public class UsuarioSetUpFixtureTest extends SetUpFixture {

	public UsuarioSetUpFixtureTest(){
		super();
	}
	
	public void nomeEnderecoEmailCpf(String nome, String endereco, String email, String cpf){
		try{
			FabricaDeMercado fabrica = new FabricaDeMercado();
			MercadoLeilao mercado = new MercadoLeilao();
			mercado.cadastrarUsuario(nome, endereco, email, cpf);
			fabrica.desmontar(mercado);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
