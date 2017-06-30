package fixtures;

import fit.ColumnFixture;
import interfaces.ILeiloavel;
import modelo.FachadaMercadoLeilaoComSerializacao;

public class LanceColumnFixtureTest extends ColumnFixture {

	public String nomeProduto;
	public String cpfComprador;
	public double valorLance;
	
	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();
	
	public LanceColumnFixtureTest(){
		super();
	}
	
	public boolean darLance(){
		try{
			fachada.daLance(nomeProduto, cpfComprador, valorLance);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public boolean verificarLanceExiste(){
		try{
			for(ILeiloavel p : fachada.getProdutosQueDeuLance(cpfComprador)){
				if(p.getNome().equals(nomeProduto))
					return true;
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
