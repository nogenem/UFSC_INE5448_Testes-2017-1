package modelo;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import interfaces.ILeiloavel;
import interfaces.IUsuario;
import interfaces.IVendido;

public class FachadaMercadoLeilaoComSerializacao {

	MercadoLeilao mercado = new MercadoLeilao();

	public void cadastrarUsuario(String nome, String endereco, String email, String cpf) throws Exception {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		mercado.cadastrarUsuario(nome, endereco, email, cpf);
		fabrica.desmontar(mercado);
	}

	public void cadastrarProduto(String nome, String descricao, Double lanceMinimo, String cpfLeiloador, Date dataLimite)
			throws Exception {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		mercado.cadastrarProduto(nome, descricao, lanceMinimo, cpfLeiloador, dataLimite);
		fabrica.desmontar(mercado);
	}

	public List<? extends ILeiloavel> getProdutosEmLeilao() {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		return mercado.getProdutosEmLeilao();
	}

	public List<? extends ILeiloavel> getProdutosVencidosENaoVendidos() {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		return mercado.getProdutosVencidosENaoVendidos();
	}

	public List<? extends IVendido> getProdutosVendidos() {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		return mercado.getProdutosVendidos();
	}

	public List<IUsuario> getUsuariosCadastrados() {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		return mercado.getUsuariosCadastrados();
	}

	public void daLance(String nomeProduto, String cpfComprador, Double valorLance) throws Exception {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		mercado.daLance(nomeProduto, cpfComprador, valorLance);
		fabrica.desmontar(mercado);
	}

	public List<Lance> retornaTodosOsLancesEfetuados() {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		return mercado.retornaTodosOsLancesEfetuados();
	}

	public List<Lance> retornaLancesDeUmUsuario(String cpfUsuario) throws Exception {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		return mercado.retornaLancesDeUmUsuario(cpfUsuario);
	}

	public List<ProdutoLeilao> retornaProdutosDeUmLeiloador(String cpfUsuario) throws Exception {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		return mercado.retornaProdutosDeUmLeiloador(cpfUsuario);
	}

	public List<? extends ILeiloavel> getProdutosQueDeuLance(String cpf) throws Exception {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		return mercado.getProdutosQueDeuLance(cpf);
	}

	public IUsuario getUsuarioPor(String cpf) throws Exception {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		return mercado.getUsuarioPor(cpf);
	}

	public void limparMercado() {
		File file = new File("arquivoDoMercado");
		if(file.exists()){
			file.delete();
		}
		
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = new MercadoLeilao();
		fabrica.desmontar(mercado);
	}

	public Boolean isPossuiUsuario() {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		return mercado.isPossuiUsuario();
	}

	public Boolean isPossuiProduto() {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		return mercado.isPossuiProduto();
	}

	public Boolean verificaSeOProdutoJaExiste(String nome) {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		return mercado.verificaSeOProdutoJaExiste(nome);
	}

	public void montarMercado() {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		if (mercado == null) {
			mercado = new MercadoLeilao();
			fabrica.desmontar(mercado);
		}
	}

	public void desmontarMercado() {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		if (mercado == null) {
			mercado = new MercadoLeilao();
			fabrica.desmontar(mercado);
		}
		fabrica.desmontar(mercado);

	}

	public Boolean isMercadoMontado() {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		if (mercado == null) {
			return false;
		}
		return true;
	}

	public Boolean isMercadoDesmontado() {
		FabricaDeMercado fabrica = new FabricaDeMercado();
		mercado = (MercadoLeilao) fabrica.montar();
		if (mercado == null) {
			return false;
		}
		return true;
	}

}