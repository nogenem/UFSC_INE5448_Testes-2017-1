package main.model;

public class Ocorrencia {
	
	private static long CURRENT_ID;
	
	private long uid;
	private String resumo;
	
	public Ocorrencia(String resumo) {
		this.uid = ++CURRENT_ID;
		this.resumo = resumo;
	}

	public String getResumo() {
		return this.resumo;
	}

	public long getUid() {
		return this.uid;
	}
	
	
}
