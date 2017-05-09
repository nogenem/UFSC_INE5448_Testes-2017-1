package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.dados.feijao;

public class CartaFeijao {
	
	private String valor;
	private String naipe;
	
	public CartaFeijao(String valor, String naipe) {
		this.valor = valor;
		this.naipe = naipe;
	}
	
	public String fornecerComoTexto() {
		return (valor+naipe.toLowerCase());
	}
	
	public String mostrarBonitinho() {
		String nomeDoValor = valor.toString();
		if (valor.equals("A"))
			nomeDoValor = "Ás";		
		if (valor.equals("J"))
			nomeDoValor = "Valete";
		if (valor.equals("Q"))
			nomeDoValor = "Dama";
		if (valor.equals("K"))
			nomeDoValor = "Rei";
		
		return nomeDoValor + " de " + naipe.toString();
	}
}