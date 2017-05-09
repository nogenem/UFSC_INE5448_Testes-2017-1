package br.ufsc.es.projetoPoquer.modelo.resposta;

public interface TipoResposta {
	
	public boolean possuiErro();
	
	public String fornecerMensagemDeErro();
}
