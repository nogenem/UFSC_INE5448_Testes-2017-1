package br.ufsc.es.projetoPoquer.modelo.jogador.dados.feijao;

import br.ufsc.es.projetoPoquer.modelo.jogador.dados.ChaveDeSecao;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.NomeDeUsuario;

public final class JogadorFeijao {
	
	private Nome nome;
	private NomeDeUsuario nomeDeUsuário;
	private ChaveDeSecao chaveDeSeção;
	private Email email;
	private Senha senha;
	
	public JogadorFeijao() {
		nome = new Nome("");
		nomeDeUsuário = new NomeDeUsuario("");
		chaveDeSeção = new ChaveDeSecao();
		email = new Email("");
		senha = new Senha("");
	}
	
	public void fixarNome(Nome nome) {
		this.nome = nome;
	}
	
	public void fixarNomeDeUsuário(NomeDeUsuario nomeDeUsuário) {
		if (nomeDeUsuário == null) {
			nomeDeUsuário = new NomeDeUsuario("");
		}
		this.nomeDeUsuário = nomeDeUsuário;
	}
	
	public void fixarChaveDeSeção(ChaveDeSecao chaveDeSeção) {
		this.chaveDeSeção = chaveDeSeção;
	}
	
	public void fixarEmail(Email email) {
		this.email = email;
	}
	
	public void fixarSenha(Senha senha) {
		this.senha = senha;
	}

	public Nome fornecerNome() {
		return nome;
	}
	
	public NomeDeUsuario fornecerNomeDeUsuário() {
		return nomeDeUsuário;
	}
	
	public ChaveDeSecao fornecerChaveDeSecao() {
		return chaveDeSeção;
	}
	
	public Email fornecerEmail() {
		return email;
	}
	
	public Senha fornecerSenha() {
		return senha;
	} 
}