package fixtures;

import fit.ActionFixture;
import modelo.FachadaMercadoLeilaoComSerializacao;

public class MercadoActionFixtureTest extends ActionFixture {
	
	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();
	
	public void montarMercado() {
		fachada.montarMercado();
	}
	
	public Boolean mercadoMontado(){
		return fachada.isMercadoMontado();
	}
	
	public void desmontarMercado() {
		fachada.desmontarMercado();
	}
	
	public Boolean mercadoDesmontado(){
		return fachada.isMercadoDesmontado();
	}
	
	public void limparMercado() {
		fachada.limparMercado();
	}
	
	public Boolean mercadoLimpo(){
		return !fachada.isPossuiProduto() &&
				!fachada.isPossuiUsuario();
	}

}
