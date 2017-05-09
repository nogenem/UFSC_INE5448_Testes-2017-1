package br.ufsc.es.projetoPoquer.bancoDeDados;

import java.util.List;

public final class OperacaoDeDados<T> {
	
	private boolean sucesso;
	private String mensagemDeErro;
	private T consulta; 
	private List<T> consultas;
	
	public OperacaoDeDados() {
		sucesso = true;
	}
	
	public OperacaoDeDados(T consulta) {
		sucesso = true;
		this.consulta = consulta;
	}
	
	public OperacaoDeDados(List<T> consultas) {
		sucesso = true;
		this.consultas = consultas;
	}
	
	public OperacaoDeDados(String mensagemDeErro) {
		sucesso = false;
		this.mensagemDeErro = mensagemDeErro;
	}
	
	public boolean sucesso() {
		return sucesso;
	}
	
	public String fornecerMensagemDeErro() {
		return mensagemDeErro;
	}
	
	public T fornecerConsulta() {
		return consulta;
	}
	
	public List<T> fornecerConsultas() {
		return consultas;
	}
}