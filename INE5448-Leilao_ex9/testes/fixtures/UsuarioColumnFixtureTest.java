package fixtures;

import fit.ColumnFixture;
import modelo.FachadaMercadoLeilaoComSerializacao;

public class UsuarioColumnFixtureTest extends ColumnFixture {

	public String nome;
	public String endereco;
	public String email;
	public String cpf;
	
	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();
	
	public UsuarioColumnFixtureTest(){
		super();
	}
	
	public boolean cadastrarUsuario(){
		try{
			fachada.cadastrarUsuario(this.nome, this.endereco, this.email, this.cpf);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public boolean verificarUsuarioExiste(){
		try{
			return fachada.getUsuarioPor(this.cpf) != null;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
