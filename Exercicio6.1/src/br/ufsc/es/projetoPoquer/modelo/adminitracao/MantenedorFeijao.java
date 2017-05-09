package br.ufsc.es.projetoPoquer.modelo.adminitracao;

public class MantenedorFeijao {
	
	private String nomeDeMantenedor;
	private String email;
	private String senha;
	
	public MantenedorFeijao() {
		
	}
	
	public void fixarNomeDeMantenedor(String nomeDeMantenedor) {
		this.nomeDeMantenedor = nomeDeMantenedor;
	}
	
	public void fixarEmail(String email) {
		this.email = email;
	}
	
	public void fixarSenha(String senha) {
		this.senha = senha;
	}
	
	public String fornecerNomeDeMantenedor() {
		return nomeDeMantenedor;
	}
	
	public String fornecerEmail() {
		return email;
	}
	
	public String fornecerSenha() {
		return senha;
	}
}