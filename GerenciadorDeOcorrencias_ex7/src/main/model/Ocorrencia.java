package main.model;

public class Ocorrencia {
	
	public enum Prioridade { ALTA, MEDIA, BAIXA };
	public enum Tipo { TAREFA, BUG, MELHORIA };
	public enum Estado { ABERTA, COMPLETADA };
	
	private static long CURRENT_UID;
	
	private long uid;
	private String resumo;
	private Prioridade prioridade;
	private Tipo tipo;
	private Estado estado;
	private Funcionario responsavel;
	
	public Ocorrencia(String resumo, Prioridade prioridade, Tipo tipo) {
		this.uid = ++CURRENT_UID;
		this.resumo = resumo;
		this.prioridade = prioridade;
		this.tipo = tipo;
		this.estado = Estado.ABERTA;
		this.responsavel = null;
	}

	public long getUid() {
		return uid;
	}

	public String getResumo() {
		return resumo;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) throws Exception {
		if(this.estado != Estado.ABERTA)
			throw new Exception("Tentativa de modificar a prioridade da ocorrencia com uid = " +this.uid+ 
					" enquanto a mesma não esta aberta!");
		this.prioridade = prioridade;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Funcionario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Funcionario responsavel) throws Exception {
		if(this.estado != Estado.ABERTA)
			throw new Exception("Tentativa de modificar o responsavel da ocorrencia com uid = " +this.uid+ 
					" enquanto a mesma não esta aberta!");
		this.responsavel = responsavel;
	}
	
	public static void zerarUID(){
		CURRENT_UID = 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ocorrencia) {
			Ocorrencia ocorrencia = (Ocorrencia) obj;
			return this.uid == ocorrencia.uid;
		}
		return super.equals(obj);
	}
	
}
