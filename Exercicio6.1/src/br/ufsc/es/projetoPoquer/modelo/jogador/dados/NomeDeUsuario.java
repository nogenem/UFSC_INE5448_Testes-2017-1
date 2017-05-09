package br.ufsc.es.projetoPoquer.modelo.jogador.dados;

public final class NomeDeUsuario {
	
	private String nomeDeUsuário;
	
	public NomeDeUsuario(String nomeDeUsuário) {
		this.nomeDeUsuário = nomeDeUsuário;
	}
	
	public String fornecerComoTexto() {
		return nomeDeUsuário;
	}

	@Override
	public int hashCode() {
		return nomeDeUsuário.hashCode();
	}
	
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof NomeDeUsuario) {
			return nomeDeUsuário.equals(((NomeDeUsuario) objeto).nomeDeUsuário);
		}
		
		return false;
	}
}