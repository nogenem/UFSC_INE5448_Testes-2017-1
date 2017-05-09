package br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao;

public final class Email {
	
	public String email;
	
	public Email(String email) {
		this.email = email.toLowerCase();
	}
	
	public String fornecerComoTexto() {
		return email;
	}
}