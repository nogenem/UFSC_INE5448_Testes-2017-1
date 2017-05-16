package main.model;

public class Ocorrencia {
	
	public enum Prioridade{ALTA, MEDIA, BAIXA};
	public enum Tipo{TAREFA, BUG, MELHORIA};
	public enum Status{ABERTA, COMPLETADA};
	
	private static long CURRENT_ID;
	
	private long uid;	
	private Funcionario responsavel;
	private Prioridade prioridade;
	private Tipo tipo;
	private String resumo;
	private Status status;
	
	public Ocorrencia(Prioridade prioridade, Tipo tipo, String resumo) {
		this.uid = ++CURRENT_ID;
		this.responsavel = null;
		this.prioridade = prioridade;
		this.tipo = tipo;
		this.resumo = resumo;
		this.status = Status.ABERTA;
	}
	
	// Getters e Setters
	public long getUid() {
		return this.uid;
	}

	public Funcionario getResponsavel() {
		return responsavel;
	}

	public boolean setResponsavel(Funcionario responsavel) {
		if(this.status != Status.ABERTA)
			return false;
		this.responsavel = responsavel;
		return true;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public boolean setPrioridade(Prioridade prioridade) {
		if(this.status != Status.ABERTA)
			return false;
		this.prioridade = prioridade;
		return true;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public String getResumo() {
		return this.resumo;
	}
	
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
