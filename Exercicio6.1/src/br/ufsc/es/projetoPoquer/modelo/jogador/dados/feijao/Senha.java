package br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao;

public final class Senha {
	
	private String senha;
	
	public Senha(String senha) {
		this.senha = senha;
	}
	
	public String fornecerComoTexto() {
		return senha;
	}
	
	public boolean éIgual(Senha outraSenha) {
		return senha.equals(outraSenha.senha);
	}
}