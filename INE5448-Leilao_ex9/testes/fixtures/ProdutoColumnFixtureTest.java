package fixtures;

import java.util.Date;

import fit.ColumnFixture;
import modelo.FachadaMercadoLeilaoComSerializacao;

public class ProdutoColumnFixtureTest extends ColumnFixture {

	public String nome;
	public String descricao;
	public double lanceMinimo;
	public String cpfLeiloador;
	public Date dataLimite;
	
	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();
	
	public ProdutoColumnFixtureTest(){
		super();
	}
	
	public boolean cadastrarProduto(){
		try{
			fachada.cadastrarProduto(nome, descricao, lanceMinimo, cpfLeiloador, dataLimite);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public boolean verificarProdutoExiste(){
		try{
			return fachada.verificaSeOProdutoJaExiste(nome);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
